"""
A list (or an array) of the length N contains numbers in the range from 1 to N.
Write a function that returns the first number that has duplicates in the list, or -1 if there are no duplicates.
Space complexity should be O(1), time complexity O(N).
"""
import unittest


def find_first_duplicate(a):
    for i in range(0, len(a)):
        print(a, i)
        while a[i] != i + 1:
            if a[i] == a[a[i] - 1]:
                print('Found:', a[i])
                return a[i]
            tmp = a[i]
            a[i] = a[a[i] - 1]
            a[tmp - 1] = tmp
            print(a)
        # else:
        #     print(a)
    print('Not found')
    return -1


class TestDuplicateFinder(unittest.TestCase):
    # def test_no_duplicates(self):
    #     self.assertEqual(-1, find_first_duplicate([3, 4, 1, 2]))
    #
    # def test_one_duplicate(self):
    #     self.assertEqual(3, find_first_duplicate([4, 3, 2, 3]))
    #
    # def test_two_duplicates(self):
    #     self.assertEqual(3, find_first_duplicate([1, 3, 2, 3, 4, 2]))
    #
    # def test_tails(self):
    #     self.assertEqual(6, find_first_duplicate([6, 3, 6, 2, 5, 2]))
    #
    def test_my_case(self):
        self.assertEqual(3, find_first_duplicate([5, 3, 2, 3, 2, 1, 4]))
