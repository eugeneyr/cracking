""" Palindrome Permutation:

Given a string, write a function to check if it is a permutation of a palindrome.
A palindrome is a word or a phrase that is the same forwards and backwards.
A permutation is a rearrangement of letters.
The palindrome does not to be limited to just dictionary words.
(Note: according to the examples in the book space characters should be ignored)

"""

# My analysis:

# To be a permutation of a palindrome, the string has to satisfy the following requirement:
# There is no more than one character for which the number of occurrences in the string is odd.

def is_palindrome_permutation(s):
    counter = {}
    for c in s.lower():
        if c == ' ':
            continue
        counter[c] = (counter.get(c) or 0) + 1
    odds = 0
    for k, v in counter.items():
        if v % 2 == 1:
            odds += 1
        if odds > 1:
            return False
    return True


def is_palindrome_permutation_intermediate(s):
    counter = {}
    for c in s.lower():
        if c == ' ':
            continue
        counter[c] = (counter.get(c) or 0) + 1
    return len([k for k, v in counter.items() if v % 2 == 1]) <= 1

from collections import Counter


def is_palindrome_permutation_advanced(s):
    return len([k for k, v in Counter(s.lower()).items() if k != ' ' and v % 2 == 1]) <= 1


import unittest

TEST_DATA = {
    'abb': True,
    'baobab': False,
    'Tact Coa': True,
    'A man a plan a canal Panama': True,
    'banana': False,
    'not a palindrome': False,
    'adam madam Im': True,
    'abanana': True,
    ' ': True,
    '': True
}


class TestPalindromePermutation(unittest.TestCase):
    def test_isPalindromePermutation(self):
        for s in TEST_DATA:
            self.assertEqual(is_palindrome_permutation(s), TEST_DATA[s])


if __name__ == '__main__':
    unittest.main()
