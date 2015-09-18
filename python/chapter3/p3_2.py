"""
Stack Min:
How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element?
Push, pop and min should all operate in O(1) time.
"""

import random


class StackWithMin:
    def __init__(self):
        self.data = []
        self.mins = []

    def size(self):
        return len(self.data)

    def push(self, elem):
        self.data.append(elem)
        if len(self.mins) == 0 or self.mins[-1] > elem:
            self.mins.append(elem)
        else:
            self.mins.append(self.mins[-1])

    def pop(self):
        if len(self.data) > 0:
            self.mins.pop()
            return self.data.pop()

    def peek(self):
        if len(self.data) > 0:
            return self.data[-1]

    def min(self):
        if len(self.mins) > 0:
            return self.mins[-1]

    def clear(self):
        self.data.clear()
        self.mins.clear()


import unittest


class TestMinStack(unittest.TestCase):
    def setUp(self):
        self.stack = StackWithMin()
        random.seed()

    def test_clear(self):
        self.stack.clear()
        self.assertEqual(self.stack.size(), 0)

    def test_pushPop(self):
        self.stack.clear()
        self.assertEqual(self.stack.size(), 0)

        val = random.randint(-65536, 65535)
        sizeBefore = self.stack.size()
        self.stack.push(val)
        sizeAfter = self.stack.size()
        self.assertEquals(sizeBefore + 1, sizeAfter)
        newVal = self.stack.pop()
        self.assertEquals(sizeBefore, self.stack.size())
        self.assertEquals(val, newVal)

    def test_peek(self):
        self.stack.clear()
        val = random.randint(-65536, 65535)
        sizeBefore = self.stack.size()
        self.stack.push(val)
        sizeAfter = self.stack.size()
        self.assertEquals(sizeBefore + 1, sizeAfter)
        newVal = self.stack.peek()
        self.assertEquals(val, newVal)
        self.assertEquals(sizeAfter, self.stack.size())

    def test_stackUnderflow(self):
        self.stack.clear()
        self.assertEquals(self.stack.size(), 0)
        self.assertIsNone(self.stack.pop())

    def test_min(self):
        self.stack.clear()
        self.assertEqual(self.stack.size(), 0)

        data = [3, 3, 2, 2, 1, 2, 1, 2, 1, 1]
        mins = [3, 3, 2, 2, 1, 1, 1, 1, 1, 1]

        for i, val in enumerate(data):
            self.stack.push(val)
            self.assertEquals(self.stack.peek(), val)
            self.assertEquals(self.stack.min(), mins[i])

        while self.stack.size() > 0:
            self.assertEquals(mins.pop(), self.stack.min())
            self.assertEquals(data.pop(), self.stack.pop())
