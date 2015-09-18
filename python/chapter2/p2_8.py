"""
Loop Detection:
Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.

DEFINITION

Circular linked list: A (corrupt) linked list in which a node's next pointer points to the earlier node,
so as to make a loop in the linked list

EXAMPLE

Input: A -> B -> C -> D -> E - C [the same C as earlier]
Output: C
"""

import unittest

from python.chapter2.datastructures import *


def findLoop(head: ListNode):
    if head is None:
        return None
    hare = turtle = head

    while True:
        if turtle is None or hare is None:
            return None
        turtle = turtle.next
        hare = hare.next
        if hare is None:
            break
        hare = hare.next
        if turtle == hare:
            break


    # turtle and hare point to a node inside the loop
    # how we need to find that node
    # first, find the "girth" of the loop - the length of the corrupt part
    girth = 0
    while True:
        if hare == head:
            return head
        hare = hare.next
        girth += 1

        if hare == turtle:
            break

    # move head one step at at time and move hare the full cirle

    while True:
        for i in range(0, girth):
            if hare == head:
                return head
            hare = hare.next
        head = head.next

class TestLoopy(unittest.TestCase):
    def test_NoLoop(self):
        l = ListNode.buildFromIterable('abcdefghijklmnop')
        self.assertIsNone(findLoop(l))

    def test_FoundLoop(self):
        l = ListNode.buildFromIterable('abcdefghijklmnop')
        self.assertIsNone(findLoop(l))
        k = findNode(l, 'k')
        p = findNode(l, 'p')
        p.next = k
        found = findLoop(l)
        self.assertEqual(found, k)

if __name__ == '__main__':
    unittest.main()





