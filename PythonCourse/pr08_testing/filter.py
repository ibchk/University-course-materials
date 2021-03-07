"""Filtering."""


def remove_vowels(string: str) -> str:
    """
    Remove vowels (a, e, i, o, u).

    :param string: Input string
    :return string without vowels.
    """
    no_vowels = ""
    for i in string:
        list = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']
        if list.count(i):
            continue
        else:
            no_vowels += i
    return no_vowels


def longest_filtered_word(string_list: list) -> str:
    """
    Filter, find and return the longest string.

    :param string_list: List of strings.
    :return: Longest string without vowels.
    """
    list_of_strings = []
    for i in string_list:
        new_str = remove_vowels(i)
        list_of_strings.append(new_str)
    longest_str = ""
    for i in range(len(list_of_strings)):
        n = len(list_of_strings[i])
        m = len(longest_str)
        if n > m:
            longest_str = list_of_strings[i]
        else:
            continue
    return longest_str


def sort_list(string_list: list) -> list:
    """
    Filter vowels in strings and sort the list by the length.

    :param string_list: List of strings that need to be sorted.
    :return: Filtered list of strings sorted by the number of vowels.
    """
    list_of_strings = []
    for i in string_list:
        new_str = remove_vowels(i)
        list_of_strings.append(new_str)
    last_list = []
    for i in range(len(string_list)):
        word = longest_filtered_word(list_of_strings)
        last_list.append(word)
        list_of_strings.remove(word)
    return last_list


if __name__ == '__main__':
    print(-30 % 5)
