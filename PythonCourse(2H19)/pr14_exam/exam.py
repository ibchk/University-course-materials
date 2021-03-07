"""Preparation for the exam."""


def swap_items(dic: dict) -> dict:
    """
    Given a dictionary return a new dictionary where keys and values are swapped.

    If duplicate keys in the new dictionary exist, leave the first one.
    {"a": 1, "b": 2, "c": 3} => {1: "a", 2: "b", 3: "c"}
    {"Morning": "Good", "Evening": "Good"} => {"Good": "Morning"}

    :param dic: original dictionary
    :return: dictionary where keys and values are swapped
    """
    nd = {}
    for i in dic.keys():
        if dic[i] not in nd:
            nd[dic[i]] = i
    return nd


def find_divisors(number) -> list:
    """
    The task is to find all the divisors for given number in range to the given number's value.

    Divisor - a number that divides evenly into another number.
    Return list of given number divisors in ascending order.
    NB! Numbers 1 and number itself must be excluded if there are more divisors
    than 1 and number itself!
    (138) > [2, 3, 6, 23, 46, 69]
    (3) > [1, 3]
    :param number: int
    :return: list of number divisors
    """
    li = [i for i in range(number + 1) if i != 0 and number % i == 0]
    if len(li) > 2:
        return li[1:-1]
    return li


def sum_of_multiplies(first_num, second_num, limit) -> int:
    """
    The task is to find all the multiplies of each given of two numbers within the limit.

    Then, find the sum of those multiplies.
    (3, 5, 20) => 98 (3 + 6 + 9 + 12 + 15 + 18 + 5 + 10 + 20) 15 is included only once
    (3, 3, 10) => 18 (3 + 6 + 9)
    (3, 10, 2) => 0
    :param first_num: first number
    :param second_num: second number
    :param limit: limit
    :return: sum of multiplies
    """
    li = []
    nr = first_num

    for n in range(2):
        for i in range(1, limit + 1):
            if nr > limit:
                break
            if nr not in li:
                li.append(nr)
            if n == 0:
                nr += first_num
            else:
                nr += second_num
        nr = second_num
    return sum(li)


def count_odds_and_evens(numbers: list) -> str:
    r"""
    The task is to count how many odd and even numbers does the given list contain.

    Do not count zeros (0).
    Result should be displayed as string "ODDS: {number of odds}\nEVENS: {number of evens}"

    count_odds_and_events([1, 2, 3]) => "ODDS: 2\nEVENS: 1"
    count_odds_and_events([1, 0]) => "ODDS: 1\nEVENS: 0"

    :param numbers: list
    :return: str
    """
    odds = [i for i in numbers if i != 0 and i % 2 != 0]
    evens = [i for i in numbers if i != 0 and i % 2 == 0]
    return f"ODDS: {len(odds)}\nEVENS: {len(evens)}"


def sum_between_25(numbers: list) -> int:
    """
    Return the sum of the numbers in the array which are between 2 and 5.

    Summing starts from 2 (not included) and ends at 5 (not included).
    The section can contain 2 (but cannot 5 as this would end it).
    There can be several sections to be summed.

    sum_between_25([1, 3, 6, 7]) => 0
    sum_between_25([1, 2, 3, 4, 5, 6]) => 7
    sum_between_25([1, 2, 3, 4, 6, 6]) => 19
    sum_between_25([1, 3, 3, 4, 5, 6]) => 0
    sum_between_25([1, 2, 3, 4, 5, 6, 1, 2, 9, 5, 6]) => 16
    sum_between_25([1, 2, 3, 2, 5, 5, 3, 5]) => 5
    """
    li = []
    start = False
    for i in numbers:
        if i == 2 and not start:
            start = True
        elif i == 5:
            start = False
        elif start:
            li.append(i)
    return sum(li)


def transcribe(dna_strand: str):
    """
    Write a function that returns a transcribed RNA strand from the given DNA strand.

    that is formed by replacing each nucleotide(character) with its complement: G => C, C => G, T => A, A => U
    Return None if it is not possible to transcribe a DNA strand.
    Empty string should return empty string.

    "ACGTGGTCTTAA" => "UGCACCAGAAUU"
    "gcu" => None

    :param dna_strand: original DNA strand
    :return: transcribed RNA strand in the uppercase or None
    """
    rna = ''
    for i in dna_strand:
        if i == 'G' or i == 'g':
            rna += 'C'
        elif i == 'C' or i == 'c':
            rna += 'G'
        elif i == 'T' or i == 't':
            rna += 'A'
        elif i == 'A' or i == 'a':
            rna += 'U'
        else:
            return None
    return rna


def union_of_dict(d1: dict, d2: dict):
    """
    Given two dictionaries return dictionary that has all the key-value pairs that are the same in given dictionaries.

    union_of_dict({"a": 1, "b": 2, "c":3}, {"a": 1, "b": 42}) ==> {"a": 1}
    union_of_dict({}, {"bar": "foo"}) => {}
    """
    di = {}
    if len(d1) > len(d2):
        mr = d1
        mr2 = d2
    else:
        mr = d2
        mr2 = d1
    for i in mr:
        if i in mr2 and mr[i] == mr2[i]:
            di[i] = mr[i]
    return di


def reserve_list(input_strings: list) -> list:
    """
    Given list of strings, return new reversed list where each list element is.

    reversed too. Do not reverse strings followed after element "python". If element is "java" -
    reverse mode is on again.
    P.S - "python" and "java" are not being reversed

    ['apple', 'banana', 'onion'] -> ['noino', 'ananab', 'elppa']
    ['lollipop', 'python', 'candy'] -> ['candy', 'python', 'popillol']
    ['sky', 'python', 'candy', 'java', 'fly'] -> ['ylf', 'java', 'candy', 'python', 'yks']
    ['sky', 'python', 'java', 'candy'] -> ['ydnac', 'java', 'python', 'yks']

    :param input_strings: list of strings
    :return: reversed list
    """
    li = []
    nr = 0
    for i in input_strings:
        if i == 'python':
            nr = 1
        elif i == 'java':
            nr = 0
        if nr == 0 and i != 'java':
            li.append(i[::-1])
        else:
            li.append(i)
    return li[::-1]


def convert_binary_to_decimal(binary_list: list):
    """
    Extract binary codes of given length from list and convert to decimal numbers.

    [0, 0, 0, 0] => 0.
    [0, 1, 0, 0] => 4.

    :param binary_list: list of 1 and 0 (binary code)
    :return: number converted into decimal system
    """
    li = []
    nr = 0
    for i in binary_list[::-1]:
        if i == 1:
            li.append(2 ** nr)
        nr += 1
    return sum(li)


def print_pages(pages: str) -> list:
    """
    Find pages to print in console.

    examples:
    print_pages("2,4,9") -> [2, 4, 9]
    print_pages("2,4-7") -> [2, 4, 5, 6, 7]
    print_pages("2-5,7,10-12,17") -> [2, 3, 4, 5, 7, 10, 11, 12, 17]
    print_pages("1,1") -> [1]
    print_pages("") -> []
    print_pages("2,1") -> [1, 2]

    :param pages: string containing page numbers and page ranges to print.
    :return: list of pages to print with no duplicates, sorted in increasing order.
    """
    li = []
    nr = ''
    for i in pages:
        if i != ',':
            nr += i
        else:
            if '-' in nr:
                for s in range(int(nr[:nr.index('-')]), int(nr[nr.index('-') + 1:]) + 1):
                    li.append(s)
            else:
                li.append(int(nr))
            nr = ''
    if nr != '' and '-' in nr:
        for s in range(int(nr[:nr.index('-')]), int(nr[nr.index('-') + 1:]) + 1):
            li.append(s)
    elif nr != '':
        li.append(int(nr))
    li1 = []
    for i in li:
        if i not in li1:
            li1.append(i)
    return sorted(li1)


def sum_time(time1: tuple, time2: tuple) -> tuple:
    """
    Add two times represented as tuples.

    #01

    Both arguments represent time in format (hours, minutes).
    A tuple with two integers. The input is always correct (you don't have to check that).
    0 <= hours <= 23
    0 <= minutes <= 59

    sum_time((0, 10), (0, 20)) => (0, 30)
    sum_time((12, 30), (0, 40)) => (13, 10)
    sum_time((23, 20), (2, 40)) => (2, 0)

    :param time1: tuple with two integers: hours, minutes
    :param time2: tuple with two integers: hours, minutes
    :return: sum of time1, time2; tuple with two integers: hours, minutes
    """
    hr = time1[0] + time2[0]
    min = time1[1] + time2[1]
    hr += min // 60
    min %= 60
    hr %= 24
    return (hr, min)


def double_char(original_string: str) -> str:
    """
    Given a string, return a string where for every char in the original is doubled.

    #02

    double_char("a") => "aa"
    double_char("ab") => "aabb"
    double_char("") => ""

    :param str: string
    :return: string where chars are doubled
    """
    nostr = ''
    for i in original_string:
        nostr += i * 2
    return nostr
