# Implement an algorithm to determine if a string has all unique characters.

def areAllUnique(s):
    if not isinstance(s, str):
        return False
    found = set()
    for c in s:
        if c in found:
            return False
        found.add(c)
    return True


# What if you cannot use additional data structures?
def areAllUniqueOnTheSpot(s):
    if not isinstance(s, str):
        return False

    for i, c in enumerate(s):
        if i > 0:
            if c in s[:i]:
                return False
    return True


# Driver code

import unittest

TEST_DATA = {
    'abcde': True,
    'abca': False,
    '': True,
    'aabc': False,
    'abccccb': False
}


class TestProOneOne(unittest.TestCase):
    def runTest(self, func):
        for s in TEST_DATA:
            print('testing: %s' % s)
            self.assertEqual(func(s), TEST_DATA[s])

    def test_areAllUnique(self):
        self.runTest(areAllUnique)

    def test_areAllUniqueOnTheSpot(self):
        self.runTest(areAllUniqueOnTheSpot)


if __name__ == '__main__':
    unittest.main()
