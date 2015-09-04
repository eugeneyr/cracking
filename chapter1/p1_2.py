# Given two strings, write a method to decide is one is a permutation of the other.

def countChars(s):
    result = {}
    if isinstance(s, str):
        for c in s:
            count = result.get(c, 0)
            result[c] = count + 1
    return result


def isPermutation(s1, s2):
    if isinstance(s1, str) and isinstance(s2, str):
        if len(s1) != len(s2):
            return False
        charCount1 = countChars(s1)
        charCount2 = countChars(s2)
        return charCount1 == charCount2

def countChar(s, ch):
    result = 0
    if isinstance(s, str):
        for c in s:
            if c == ch:
                result += 1
    return result

def isPermutationOnTheSpot(s1, s2):
    if isinstance(s1, str) and isinstance(s2, str):
        if len(s1) != len(s2):
            return False

        for c in s1:
            if countChar(s1, c) != countChar(s2, c):
                return False
        return True

# Driver code
import unittest

TEST_DATA = {
    ('', ''): True,
    ('abc', 'bca'): True,
    ('tapatas', 'saaptta'): True,
    ('abc', 'cde'): False,
    ('abc', 'abca'): False,
    ('acbd', 'dcbe'): False
}

class TestProOneTwo(unittest.TestCase):
    def runTest(self, func):
        for pair in TEST_DATA:
            print('testing {}, expect: {}'.format(pair, TEST_DATA[pair]))
            self.assertEqual(func(*pair), TEST_DATA[pair])

    def test_isPermutation(self):
        self.runTest(isPermutation)

    def test_isPermutationOnTheSpot(self):
        self.runTest(isPermutationOnTheSpot)
