# String Rotation:
# Assume you have a methid isSubstring which checks in one word is a substring of another.
# Given two strings, s1 and s2, write code to check is s2 is a rotation of s1 using only one call to isSubstring
# (e.g., "waterbottle" is rotation of "erbottlewat")

def isSubstring(s1:str, s2:str):
    return s1.find(s2) >= 0

def isRotation(s1, s2):
    return len(s2) == len(s1) and isSubstring(s1 * 2, s2)

import unittest

class TestRotations(unittest.TestCase):
    def test_positive(self):
        self.assertTrue(isRotation('waterbottle', 'erbottlewat'))

    def test_negative(self):
        self.assertFalse(isRotation('water', 'rewat'))






