# String Compression:
# Implement a method to perform basic string compression using the counts of repeated characters.
# For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller
# than the original string, your method should return the original string.
# You can assume the string has only uppercase and lowercase letters (a-z).

import re

def compressString(s):
    if s is None:
        return None
    rv = ''
    prev = None
    count = 0
    for c in s:
        if c == prev:
            count += 1
        else:
            if prev is not None:
                rv += '{}{}'.format(prev, count)
                if len(rv) > len(s):
                    return s
            count = 1
            prev = c
    # One more time, on exit form the loop
    if prev is not None:
        rv += '{}{}'.format(prev, count)
        if len(rv) > len(s):
            return s
    return rv

def decompressString(s):
    if s is None:
        return None
    currC = None
    currStrCount = ''
    rv = ''
    for c in s:
        if re.match(r'\d', c) is None:
            if currC is not None:
                count = 1 if currStrCount == '' else int(currStrCount)
                rv += currC * count
            currC = c
            currStrCount = ''
        else:
            currStrCount += c
    # one more time, on the loop end - there is an unprocessed data we just accumulated
    if currC is not None:
        count = 1 if currStrCount == '' else int(currStrCount)
        rv += currC * count
    return rv

# Driver code

import unittest

class TestCompress(unittest.TestCase):
    def test_compress(self):
        s = compressString('aabcccccaaa')
        print(s)
        self.assertEqual(s, 'a2b1c5a3')

    def test_decompress(self):
        s = decompressString('a2b1c5a3')
        print(s)
        self.assertEqual(s, 'aabcccccaaa')

if __name__ == '__main__':
    unittest.main()
