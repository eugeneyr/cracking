# URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string
# has sufficient space at the end to hold the additional characters, and that you are given the 'true'
# length of the string. (Note: if implementing in Java, please use a character array so that you can
# perform the operation in place)

import array


def urlify(arr, maxLength):
    def countSpaces(arr):
        return sum(1 for c in arr if c == ' ')

    noOfSpaces = countSpaces(arr)
    if noOfSpaces > 0:
        strLen = arr.index('\0') if '\0' in arr else maxLength
        if strLen >= maxLength:
            raise ValueError('The null terminator is outside the array boundaries (???)')
        if strLen + noOfSpaces * 2 > maxLength:
            raise ValueError('Not enough free space in the array (%d, %s)' % (maxLength, arr), )
        fromIdx = strLen - 1
        toIdx = strLen + noOfSpaces * 2 - 1
        while fromIdx >= 0:
            if arr[fromIdx] == ' ':
                # we would use strcpn() here, if it were C
                arr[toIdx] = '0'
                toIdx -= 1
                arr[toIdx] = '2'
                toIdx -= 1
                arr[toIdx] = '%'
            else:
                arr[toIdx] = arr[fromIdx]
            toIdx -= 1
            fromIdx -= 1
    return arr


# Driver code:
import unittest

TEST_DATA = [('http url', 15, 'http%20url'),
             (' http url', 15, '%20http%20url'),
             (' http url ', 15, ValueError),
             (' http url ', 25, '%20http%20url%20'),
             (' http    url\0', 6, ValueError),
             (' http    url ', 25, '%20http%20%20%20%20url%20')]


class TestUrlify(unittest.TestCase):
    def test_urlify(self):
        def to_array(s):
            # Imitate C-style strings
            arr = array.array('u', '\0' * 40)
            arr[0:len(s)] = array.array('u', s)
            return arr

        def from_c_str_array(arr):
            s = ''
            for c in arr:
                if c != '\0':
                    s += c
                else:
                    break
            return s

        for vals in TEST_DATA:
            arr = to_array(vals[0])
            if vals[2] == ValueError:
                self.assertRaises(vals[2], urlify, arr, vals[1])
            else:
                arr = urlify(arr, vals[1])
                res = from_c_str_array(arr)
                print('%s => %s' % (vals[0], res))
                self.assertEqual(res, vals[2])
