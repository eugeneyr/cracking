"""
Stack via Queues:

Implement an MyStack class which implements a stack using two queues.
"""

import unittest


class MyStack:
    def __init__(self):
        self.queue1 = []
        self.queue2 = []

    def clear(self):
        self.queue1.clear()
        self.queue2.clear()

    def push(self, elem):
        self.queue1.append(elem)

    def pop(self):
        rv = None
        while len(self.queue1) > 0:
            rv = self.queue1.pop(0)
            if len(self.queue1) > 0:
                self.queue2.append(rv)
        tmp = self.queue2
        self.queue2 = self.queue1
        self.queue1 = tmp
        return rv

    def peek(self):
        rv = None
        while len(self.queue1) > 0:
            rv = self.queue1.pop(0)
            self.queue2.append(rv)
        tmp = self.queue2
        self.queue2 = self.queue1
        self.queue1 = tmp
        return rv

    def size(self):
        return len(self.queue1) + len(self.queue2)


class TestMyStack(unittest.TestCase):
    def test_pushPop(self):
        stack = MyStack()
        for i in range(0, 100):
            stack.push(i)
            self.assertEqual(i, stack.peek())
        self.assertEquals(stack.size(), 100)
        self.assertEquals(stack.peek(), 99)
        for i in range(0, 100):
            val = stack.pop()
            self.assertEquals(val, 99 - i)
        self.assertEquals(stack.size(), 0)
        self.assertIsNone(stack.peek())
