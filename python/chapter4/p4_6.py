"""

Successor:

Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search tree.
You may assume that each node has a link to its parent.
"""

from python.chapter4.datastructures import TreeNode


class TreeNodeWithParent(TreeNode):
    def __init__(self, value, left=None, right=None, parent=None):
        super().__init__(value, left, right)
        self.parent = parent

    def __str__(self):
        return 'TreeNodeWithParent({})'.format(self.value)


def buildTreeFromList(l: list):
    def buildMinTree(l: list, parent, start, end):
        if l is None or len(l) == 0:
            return None
        if start >= end:
            return None
        middle = int((end + start) / 2)
        if middle < end:
            node = TreeNodeWithParent(l[middle])
            node.left = buildMinTree(l, node, start, middle)
            node.right = buildMinTree(l, node, middle + 1, end)
            node.parent = parent
            return node

    if l is None or len(l) == 0:
        return None
    return buildMinTree(l, None, 0, len(l))


def findNextNode(node: TreeNodeWithParent):
    def findLeftmostDescendant(node):
        if node is None:
            return None
        while node.left is not None:
            node = node.left
        return node

    def findFirstRightParent(node):
        while node is not None and node.parent is not None and node.parent.left != node:
            node = node.parent
        return node.parent if node.parent is not None else None

    if node is None:
        return None
    if node.right is not None:
        return findLeftmostDescendant(node.right)
    if node.parent is None:
        # I am the root and there is no right subtree - there is no next element!
        return None
    if node == node.parent.left:
        # my parent is the next one
        return node.parent
    return findFirstRightParent(node)

import unittest

class TestFindNextNode(unittest.TestCase):
    def test_findNextNode(self):
        def inOrderTraverse(node):
            if node is None:
                return
            inOrderTraverse(node.left)
            nextNode = findNextNode(node)
            if node.value == 31:
                self.assertIsNone(nextNode)
                print(node.value, '=> *')
            else:
                self.assertIsNotNone(nextNode)
                self.assertEqual(node.value + 1, nextNode.value)
                print(node.value, '=>', nextNode.value)
            inOrderTraverse(node.right)

        l = [i for i in range(0, 32)]
        bst = buildTreeFromList(l)
        inOrderTraverse(bst)

if __name__ == '__main__':
    unittest.main()