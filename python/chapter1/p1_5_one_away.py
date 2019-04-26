"""
One Edit Away:

There are three types of edits that can be performed on strings:
insert a character, remove a character, or replace a character.
Given two strings, write a function to check if they are one edit (or zero edits) away.

Author:
    Gayle Laakman, "Cracking the Coding Interview"
Asked by:
    Eugene, HouseCanary, 2016 - 2019

"""

def is_one_edit_away(str1, str2):
    if str1 is None or str2 is None:
        return False
    len1 = len(str1)
    len2 = len(str2)
    if len2 < len1:
        str2, str1 = str1, str2
        len2, len1 = len1, len2
    if len2 - len1 > 1:
        return False

    pos1 = 0
    pos2 = 0
    diffs = 0
    while pos1 < len1:
        if str1[pos1] != str2[pos2]:
            diffs += 1
            if len1 - pos1 != len2 - pos2:
                pos2 += 1
            else:
                pos1 += 1
                pos2 += 1
        else:
            pos1 += 1
            pos2 += 1
        if diffs > 1:
            return False
    return True


# A better structured version
def is_one_edit_away_longer(str1, str2):
    def is_one_edit_apart(str1, str2):
        return len([(c1, c2) for c1, c2 in zip(str1, str2) if c1 != c2]) <= 1

    def is_one_insertion_apart(shorter_s, longer_s):
        for i, c in enumerate(shorter_s):
            if longer_s[i] != c:
                return shorter_s[i:] == longer_s[i + 1:]
        return True

    len1 = len(str1)
    len2 = len(str2)
    if abs(len1 - len2) > 1:
        return False
    elif len1 == len2:
        return is_one_edit_apart(str1, str2)
    elif len1 > len2:
        return is_one_insertion_apart(str2, str1)
    else:
        return is_one_insertion_apart(str1, str2)


import unittest


class TestOneAway(unittest.TestCase):
    def test_is_one_edit_away(self):
        self.assertFalse(is_one_edit_away('test', 'testte'))
        self.assertTrue(is_one_edit_away('test', 'test1'))
        self.assertTrue(is_one_edit_away('test', 'tesx'))
        self.assertTrue(is_one_edit_away('test2', 'test'))
        self.assertTrue(is_one_edit_away('test', 'atest'))
        self.assertFalse(is_one_edit_away('testa', 'atest'))
        self.assertFalse(is_one_edit_away('test', 'xxest'))
        self.assertFalse(is_one_edit_away('test', 'xestx'))
        self.assertTrue(is_one_edit_away('test', 'text'))
        self.assertTrue(is_one_edit_away('teest', 'test'))
        self.assertFalse(is_one_edit_away('xwest', 'test'))
        self.assertFalse(is_one_edit_away('test', 'tesxy'))

    def test_is_one_edit_away_longer(self):
        self.assertFalse(is_one_edit_away_longer('test', 'testte'))
        self.assertTrue(is_one_edit_away_longer('test', 'test1'))
        self.assertTrue(is_one_edit_away_longer('test', 'tesx'))
        self.assertTrue(is_one_edit_away_longer('test2', 'test'))
        self.assertTrue(is_one_edit_away_longer('test', 'atest'))
        self.assertFalse(is_one_edit_away_longer('testa', 'atest'))
        self.assertFalse(is_one_edit_away_longer('test', 'xxest'))
        self.assertFalse(is_one_edit_away_longer('test', 'xestx'))
        self.assertTrue(is_one_edit_away_longer('test', 'text'))
        self.assertTrue(is_one_edit_away_longer('teest', 'test'))
        self.assertFalse(is_one_edit_away_longer('xwest', 'test'))
        self.assertFalse(is_one_edit_away_longer('test', 'tesxy'))

