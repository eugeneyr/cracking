# One Away:

# There are three types of edits that can be performed on strings:
# insert a character, remove a character, or replace a character.
# Given two strings, write a function to check if they are one edit (or zero edits) away.
import fnmatch

def isOneAwayLong(str1, str2):
    def isOneEditAway(str1, str2):
        wasDiff = False
        for (i, c) in enumerate(str1):
            if str2[i] != c:
                if wasDiff:
                    return False
                wasDiff = True
        return True

    def isOneInsertionAway(short, long):
        wasDiff = False
        longIdx = 0
        for (i, c) in enumerate(short):
            if long[longIdx] != c:
                if wasDiff:
                    return False
                wasDiff = True
                longIdx += 1
                if long[longIdx] != c:
                    return False
            longIdx += 1
        return True

    len1 = len(str1)
    len2 = len(str2)
    if abs(len1 - len2) > 1:
        return False
    elif len1 == len2:
        return isOneEditAway(str1, str2)
    elif len1 > len2:
        return isOneInsertionAway(str2, str1)
    else:
        return isOneInsertionAway(str1, str2)

def isOneAway(str1, str2):
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
            if len1 != len2:
                pos2 += 1
        pos1 += 1
        pos2 += 1
        if diffs > 1:
            return False
    return True


import unittest

class TestOneAway(unittest.TestCase):
    def test_isOneAway(self):
        self.assertTrue(isOneAway('test', 'test1'))
        self.assertTrue(isOneAway('test', 'tesx'))
        self.assertTrue(isOneAway('test2', 'test'))
        self.assertTrue(isOneAway('test', 'atest'))
        self.assertFalse(isOneAway('testa', 'atest'))
        self.assertFalse(isOneAway('test', 'xxest'))

    def test_isOneAwayLong(self):
        self.assertTrue(isOneAwayLong('test', 'test1'))
        self.assertTrue(isOneAwayLong('test', 'tesx'))
        self.assertTrue(isOneAwayLong('test2', 'test'))
        self.assertTrue(isOneAwayLong('test', 'atest'))
        self.assertFalse(isOneAwayLong('testa', 'atest'))
        self.assertFalse(isOneAwayLong('test', 'xxest'))


if __name__ == '__main__':
    unittest.main()

