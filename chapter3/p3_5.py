"""
Sort Stack:

Write a program to sort a stack such that the smallest items are on the top.
You can use an additional temporary stack, but you may not copy the elements into any other data structure
(such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.
"""

class Stack:
    def __init__(self):
        self.data = []

    def push(self, x):
        self.data.append(x)

    def pop(self):
        return self.data.pop()

    def peek(self):
        return self.data[-1]

    def isEmpty(self):
        return len(self.data) == 0

def sortStack(stack: Stack):
    tmpStack = Stack()
    size = 0
    haveSize = False
    sorted = 0
    while True:
        count = 0
        maxVal = None
        while ((not haveSize) or (count + sorted < size)) and not stack.isEmpty():
            val = stack.pop()
            if not haveSize:
                size += 1
            else:
                count += 1
            if maxVal is not None:
                if val > maxVal:
                    tmpStack.push(maxVal)
                    maxVal = val
                else:
                    tmpStack.push(val)
            else:
                maxVal = val
        if not haveSize:
            haveSize = True
        sorted += 1
        if maxVal is not None:
            stack.push(maxVal)
        while not tmpStack.isEmpty():
            stack.push(tmpStack.pop())
        if sorted == size:
            break

import unittest
import random

random.seed()

class TestSortStack(unittest.TestCase):
    def test_sortStack(self):
        data = [x for x in range(0, 100)]
        data.extend(range(0, 100))
        random.shuffle(data)
        stack = Stack()
        for i in data:
            stack.push(i)
        data.sort()
        sortStack(stack)
        while not stack.isEmpty():
            x = stack.pop()
            y = data.pop(0)
            self.assertEqual(x, y)
        self.assertTrue(stack.isEmpty())
        self.assertEqual(len(data), 0)
