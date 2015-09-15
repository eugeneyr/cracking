"""
Minimal Tree:
Given a sorted (in increasing order) array with unique integer elements, write an algorithm
to create a binary search tree with minimal height.
"""

from chapter4.datastructures import *


def buildMinBSTFromSortedList(l: list):
    def buildMinTree(l: list, start, end):
        if l is None or len(l) == 0:
            return None
        if start >= end:
            return None
        middle = int((end + start) / 2)
        if middle < end:
            node = TreeNode(l[middle])
            node.left = buildMinTree(l, start, middle - 1)
            node.right = buildMinTree(l, middle + 1, end)
            return node

    if l is None or len(l) == 0:
        return None
    return buildMinTree(l, 0, len(l))


if __name__ == '__main__':
    l = list(range(0, 30))
    root = buildMinBSTFromSortedList(l)
    print('depth:', getTreeDepth(root))
    print('tree:')
    printTree(root)
