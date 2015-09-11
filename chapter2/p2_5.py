# Sum Lists
"""
You have two numbers represented by a linked list, where each node contains a single digit.
The digits ae stored in reverse order, such that the 1's digit is at the head of the list.
Write a function that adds the two numbers and returns the sum as a linked list.

EXAMPLE:

Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295
Output: 2 -> 1 -> 9. That is, 912

FOLLOW UP

Suppose the digits are stored in forward order. Repeat the above problem.

EXAMPLE

Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295
Output: 9 -> 1 -> 2. That is, 912
"""

from chapter2.datastructures import *


def addNumbersAsLists(head1: ListNode, head2: ListNode):
    if head1 is None or head2 is None:
        return None

    res_head = res_curr = None
    curr1, curr2 = head1, head2
    carryover = 0
    while curr1 is not None and curr2 is not None:
        sum = curr1.value + curr2.value + carryover
        new_value = sum % 10
        carryover = int(sum / 10)
        new_node = ListNode(new_value)

        if res_head is None:
            res_curr = res_head = new_node
        else:
            res_curr.next = new_node
            res_curr = res_curr.next

        curr1 = curr1.next
        curr2 = curr2.next

    # there is a chance one of the lists is longer than the other. Complete addition, if necessary

    curr = curr1 if curr1 is not None else curr2

    if curr is not None:
        while curr is not None:
            sum = curr.value + carryover
            new_value = sum % 10
            carryover = int(sum / 10)
            new_node = ListNode(new_value)

            res_curr.next = new_node
            res_curr = res_curr.next
            curr = curr.next

    if carryover > 0:
        res_curr.next = ListNode(carryover)

    return res_head


if __name__ == '__main__':
    num1 = ListNode.buildFromIterable([1])
    num2 = ListNode.buildFromIterable([9, 9, 9])
    printLinkedList(addNumbersAsLists(num1, num2))

    num1 = ListNode.buildFromIterable([7, 1, 6])
    num2 = ListNode.buildFromIterable([5, 9, 2])
    printLinkedList(addNumbersAsLists(num1, num2))

    num1 = ListNode.buildFromIterable([7, 1, 6])
    num2 = ListNode.buildFromIterable([5, 9, 8])
    printLinkedList(addNumbersAsLists(num1, num2))











