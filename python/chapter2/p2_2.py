# Return Kth to Last:
# Implement an algorithm to find the kth to last element of a singly linked list.

from python.chapter2.datastructures import *
def deleteKthToLast(node: ListNode, k):
    if node is None or node.next is None or k < 0:
        print('Invalid input')
        return
    print('k =', k)

    counter = 0
    runnerAhead = node
    runnerBehind = node

    while counter <= k and runnerAhead is not None:
        runnerAhead = runnerAhead.next
        counter += 1

    if runnerAhead is None:
        if counter <= k:
            print('The list has less than K elements')
            return
        # else, we need to remove the head
        # print('Deleting as this: ' + str(runnerBehind))
        runnerBehind.deleteThis()
        return node

    while runnerAhead.next is not None:
        runnerAhead = runnerAhead.next
        runnerBehind = runnerBehind.next

    # print('Deleting as next: ' + str(runnerBehind.next))
    runnerBehind.deleteNext()
    return node

if __name__ == '__main__':
    l = ListNode.buildFromIterable([2, 1, 0])
    printLinkedList(l)
    printLinkedList(deleteKthToLast(l, 0))
    print('-----------------------')

    l = ListNode.buildFromIterable([2, 1, 0])
    printLinkedList(l)
    printLinkedList(deleteKthToLast(l, 1))
    print('-----------------------')

    l = ListNode.buildFromIterable([2, 1, 0])
    printLinkedList(l)
    printLinkedList(deleteKthToLast(l, 2))
    print('-----------------------')

    l = ListNode.buildFromIterable([2, 1, 0])
    printLinkedList(l)
    printLinkedList(deleteKthToLast(l, 3))
    print('-----------------------')

    l = ListNode.buildFromIterable([1, 0])
    printLinkedList(l)
    printLinkedList(deleteKthToLast(l, 0))
    print('-----------------------')

    l = ListNode.buildFromIterable([1, 0])
    printLinkedList(l)
    printLinkedList(deleteKthToLast(l, 1))








