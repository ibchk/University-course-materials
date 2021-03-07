# -*- coding: utf-8 -*-
"""Check if given ID code is valid."""


def is_valid_gender_number(i: int) -> bool:
    """
    Check if given number is correct.

    :param i: int
    :return: boolean
    """
    return 0 < i < 7


def is_leap_year(year: int) -> bool:
    """
    Check if given year is a leap year.

    :param year: int
    :return: boolean
    """
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True
    return False


def get_gender(gender_nr: int):
    """
    Get gender from the number.

    :param gender_nr: int
    :return: Female on male
    """
    if gender_nr % 2 == 0:
        return "female"
    return "male"


def is_valid_year_number(year_number: int) -> bool:
    """
    Check if given value is correct for year number in ID code.

    :param year_number: int
    :return: boolean
    """
    return 0 <= year_number < 100


def is_valid_month_number(month_number: int) -> bool:
    """
    Check if given value is correct for month number in ID code.

    :param month_number: int
    :return: boolean
    """
    return 0 < month_number < 13


def get_full_year(gender_number: int, year_number: int) -> int:
    """
    Define the 4-digit year when given person was born.

    Person gender and year numbers from ID code must help.
    Given year has only two last digits.

    :param gender_number: int
    :param year_number: int
    :return: int
    """
    str_year = ""
    if 0 < gender_number < 3:
        str_year += '18'
    elif 2 < gender_number < 5:
        str_year += '19'
    elif 4 < gender_number < 7:
        str_year += '20'
    if 0 <= year_number < 10:
        str_year += "0" + str(year_number)
    else:
        str_year += str(year_number)
    int_year = int(str_year)
    if is_valid_year_number(year_number):
        return int_year


def is_valid_day_number(gender_number: int, year_number: int, month_number: int, day_number: int) -> bool:
    """
    Check if given value is correct for day number in ID code.

    Also, consider leap year and which month has 30 or 31 days.

    :param gender_number: int
    :param year_number: int
    :param month_number: int
    :param day_number: int
    :return: boolean
    """
    if is_valid_gender_number(gender_number) and is_valid_year_number(year_number) \
            and is_valid_month_number(month_number):
        year = get_full_year(gender_number, year_number)
        if (month_number == 2 and ((is_leap_year(year) and 0 < day_number < 30)
                                   or (not is_leap_year(year) and 0 < day_number < 29))) or \
                (((month_number < 7 and month_number % 2 == 0 and month_number != 2)
                  or (month_number > 7 and month_number % 2 != 0)) and 0 < day_number <= 30) or \
                (((month_number < 8 and month_number % 2 != 0) or (month_number > 7 and month_number % 2 == 0))
                 and 0 < day_number < 32):
            return True
    return False


def is_valid_birth_number(birth_number: int):
    """
    Check if given value is correct for birth number in ID code.

    :param birth_number: int
    :return: boolean
    """
    return 0 < birth_number < 1000


def is_valid_control_number(id_code: str) -> bool:
    """
    Check if given value is correct for control number in ID code.

    Use algorithm made for creating this number.

    :param id_code: string
    :return: boolean
    """
    str_id_code = []
    for i in range(10):
        if i == 9:
            str_id_code += 1 * id_code[i]
        else:
            str_id_code += (i + 1) * id_code[i]
    int_id_code = [int(i) for i in str_id_code]
    sum_id = (sum(int_id_code))
    last_number = sum_id % 11
    if last_number == 10:
        str_id_code2 = []
        for i in range(10):
            if 6 < i < 10:
                str_id_code2 += (i - 6) * id_code[i]
            else:
                str_id_code2 += (i + 3) * id_code[i]
        int_id_code2 = [int(i) for i in str_id_code2]
        sum_id = (sum(int_id_code2))
        last_number2 = sum_id % 11
        if last_number2 == 10:
            last_number2 = 0
            if last_number2 == int(id_code[10]):
                return True
            else:
                return False
        elif last_number2 == int(id_code[10]):
            return True
        else:
            return False
    elif last_number == int(id_code[10]):
        return True
    return False


def get_birth_place(birth_number: int) -> str:
    """
    Find the place where the person was born.

    Possible locations are following: Kuressaare, Tartu, Tallinn, Kohtla-Järve, Narva, Pärnu,
    Paide, Rakvere, Valga, Viljandi, Võru and undefined. Lastly if the number is incorrect the function must return
    the following 'Wrong input!'
    :param birth_number: int
    :return: str
    """
    if (birth_number < 1) or (birth_number > 999):
        return "Wrong input!"
    else:
        return {
            0 < birth_number < 11: "Kuressaare",
            (10 < birth_number < 21) or (270 < birth_number < 371): "Tartu",
            (20 < birth_number < 221) or (470 < birth_number < 491): "Tallinn",
            220 < birth_number < 271: "Kohtla-Järve",
            370 < birth_number < 421: "Narva",
            420 < birth_number < 471: "Pärnu",
            490 < birth_number < 521: "Paide",
            520 < birth_number < 571: "Rakvere",
            570 < birth_number < 601: "Valga",
            600 < birth_number < 651: "Viljandi",
            650 < birth_number < 711: "Võru",
            710 < birth_number < 1000: "undefined",
        }[True]


def get_data_from_id(id_code: str) -> str:
    """
    Get possible information about the person.

    Use given ID code and return a short message.
    Follow the template - This is a <gender> born on <DD.MM.YYYY> in <location>.
    :param id_code: str
    :return: str
    """
    if is_valid_control_number(id_code):
        gender_nr = int(id_code[0])
        year_nr = int(id_code[1:3])
        month_nr = int(id_code[3:5])
        day_nr = int(id_code[5:7])
        gender = get_gender(gender_nr)
        if is_valid_day_number(gender_nr, year_nr, month_nr, day_nr):
            year = get_full_year(gender_nr, year_nr)
            date = f'{id_code[5:7]}.{id_code[3:5]}.{year}'
        else:
            return 'Given invalid ID code!'
        place_nr = int(id_code[7:10])
        birth_place = get_birth_place(place_nr)
        return f'This is a {gender} born on {date} in {birth_place}.'
    return 'Given invalid ID code!'


def is_id_valid(id_code: str) -> bool:
    """
    Check if given ID code is valid and return the result (True or False).

    Complete other functions before starting to code this one.
    You should use the functions you wrote before in this function.
    :param id_code: str
    :return: boolean
    """
    if len(id_code) == 11:
        if id_code.isdigit():
            gender_nr = int(id_code[0])
            year_nr = int(id_code[1:3])
            month_nr = int(id_code[3:5])
            day_nr = int(id_code[5:7])
            place_nr = int(id_code[7:10])
            if is_valid_day_number(gender_nr, year_nr, month_nr, day_nr) \
                    and (get_birth_place(place_nr) != 'undefined' or get_birth_place(place_nr) != "Wrong input!") \
                    and is_valid_control_number(id_code):
                return True
    return False
