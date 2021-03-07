"""regex program."""
import re


def read_file(path: str) -> list:
    """
    Read file and return list of lines read.

    :param path: str
    :return: list
    """
    with open(path) as file:
        list1 = []
        for i in file:
            list1.append(i.replace('\n', ''))
        return list1


def match_specific_string(input_data: list, keyword: str) -> int:
    """
    Check if given list of strings contains keyword.

    Return all keyword occurrences (case insensitive). If an element cointains the keyword several times, count all the occurrences.

    ["Python", "python", "PYTHON", "java"], "python" -> 3

    :param input_data: list
    :param keyword: str
    :return: int
    """
    nr = 0
    low = keyword.lower()
    for i in input_data:
        nr += re.findall(keyword.lower(), i.lower()).count(low)
    return nr


def detect_email_addresses(input_data: list) -> list:
    """
    Check if given list of strings contains valid email addresses.

    Return all unique valid email addresses in alphabetical order presented in the list.
    ["Test", "Add me test@test.ee", "ago.luberg@taltech.ee", "What?", "aaaaaa@.com", ";_:Ö<//test@test.au??>>>;;d,"] ->
    ["ago.luberg@taltech.ee", "test@test.au", "test@test.ee"]

    :param input_data: list
    :return: list
    """
    list_emails = []
    for email in input_data:
        email = re.findall('[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[a-zA-Z0-9-.]+', email)
        for i in email:
            if i not in list_emails:
                list_emails.append(i)
    return sorted(list_emails)


if __name__ == '__main__':
    list_of_lines_emails = read_file("input_detect_email_addresses_example_1.txt")  # reading from file
    print(detect_email_addresses(
        list_of_lines_emails))  # ['allowed@example.com', 'firstname-lastname@example.com', 'right@example.com', 'spoon@lol.co.jp', 'testtest@dome.museum', 'testtest@example.name']

    list_of_lines_keywords = read_file("input_match_specific_string_example_1.txt")
    print(match_specific_string(list_of_lines_keywords, "Becky"))  # 9
