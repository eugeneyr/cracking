"""
Three in One:
Describe how you could use a single array to implement three stacks.
"""

import unittest
import random


class StackOverflowException(Exception):
    pass


class StackUnderflowException(Exception):
    pass


class TripleStack:
    MAX_SIZE = 1024

    def __init__(self):
        self.storage = [0 for i in range(0, 3 * TripleStack.MAX_SIZE)]
        self.pointers = [0, 1, 2]

    def size(self, stackNo):
        assert stackNo in range(0, 3)
        return int((self.pointers[stackNo] - stackNo) / 3)

    def peek(self, stackNo):
        assert stackNo in range(0, 3)
        if self.size(stackNo) > 0:
            return self.storage[self.pointers[stackNo] - 3]

    def push(self, stackNo, elem):
        assert stackNo in range(0, 3)
        if self.size(stackNo) < TripleStack.MAX_SIZE:
            self.storage[self.pointers[stackNo]] = elem
            self.pointers[stackNo] += 3
        else:
            raise StackOverflowException('Stack {} is at max capacity'.format(stackNo))

    def pop(self, stackNo):
        assert stackNo in range(0, 3)
        if self.size(stackNo) > 0:
            self.pointers[stackNo] -= 3
            rv = self.storage[self.pointers[stackNo]]
            return rv
        else:
            raise StackUnderflowException('Stack {} is empty'.format(stackNo))

    def clear(self, stackNo):
        assert stackNo in range(0, 3)
        self.pointers[stackNo] = 0 + stackNo

    def clearAll(self):
        for stackNo in range(0, 3):
            self.clear(stackNo)


class TestTripleStack(unittest.TestCase):
    def setUp(self):
        self.stack = TripleStack()
        random.seed()

    def test_clear(self):
        self.stack.clearAll()
        for i in range(0, 3):
            self.assertEqual(self.stack.size(i), 0)

    def test_pushPop(self):
        self.stack.clearAll()
        for i in range(0, 3):
            self.assertEqual(self.stack.size(i), 0)

        for i in range(0, 3):
            val = random.randint(-65536, 65535)
            sizeBefore = self.stack.size(i)
            self.stack.push(i, val)
            sizeAfter = self.stack.size(i)
            self.assertEquals(sizeBefore + 1, sizeAfter)
            newVal = self.stack.pop(i)
            self.assertEquals(sizeBefore, self.stack.size(i))
            self.assertEquals(val, newVal)

    def test_peek(self):
        self.stack.clearAll()

        for i in range(0, 3):
            val = random.randint(-65536, 65535)
            sizeBefore = self.stack.size(i)
            self.stack.push(i, val)
            sizeAfter = self.stack.size(i)
            self.assertEquals(sizeBefore + 1, sizeAfter)
            newVal = self.stack.peek(i)
            self.assertEquals(val, newVal)
            self.assertEquals(sizeAfter, self.stack.size(i))

    def test_stackOverflow(self):
        self.stack.clearAll()

        for i in range(0, 3):
            for j in range(0, TripleStack.MAX_SIZE):
                val = random.randint(-65536, 65535)
                self.stack.push(i, val)
            self.assertEquals(self.stack.size(i), TripleStack.MAX_SIZE)
            self.assertRaises(StackOverflowException, lambda: self.stack.push(i, 0))

    def test_stackUnderflow(self):
        self.stack.clearAll()

        for i in range(0, 3):
            self.assertEquals(self.stack.size(i), 0)
            self.assertRaises(StackUnderflowException, lambda: self.stack.pop(i))
