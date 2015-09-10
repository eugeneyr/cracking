# Remove Dups:
# Write code to remove duplicates from an unsorted linked list.
# FOLLOW UP:
# How would you solve this problem if a temporary buffer is not allowed?

from chapter2.datastructures import ListNode, printLinkedList


def removeDupsInPlace(node: ListNode):
    currNode = node
    while currNode is not None:
        runner = currNode
        while runner is not None:
            if runner.next is not None and currNode.value == runner.next.value:
                print('Deleting: ' + str(runner.next))
                runner.deleteNext()
            else:
                runner = runner.next
        currNode = currNode.next
    return node


def removeDupsWithTempStorage(node: ListNode):
    values = set()
    currNode = node
    while currNode is not None:
        values.add(currNode.value)
        if currNode.next is not None and currNode.next.value in values:
            currNode.deleteNext()
        else:
            currNode = currNode.next
    return node


if __name__ == '__main__':
    l1 = ListNode.buildFromIterable([1, 2, 3, 1, 4, 5, 6, 1])
    printLinkedList(l1)
    printLinkedList(removeDupsInPlace(l1))
    l1 = ListNode.buildFromIterable([])
    printLinkedList(removeDupsInPlace(l1))
    l1 = ListNode.buildFromIterable([1, 1, 1, 1, 1, 1, 1, 1])
    printLinkedList(removeDupsInPlace(l1))
    l1 = ListNode.buildFromIterable([1, 2, 3, 1, 4, 2, 5, 6, 1, 1])
    printLinkedList(removeDupsInPlace(l1))

    l1 = ListNode.buildFromIterable([1, 2, 3, 1, 4, 5, 6, 1])
    printLinkedList(l1)
    printLinkedList(removeDupsWithTempStorage(l1))
    l1 = ListNode.buildFromIterable([])
    printLinkedList(removeDupsWithTempStorage(l1))
    l1 = ListNode.buildFromIterable([1, 1, 1, 1, 1, 1, 1, 1])
    printLinkedList(removeDupsWithTempStorage(l1))
    l1 = ListNode.buildFromIterable([1, 2, 3, 1, 4, 2, 5, 6, 1, 1])
    printLinkedList(removeDupsWithTempStorage(l1))

