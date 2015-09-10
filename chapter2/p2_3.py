# Delete Middle Node:
# Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node.
# EXAMPLE
# Input: the node c from the linked list a->b->c->d->e
# Result: nothing is returned, but the linked list looks like a->b->d->e

from chapter2.datastructures import *


def deleteFromTheMiddle(node: ListNode):
    if node is None:
        print('node is empty')
        return
    if node.next is None:
        print('No can do, I am the last node')
    else:
        node.deleteThis()


if __name__ == '__main__':
    head = ListNode.buildFromIterable([0, 1, 2, 3, 4])
    printLinkedList(head)
    midNode = findNode(head, 2)
    deleteFromTheMiddle(midNode)
    printLinkedList(head)
