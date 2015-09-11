"""
Palindrome:
Implement a function to check if a linked list is a palindrome.
"""

from chapter2.datastructures import *


def isPalindrome(node: ListNode):
    def recurseAndCheck(node: ListNode, head: ListNode):
        if node is None:
            return False
        if node.next is None:
            otherNode = head
        else:
            otherNode = recurseAndCheck(node.next, head)
        if isinstance(otherNode, ListNode):
            if not otherNode.value == node.value:
                return False
            if otherNode == node or node.next == otherNode:
                #print('hit the middle:', node.value)
                return True
            return otherNode.next
        return otherNode

    return recurseAndCheck(node, node)


if __name__ == '__main__':
    l = ListNode.buildFromIterable('abc')
    printLinkedList(l)
    print(isPalindrome(l))

    print(isPalindrome(ListNode.buildFromIterable('abba')))
    print(isPalindrome(ListNode.buildFromIterable('ababa')))
    print(isPalindrome(ListNode.buildFromIterable('abcba')))
    print(isPalindrome(ListNode.buildFromIterable('amanaplanacanalpanama')))
    print(isPalindrome(ListNode.buildFromIterable('aaba')))
    print(isPalindrome(ListNode.buildFromIterable('aman')))
    print(isPalindrome(ListNode.buildFromIterable('amanaplanacanalpanamaa')))
