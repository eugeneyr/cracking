"""
Intersection:

Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node.
Note that intersection is based on reference, not value. That is, if the kth node of the first linked list
is the exact same node (by reference) as the jth node of the second linked list, then they are intersecting.

"""

import unittest

from python.chapter2.datastructures import *


def findIntersection(head1: ListNode, head2: ListNode):
    if head1 is None or head2 is None:
        return False
    # 1) Find the length and the last node for each list
    def measure(head):
        counter = 1
        node = head
        while node.next is not None:
            node = node.next
            counter += 1
        return counter, node

    length1, tail1 = measure(head1)
    length2, tail2 = measure(head2)

    if tail1 != tail2:
        return None

    # 2) make sure we are starting at the same distance from the end
    diff = length1 - length2
    if diff > 0:
        while diff > 0:
            head1 = head1.next
            diff -= 1
    else:
        while diff < 0:
            head2 = head2.next
            diff += 1
    while head1 != head2:
        head1 = head1.next
        head2 = head2.next

    return head1


class TestIntersections(unittest.TestCase):
    def test_noIntersection(self):
        list1 = ListNode.buildFromIterable('123456789')
        list2 = ListNode.buildFromIterable('abcdefghijklmnop')
        self.assertIsNone(findIntersection(list1, list2))

    def test_intersection(self):
        list1 = ListNode.buildFromIterable('123456789')
        list2 = ListNode.buildFromIterable('abcdefghijklmnop')

        self.assertIsNone(findIntersection(list1, list2))

        node1 = findNode(list1, '6')
        node2 = findNode(list2, 'i')

        self.assertIsNotNone(node1)
        self.assertIsNotNone(node2)
        node1.next = node2

        printLinkedList(list1)
        printLinkedList(list2)

        node = findIntersection(list1, list2)
        self.assertIsNotNone(node)
        printLinkedList(node)


if __name__ == '__main__':
    unittest.main()
