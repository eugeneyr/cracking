"""
Queue via Stacks:

Implement an MyQueue class which implements a queue using two stacks.
"""

import unittest

class MyQueue:
    def __init__(self):
        self.stack1 = []
        self.stack2 = []

    def add(self, val):
        self.stack1.append(val)

    def peek(self):
        if len(self.stack2) == 0:
            while len(self.stack1) > 0:
                self.stack2.append(self.stack1.pop())
        if len(self.stack2) > 0:
            return self.stack2[-1]

    def remove(self):
        if len(self.stack2) == 0:
            while len(self.stack1) > 0:
                self.stack2.append(self.stack1.pop())
        if len(self.stack2) > 0:
            return self.stack2.pop()

    def size(self):
        return len(self.stack1) + len(self.stack2)

    def isEmpty(self):
        return self.size() == 0

class TestMyQueue(unittest.TestCase):
    def test_wholeSeries(self):
        queue = MyQueue()
        for i in range(0, 1000):
            queue.add(i)

        for i in range(0, 1000):
            val = queue.remove()
            self.assertEqual(val, i)

    def test_readsWrites(self):
        queue = MyQueue()
        for i in range(0, 50):
            queue.add(i)

        for i in range(0, 20):
            val = queue.remove()
            self.assertEqual(val, i)

        for i in range(50, 100):
            queue.add(i)

        for i in range(20, 100):
            val = queue.remove()
            self.assertEqual(val, i)
