"""Test 1 (K14)."""


def has23(nums):
    """
    Given an int array length 2, return True if it contains a number 2 or a number 3.

    has23([2, 5]) => True
    has23([4, 3]) => True
    has23([4, 25]) => False

    :param nums: list of integers.
    :return: True if the list contains a 2 or a 3.
    """
    return (((2 or 3) in nums) or ((2 and 3) in nums))


def caught_speeding(speed, is_birthday):
    """
    Return which category speeding ticket you would get.

    You are driving a little too fast, and a police officer stops you.
    Write code to compute the result, encoded as an int value:
    0=no ticket, 1=small ticket, 2=big ticket.
    If speed is 60 or less, the result is 0.
    If speed is between 61 and 80 inclusive, the result is 1.
    If speed is 81 or more, the result is 2.
    Unless it is your birthday -- on that day, your speed can be 5 higher in all cases.

    caught_speeding(60, False) => 0
    caught_speeding(65, False) => 1
    caught_speeding(65, True) => 0

    :param speed: Speed value.
    :param is_birthday: Whether it is your birthday (boolean).
    :return: Which category speeding ticket you would get (0, 1, 2).
    """
    if (is_birthday and speed <= 65) or (not is_birthday and speed <= 60):
        return 0
    elif (is_birthday and speed <= 85) or (not is_birthday and speed <= 80):
        return 1
    return 2


def first_half(text):
    """
    Return the first half of an string.

    The length of the string is even.

    first_half('HaaHoo') => 'Haa'
    first_half('HelloThere') => 'Hello'
    first_half('abcdef') => 'abc'
    """
    return text[:(int(len(text) / 2))]


def last_indices_elements_sum(nums):
    """
    Return sum of elements at indices of last two elements.

    Take element at the index of the last element value
    and take element at the index of the previous element value.
    Return the sum of those two elements.

    If the index for an element is out of the list, use 0 instead.

    The list contains at least 2 elements.

    last_indices_elements_sum([0, 1, 2, 0]) => 2 (0 + 2)
    last_indices_elements_sum([0, 1, 1, 7]) => 1 (just 1)
    last_indices_elements_sum([0, 1, 7, 2]) => 7 (just 7)
    last_indices_elements_sum([0, 1, 7, 8]) => 0 (indices too large, 0 + 0)

    :param nums: List of non-negative integers.
    :return: Sum of elements at indices of last two elements.
    """
    sum = 0
    if nums[-1] <= len(nums) and nums[-2] <= len(nums):
        sum += nums[nums[-1]] + nums[nums[-2]]
    elif nums[-1] <= len(nums):
        sum += nums[nums[-1]]
    elif nums[-2] <= len(nums):
        sum += nums[nums[-2]]
    return sum


def max_duplicate(nums):
    """
    Return the largest element which has at least one duplicate.

    If no element has duplicate element (an element with the same value), return None.

    max_duplicate([1, 2, 3]) => None
    max_duplicate([1, 2, 2]) => 2
    max_duplicate([1, 2, 2, 1, 1]) => 2

    :param nums: List of integers
    :return: Maximum element with duplicate. None if no duplicate found.
    """
    nr = 0
    for i in nums:
        if i > nr:
            nr = i
        else:
            continue
    return nr


if __name__ == '__main__':
    print(has23([23, 2]))
    print(caught_speeding(90, True))
    print(first_half("abcd"))
    print(last_indices_elements_sum([2, 9, 1, 2, 6, 9, 9]))
    print(max_duplicate([1, 2, 3, 1, 3, 2, 4]))
