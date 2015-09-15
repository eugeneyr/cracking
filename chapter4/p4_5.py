"""
Validate BST:

Implement a function to check if a binary tree is a binary search tree.
"""

from chapter4.datastructures import TreeNode, buildMinBSTFromSortedList, printTree


def isBST(node):
    if node is None:
        return True
    if node.left is not None:
        if node.left.value > node.value:
            return False
        if not isBST(node.left):
            return False
    if node.right is not None:
        if node.right.value < node.value:
            return False
        if not isBST(node.right):
            return False
    return True

import random

if __name__ == '__main__':
    random.seed()
    l = [i for i in range(0, 16)]
    tree = buildMinBSTFromSortedList(l)
    printTree(tree)
    print('is BST:', isBST(tree))

    l = [i for i in range(0, 16)]
    random.shuffle(l)
    tree = buildMinBSTFromSortedList(l)
    printTree(tree)
    print('is BST:', isBST(tree))

    l = [i for i in range(0, 16)]
    tree = buildMinBSTFromSortedList(l)
    tmp = tree.left
    tree.left = tree.right
    tree.right = tmp
    printTree(tree)
    print('is BST:', isBST(tree))
