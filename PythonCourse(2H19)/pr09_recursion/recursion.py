"""Recursion is recursion."""


def recursive_reverse(s: str) -> str:
    """Reverse a string using recursion.

    recursive_reverse("") => ""
    recursive_reverse("abc") => "cba"

    :param s: string
    :return: reverse of s
    """
    if len(s) == 1:
        return s
    elif len(s) == 0:
        return ''
    p = recursive_reverse(s[1:-1])
    s = s[-1] + p + s[0]
    return s


def remove_nums_and_reverse(string):
    """
    Recursively remove all the numbers in the string and return reversed version of that string without numbers.

    print(remove_nums_and_reverse("poo"))  # "oop"
    print(remove_nums_and_reverse("3129047284"))  # empty string
    print(remove_nums_and_reverse("34e34f7i8l 00r532o23f 4n5oh565ty7p4"))  # "python for life"
    print(remove_nums_and_reverse("  k 4"))  # " k  "

    :param string: given string to change
    :return: reverse version of the original string, only missing numbers
    """
    if len(string) == 0:
        return ''
    make = remove_nums_and_reverse(string[1:])
    if not string[0].isnumeric():
        make += string[0]
    return make


def task1(string):
    """
    Figure out what this code is supposed to do and rewrite it using recursion.

    :param string: given string
    :return: figure it out
    """
    if len(string) < 2 or (string[0] == string[-1] and task1(string[1:-1]) is True):
        return True
    else:
        return False


def task2(string):
    """
    Figure out what this code is supposed to do and rewrite it using iteration.

    :param string: given string
    :return: figure it out
    """
    new = ''
    for i in range(len(string)):
        if i + 1 != len(string):
            if string[i] == string[i + 1]:
                new += string[i] + '-'
            else:
                new += string[i]
        elif i + 1 == len(string):
            new += string[i]
    return new
