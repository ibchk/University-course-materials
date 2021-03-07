"""Program that creates beautiful pyramids."""
from math import ceil


def make_pyramid(base: int, char: str) -> list:
    """
    Construct a pyramid with given base.

    Pyramid should consist of given chars, all empty spaces in the pyramid list are ' '. Pyramid height depends on base length. Lowest floor consists of base-number chars.
    Every floor has 2 chars less than the floor lower to it.
    make_pyramid(3, "A") ->
    [
        [' ', 'A', ' '],
        ['A', 'A', 'A']
    ]
    make_pyramid(6, 'a') ->
    [
        [' ', ' ', 'a', 'a', ' ', ' '],
        [' ', 'a', 'a', 'a', 'a', ' '],
        ['a', 'a', 'a', 'a', 'a', 'a']
    ]
    :param base: int
    :param char: str
    :return: list
    """
    pyramid = [[char if base - i > j >= i else ' ' for j in range(base)] for i in range(ceil(base / 2))]
    return pyramid[::-1]


def join_pyramids(pyramid_a: list, pyramid_b: list) -> list:
    """
    Join together two pyramid lists.

    Get 2 pyramid lists as inputs. Join them together horizontally. If the the pyramid heights are not equal, add empty lines on the top until they are equal.
    join_pyramids(make_pyramid(3, "A"), make_pyramid(6, 'a')) ->
    [
        [' ', ' ', ' ', ' ', ' ', 'a', 'a', ' ', ' '],
        [' ', 'A', ' ', ' ', 'a', 'a', 'a', 'a', ' '],
        ['A', 'A', 'A', 'a', 'a', 'a', 'a', 'a', 'a']
    ]

    :param pyramid_a: list
    :param pyramid_b: list
    :return: list
    """
    pyramid_b = pyramid_b[::-1]
    pyramid_a = pyramid_a[::-1]
    if len(pyramid_a) - len(pyramid_b) > 0:
        b = [[' ' for i in range(len(pyramid_b[0]))] for i in range(len(pyramid_a) - len(pyramid_b))]
        pyramid_b += b
        pyramid = [pyramid_a[i] + pyramid_b[i] for i in range(len(pyramid_a))]
    else:
        a = [[' ' for i in range(len(pyramid_a[0]))] for i in range(len(pyramid_b) - len(pyramid_a))]
        pyramid_a += a
        pyramid = [pyramid_a[i] + pyramid_b[i] for i in range(len(pyramid_a))]
    return pyramid[::-1]


def to_string(pyramid: list) -> str:
    """
    Return pyramid list as a single string.

    Join pyramid list together into a string and return it.

    to_string(make_pyramid(3, 'A')) ->
    '''
     A
    AAA
    '''

    :param pyramid: list
    :return: str
    """
    return ''.join([''.join(i) + '\n' for i in pyramid])[:-1]


if __name__ == '__main__':
    pyramid_a = make_pyramid(3, "A")
    print(pyramid_a)  # ->
    """
    [
        [' ', 'A', ' '],
        ['A', 'A', 'A']
    ]
    """

    pyramid_b = make_pyramid(8, 'a')
    print(pyramid_b)  # ->
    """
    [
        [' ', ' ', 'a', 'a', ' ', ' '],
        [' ', 'a', 'a', 'a', 'a', ' '],
        ['a', 'a', 'a', 'a', 'a', 'a']
    ]
    """

    joined = join_pyramids(pyramid_a, pyramid_b)
    print(joined)  # ->
