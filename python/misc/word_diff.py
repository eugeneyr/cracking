# One Away:

# There are three types of edits that can be performed on strings:
# insert a character, remove a character, or replace a character.
# Given two strings, write a function to check if they are one edit (or zero edits) away.

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
            pos2 += 1
            if len1 == len2:
                pos1 += 1
        else:
            pos1 += 1
            pos2 += 1
        if diffs > 1:
            return False
    return True

def is_one_edit_away1(str1, str2):
    if str1 is None or str2 is None:
        return False
    len1 = len(str1)
    len2 = len(str2)
    if len2 < len1:
        str2, str1 = str1, str2
        len2, len1 = len1, len2
    if len2 - len1 > 1:
        return False

    pos2 = 0
    diffs = 0
    for pos1, c in enumerate(str1):
        if str1[pos1] != str2[pos2]:
            diffs += 1
            if len1 != len2:
                pos2 += 1
        if diffs > 1:
            return False
        pos2 += 1

    return True



import unittest

class TestOneAway(unittest.TestCase):
    def test_is_one_edit_away(self):
        self.assertTrue(is_one_edit_away('test', 'test1'))
        self.assertTrue(is_one_edit_away('test', 'tesx'))
        self.assertTrue(is_one_edit_away('test2', 'test'))
        self.assertTrue(is_one_edit_away('test', 'atest'))
        self.assertFalse(is_one_edit_away('testa', 'atest'))
        self.assertFalse(is_one_edit_away('test', 'xxest'))
        self.assertTrue(is_one_edit_away('test', 'text'))
        self.assertFalse(is_one_edit_away('test', 'tesxy'))

    def test_is_one_edit_away1(self):
        self.assertTrue(is_one_edit_away1('test', 'test1'))
        self.assertTrue(is_one_edit_away1('test', 'tesx'))
        self.assertTrue(is_one_edit_away1('test2', 'test'))
        self.assertTrue(is_one_edit_away1('test', 'atest'))
        self.assertFalse(is_one_edit_away1('testa', 'atest'))
        self.assertFalse(is_one_edit_away1('test', 'xxest'))
        self.assertTrue(is_one_edit_away1('test', 'text'))
        self.assertFalse(is_one_edit_away1('test', 'tesxy'))


if __name__ == '__main__':
    unittest.main()

