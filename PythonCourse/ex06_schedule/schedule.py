"""Create schedule from the given file."""
import re


def get_formatted_time(time: str) -> str:
    """Format 24 hour time to the 12 hour time."""
    search = re.findall(r"\d*\d*", time)
    if re.findall(r"\s", time):
        return 'wrong'
    hour = search[0]
    ms = search[2]
    day_half = 'AM'
    if len(ms) < 2:
        ms = '0' + ms
    if (int(ms) > 59) or (int(hour) > 23):
        return "wrong"
    if hour == '24' or hour == '0' or hour == '00':
        hour = '12'
    elif hour == '12':
        day_half = 'PM'
    elif int(hour) > 12:
        hour = str(int(hour) - 12)
        day_half = 'PM'
    if hour[0] == '0':
        hour = hour[1]
    return f'{hour}:{ms} {day_half}'


def time_to_number(time: str) -> int:
    """Make from time a number. the biggest time, the largest number."""
    if len(time) == 8:
        return int(time[:2] + time[3:5])
    else:
        return int(time[0] + time[2:4])


def create_list(string: str) -> list:
    """Make list of tuples, where are number, time and string."""
    list_am = []
    list_pm = []
    for time in re.findall(r'(\s{1})(\d{1,2})(\D|\W)(\d{1,2}) +([a-z]+)', string.lower()):
        am_pm_time = get_formatted_time(time[1] + '.' + time[3])
        if (am_pm_time != 'wrong') and ('AM' in am_pm_time):
            if am_pm_time[:2] == '12':
                list_am.append((time_to_number(am_pm_time) - 1200, am_pm_time, time[4]))
            else:
                list_am.append((time_to_number(am_pm_time), am_pm_time, time[4]))
        elif (am_pm_time != 'wrong') and ('PM' in am_pm_time):
            if am_pm_time[:2] == '12':
                list_pm.append((time_to_number(am_pm_time) + 7000, am_pm_time, time[4]))
            else:
                list_pm.append((time_to_number(am_pm_time) + 10000, am_pm_time, time[4]))
    return list_am + list_pm


def create_sorted_list(list_of_tuples: list) -> list:
    """Sort list by time: earlier in the beginning."""
    sorted_list = []
    lot = list_of_tuples
    for i in range(len(list_of_tuples)):
        tpl = (30000, 'None', 'None')
        for i in lot:
            if i[0] < tpl[0]:
                tpl = i
        if tpl[1:3] not in sorted_list:
            sorted_list.append(tpl[1:3])
        lot.remove(tpl)
    return sorted_list


def get_list_sorted_time(some_list: list) -> list:
    """Combine strings by time."""
    list2 = some_list
    list3 = []
    list3_sorted = []
    for i in list2:
        correct_list = list(i)
        for n in list2:
            if (i[0] == n[0]) and (i != n):
                correct_list.append(''.join(list(n[1])))
        if tuple(sorted(correct_list)) not in list3_sorted:
            list3.append(tuple(correct_list))
            list3_sorted.append(tuple(sorted(correct_list)))
    return list3


def get_time_size(list_tuples: list) -> int:
    """Get length of the longest time."""
    if len(list_tuples) == 0:
        nr = 5
    else:
        nr = 0
        for i in list_tuples:
            if len(i[0]) > nr:
                nr = len(i[0])
    return nr + 2


def get_string_size(list_tuples: list) -> int:
    """Get length of the longest string."""
    if len(list_tuples) == 0:
        nr = 6
    else:
        nr = 0
        for i in list_tuples:
            if len(i[1]) > nr:
                nr = len(i[1])
        if nr < 5:
            nr = 5
    return nr + 2


def get_final_list(list_of_w_tuples: list) -> list:
    """Combine strings in one string."""
    list2 = []
    for i in list_of_w_tuples:
        ltuple = [i[0]]
        str_of_str = ''
        nr = 0
        for w in i[1:]:
            if nr == 0:
                str_of_str += w
                nr += 1
            else:
                str_of_str += f', {w}'
        ltuple.append(str_of_str)
        list2.append(tuple(ltuple))
    return list2


def create_schedule_string(input_string: str) -> str:
    """Create schedule string from the given input string."""
    sort_list_tuples = get_final_list(get_list_sorted_time(create_sorted_list(create_list(input_string))))
    print(sort_list_tuples)
    time_size = get_time_size(sort_list_tuples)
    string_size = get_string_size(sort_list_tuples)
    dashes = '-' * (time_size + string_size + 3)
    table = dashes + '\n|' + ' ' * (time_size - 5) + 'time | items' + ' ' * (string_size - 6) + '|\n' + dashes
    if len(sort_list_tuples) == 0:
        table += f'\n| No items found |\n{dashes}'
        return table
    for i in sort_list_tuples:
        table += '\n|' + ' ' * (time_size - len(i[0]) - 1) + f'{i[0]} | {i[1]}' + \
                 ' ' * (string_size - len(i[1]) - 1) + '|'
    table += f'\n{dashes}'
    return table


def create_schedule_file(input_filename: str, output_filename: str) -> None:
    """Create schedule file from the given input file."""
    with open(input_filename) as file:
        shed1 = create_schedule_string(file.read())
    with open(output_filename, 'w') as file2:
        file2.write(shed1)


if __name__ == '__main__':
    print(create_schedule_string("03:40 ta 12.6 r "))
    create_schedule_file("schedule_input.txt", "schedule_output.txt")
