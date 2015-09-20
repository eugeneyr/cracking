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

    def getMin(self):
        if len(self.mins) > 0:
            return self.mins[-1]

    def clear(self):
        self.data.clear()
        self.mins.clear()


class StackWithMinArithm:
    def __init__(self):
        self.data = []
        self.min = None

    def size(self):
        return len(self.data)

    def push(self, elem):
        if self.min is None:
            self.min = elem
        elif self.min > elem:
            newMin = elem
            elem = newMin * 2 - self.min
            self.min = newMin
        self.data.append(elem)

    def pop(self):
        if len(self.data) > 0:
            elem = self.data.pop()
            if elem < self.min:
                newMin = 2 * self.min - elem
                elem = self.min
                self.min = newMin
            return elem

    def peek(self):
        if len(self.data) > 0:
            elem = self.data[-1]
            if elem < self.min:
                elem = self.min
            return elem

    def getMin(self):
        return self.min

    def clear(self):
        self.data.clear()
        self.min = None


import unittest


class TestMinStack(unittest.TestCase):
    def setUp(self):
        self.stack = StackWithMinArithm()
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
            self.assertEquals(self.stack.getMin(), mins[i])

        while self.stack.size() > 0:
            self.assertEquals(mins.pop(), self.stack.getMin())
            self.assertEquals(data.pop(), self.stack.pop())


if __name__ == '__main__':
    unittest.main()
