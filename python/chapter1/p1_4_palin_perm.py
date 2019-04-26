# 1.4 Palindrome Permutation:
# Given a string, write a function to check if it is a permutation of a palindrome.
# A palindrome is a word or a phrase that is the same forwards and backwards.
# A permutation is a rearrangement of letters.
# The palindrome does not to be limited to just dictionary words.
# (Note: according to the examples in the book space characters should be ignored)


# My analysis:

# To be a permutation of a palindrome, the string has to satisfy the following requirement:
# There is no more than one character for which the number of occurrences in the string is odd.

def isPalindromePermutation(s):
    def countAll(c, s):
        return sum(1 for ch in s if ch.lower() == c.lower())

    haveOdd = False
    for c in s:
        if c != ' ':
            count = countAll(c, s)
            if count % 2 == 1:
                if haveOdd:
                    return False
                else:
                    haveOdd = True
    return True


import unittest

TEST_DATA = {
    'Tact Coa': True,
    'A man a plan a canal Panama': True,
    'banana': False,
    'abanana': True,
    ' ': True
}


class TestPalindromePermutation(unittest.TestCase):
    def test_isPalindromePermutation(self):
        for s in TEST_DATA:
            self.assertEqual(isPalindromePermutation(s), TEST_DATA[s])


if __name__ == '__main__':
    unittest.main()
