"""
Check Balanced:

Implement a function to check if a binary tree is balanced.
For the purpose of this question, a balanced tree is defined to be a tree such that the heights of the two subtrees
of any node never differ by more than one.
"""

from chapter4.datastructures import TreeNode, printTree, buildMinBSTFromSortedList, findNode


def isBalanced(root: TreeNode):
    def getHeight(node: TreeNode):
        if node is None:
            return 0
        return 1 + max(getHeight(node.left), getHeight(node.right))

    if root is None:
        return True
    return isBalanced(root.left) and isBalanced(root.right) and abs(getHeight(root.left) - getHeight(root.right)) < 2


def isBalancedSingleTraversal(root: TreeNode):
    def getHeightAndBalance(node: TreeNode):
        if node is None:
            return (0, True)
        leftHeight, leftBalance = getHeightAndBalance(node.left)
        rightHeight, rightBalance = getHeightAndBalance(node.right)
        return (1 + max(leftHeight, rightHeight), leftBalance and rightBalance and (abs(leftHeight - rightHeight) < 2))

    if root is None:
        return True
    height, balance = getHeightAndBalance(root)
    return balance

import unittest

class TestBalancing(unittest.TestCase):
    def _check_balance(self, func):
        l = list(range(0, 31))
        root = buildMinBSTFromSortedList(l)
        printTree(root)
        b = func(root)
        self.assertTrue(b)
        node = findNode(root, 29)
        node.left = None
        printTree(root)
        b = func(root)
        self.assertTrue(b)
        node = findNode(root, 11)
        node.left = None
        printTree(root)
        b = func(root)
        self.assertFalse(b)

    def test_isBalancedSingleTraversal(self):
        self._check_balance(isBalanced)

    def test_isBalanced(self):
        self._check_balance(isBalancedSingleTraversal)

if __name__ == '__main__':
    unittest.main()
