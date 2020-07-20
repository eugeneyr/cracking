""" Palindrome Permutation:

Given a string, write a function to check if it is a permutation of a palindrome.
The function should be case-insensitive. Space characters should be ignored.
A palindrome is a word or a phrase that is the same forwards and backwards.
A permutation is a rearrangement of letters.
The palindrome does not to be limited to just dictionary words.

"""


def is_palindrome_permutation(s):
    return True


import unittest

TEST_DATA = {
    '': True,
    'aa': True,
    'abb': True,
    ' ': True,
    'baobab': False,
    'Tact Coa': True,
    'A man a plan a canal Panama': True,
    'banana': False,
    'abanana': True,
    'not a palindrome': False,
    'adam madam Im': True,
}


class TestPalindromePermutation(unittest.TestCase):
    def test_isPalindromePermutation(self):
        for s in TEST_DATA:
            self.assertEqual(is_palindrome_permutation(s), TEST_DATA[s])


def is_palindrome_permutation_basic(s):
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


"""
A glob pattern is a string expression that specifies a set of matching strings with wildcard characters.
For example, the Unix Bash shell command mv *.txt textfiles/ moves (mv) all files with names ending in .txt from the 
current directory
 to the directory textfiles. Here, * is a wildcard standing for "any string of characters" (including an empty 
 string) and *.txt is a glob pattern.
 The other common wildcard is the question mark (?), which stands for one character.

Write a function glob_match(p, s) that implements matching glob patterns against strings.
Supported special characters: * matches any string of characters, ? matches any single character.

Its arguments are a glob pattern p ans a string s. The output is a boolean value: True if s matches p, False otherwise.

"""

import unittest


def glob_match(p, s):
    return True


class GlobMatcherTest(unittest.TestCase):
    def test_match_equal(self):
        self.assertTrue(glob_match('abcd', 'abcd'))

    def test_match_unequal_diff_length(self):
        self.assertFalse(glob_match('abcde', 'abcd'))

    def test_match_unequal_same_length(self):
        self.assertFalse(glob_match('abcd', 'abxd'))

    def test_match_empty(self):
        self.assertTrue(glob_match('', ''))

    def test_match_empty_pattern_negative(self):
        self.assertFalse(glob_match('', 'abc'))

    def test_match_empty_string_negative(self):
        self.assertFalse(glob_match('abc', ''))

    def test_match_empty_string_star(self):
        self.assertTrue(glob_match('*', ''))

    def test_match_empty_string_multiple_stars(self):
        self.assertTrue(glob_match('***', ''))

    def test_match_empty_string_question_negative(self):
        self.assertFalse(glob_match('?', ''))

    def test_match_endibg_question_mark(self):
        self.assertTrue(glob_match('ab?', 'abc'))

    def test_match_two_question_marks_negative(self):
        self.assertFalse(glob_match('ab??', 'abc'))

    def test_match_question_mark_in_the_middle(self):
        self.assertTrue(glob_match('ab?def', 'abcdef'))

    def test_mixed_special_characters(self):
        self.assertTrue(glob_match('a?*f', 'abcdef'))

    def test_match_diff_ending_negative(self):
        self.assertFalse(glob_match('ab*f', 'abcde'))

    def test_match_multiple_star_prefix(self):
        self.assertTrue(glob_match('**xyz', 'abcdepqrxyz'))

    def test_star_prefix_suffix(self):
        self.assertTrue(glob_match('*f*', 'abfer'))

    def test_question_mark_prefix_suffix(self):
        self.assertTrue(glob_match('?bcdef?', 'abcdefg'))

    def test_question_mark_suffix_mismatch(self):
        self.assertFalse(glob_match('?bcdef?', 'abcdef'))


def glob_match_solution(p, s):
    if p is None or s is None:
        return False
    if not s:
        return not p or (p[0] == '*' and glob_match(p[1:], s))
    if not p:
        return False
    if p[0] == '?':
        return glob_match(p[1:], s[1:])
    elif p[0] == '*':
        return (glob_match(p[1:], s) or glob_match(p, s[1:]) or glob_match(p[1:], s[1:]))
    else:
        return p[0] == s[0] and glob_match(p[1:], s[1:])

print(''.join(['a', 'b', 'c']))

if __name__ == '__main__':
    unittest.main()
