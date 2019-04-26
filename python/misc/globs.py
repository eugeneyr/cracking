"""
A glob pattern is a string expression that specifies a set of matching strings with wildcard characters.
For example, the Unix Bash shell command mv *.txt textfiles/ moves (mv) all files with names ending in .txt from the current directory
 to the directory textfiles. Here, * is a wildcard standing for "any string of characters" (including an empty string) and *.txt is a glob pattern.
 The other common wildcard is the question mark (?), which stands for one character.

Write a function glob_match(p, s) that implements matching glob patterns against strings.
Its arguments are a glob pattern p ans a string s. The output is a boolean value: True if s matches p, False otherwise.

"""

import unittest

def glob_match(p, s):
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


if __name__ == '__main__':
    unittest.main()
