"""
Stack of Plates:

Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold.
Implement a data structure SetOfStacks that mimics this.
SetOfStacks should be composed pf several stacks and should create a new stack once the previous one exceeds capacity.
SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack (that is, pop() should
return the same values as it would if there were just a single stack.

FOLLOW UP

Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
"""

import unittest


class SetOfStacks:
    def __init__(self, maxStackSize):
        self.maxStackSize = maxStackSize
        self.stacks = []
        self.currSize = 0

    def clear(self):
        self.stacks = []
        self.currSize = 0

    def push(self, elem):
        if len(self.stacks) == 0 or len(self.stacks[-1]) == self.maxStackSize:
            self.stacks.append([])
        self.stacks[-1].append(elem)
        self.currSize += 1

    def pop(self):
        if len(self.stacks) > 0 and len(self.stacks[-1]) > 0:
            val = self.stacks[-1].pop()
            if len(self.stacks[-1]) == 0:
                self.stacks.pop()
            self.currSize -= 1
            return val

    def peek(self):
        if len(self.stacks) > 0 and len(self.stacks[-1]) > 0:
            return self.stacks[-1][-1]

    def size(self):
        return self.currSize

    def popAt(self, index):
        if index in range(0, len(self.stacks)):
            stack = self.stacks[index]
            val = stack.pop()
            if len(stack) == 0:
                self.stacks.pop(index)
            self.currSize -= 1
            return val

    def noOfStacks(self):
        return len(self.stacks)


class TestSetOfStacks(unittest.TestCase):
    def test_pushPop(self):
        sos = SetOfStacks(10)
        for i in range(0, 100):
            sos.push(i)
        self.assertEquals(sos.size(), 100)
        self.assertEquals(sos.noOfStacks(), 10)
        self.assertEquals(sos.peek(), 99)
        for i in range(0, 100):
            val = sos.pop()
            self.assertEquals(val, 99 - i)
        self.assertEquals(sos.size(), 0)
        self.assertEquals(sos.noOfStacks(), 0)
        self.assertIsNone(sos.peek())

    def test_popAt(self):
        sos = SetOfStacks(10)
        for i in range(0, 100):
            sos.push(i)
        self.assertEquals(sos.size(), 100)
        self.assertEquals(sos.noOfStacks(), 10)
        self.assertEquals(sos.peek(), 99)
        for i in range(0, 10):
            for j in range(0, 10):
                val = sos.popAt(0)
                self.assertEquals(val, 10 * i + (9 - j))
        self.assertEquals(sos.size(), 0)
        self.assertEquals(sos.noOfStacks(), 0)
        self.assertIsNone(sos.peek())
