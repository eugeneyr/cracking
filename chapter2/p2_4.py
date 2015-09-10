# Partition:
# Write code to partition a linked list around a value x, such that all nodes less than x come before
# all nodes greater than or equal to x.
# If x is contained within the list, the values of x only need to be after the elements less than x (see below)
# EXAMPLE:
# Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5]
# Output: 3 -> 1-> 2 -> 10 -> 5 -> 5 -> 8

from chapter2.datastructures import *


def swapValues(node1, node2):
    if node1 is not None and node2 is not None:
        val = node1.value
        node1.value = node2.value
        node2.value = val


# Worst case (the list is split evenly, smaller values are at the end: O(n^2)
def partitionBySwapping(head: ListNode, value):
    node = head
    while node is not None:
        if node.value >= value:
            runner = node.next
            while runner is not None and runner.value >= value:
                runner = runner.next
            if runner is None:
                break
            swapValues(runner, node)
        node = node.next
    return head


def partitionBySplitting(head: ListNode, value):
    smallHead = smallTail = bigHead = bigTail = None
    node = head
    while node is not None:
        nextNode = node.next
        if node.value < value:
            # add to the small ones
            if smallHead is None:
                smallHead = smallTail = node

            else:
                smallTail.next = node
                smallTail = node
            smallTail.next = None
        else:
            # add to the big ones
            if bigHead is None:
                bigHead = bigTail = node

            else:
                bigTail.next = node
                bigTail = node
            bigTail.next = None
        node = nextNode
    # glue partitioned lists
    if smallTail is not None:
        smallTail.next = bigHead
    else:
        smallHead = bigHead

    return smallHead


def testPartition(ls, val):
    l = ListNode.buildFromIterable(ls)
    printLinkedList(l)
    partitioned = partitionBySwapping(l, val)
    printLinkedList(partitioned)
    l = ListNode.buildFromIterable(ls)
    partitioned = partitionBySplitting(l, val)
    printLinkedList(partitioned)


if __name__ == '__main__':
    testPartition([3, 5, 8, 5, 10, 2, 1], 5)
    testPartition([1, 2], 3)
    testPartition([1], 1)
    testPartition([2, 1], 1)
    testPartition([2, 1], 2)

