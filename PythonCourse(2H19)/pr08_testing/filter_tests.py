"""My first tests."""
import filter
import copy
import random


def test_remove_vowels():
    """Test remove_vowels() function."""
    assert filter.remove_vowels('') == ''
    assert filter.remove_vowels('abba') == 'bb'
    assert filter.remove_vowels('You are The smAllest PeOPLE in thE World') == 'Y r Th smllst PPL n th Wrld'
    assert filter.remove_vowels('a') == ''
    assert filter.remove_vowels('e') == ''
    assert filter.remove_vowels('i') == ''
    assert filter.remove_vowels('o') == ''
    assert filter.remove_vowels('u') == ''
    assert filter.remove_vowels('AAEFDFGTRF') == 'FDFGTRF'
    assert filter.remove_vowels('II') == ''
    assert filter.remove_vowels('qwerty') == 'qwrty'
    assert filter.remove_vowels('AaEeIiOoUu') == ''
    assert filter.remove_vowels('RrBBbGhKLJ') == 'RrBBbGhKLJ'
    assert filter.remove_vowels('abcdefghijklmnopqrstuvwxyz') == 'bcdfghjklmnpqrstvwxyz'


def test_longest_filtered_word():
    """Test longest_filtered_word() function."""
    assert filter.longest_filtered_word(["Bunny", "Tiger", "Bear", "Snake"]) == 'Bnny'
    assert filter.longest_filtered_word(['a', 'a', 'b', 'v']) == 'b'
    assert filter.longest_filtered_word(['a', 'a', 'b', 'aa']) == 'b'
    assert filter.longest_filtered_word(['']) == ''
    assert filter.longest_filtered_word([]) is None


a = ['Bnny', 'Tgr', 'Snk', 'Br']
b = copy.deepcopy(a)


def test_sort_list():
    """Test sort_list() function."""
    assert filter.sort_list(["Bunny", "Tiger", "Bear", "Snake"]) == ['Bnny', 'Tgr', 'Snk', 'Br']
    assert filter.sort_list(["Bueenny", "Tigggger", "Bear", "Snaaioioaiaioke"]) == ['Tggggr', 'Bnny', 'Snk', 'Br']
    assert filter.sort_list([]) == []
    assert filter.sort_list(['Molly']) == ['Mlly']
    assert filter.sort_list(b) == b


def __one_vowel():
    """Give one letter."""
    list1 = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
             'v', 'w', 'x', 'y', 'z']
    return random.choice(list1)


def test_random_remove_vowels():
    """Remove random vowel."""
    result = __one_vowel()
    assert filter.remove_vowels(result) == result if result != ('a' or 'e' or 'i' or 'o' or 'u') else ''
