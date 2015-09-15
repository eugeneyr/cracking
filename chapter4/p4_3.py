"""
List of Depths:

Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth
(e.g., if you have a tree with depth D, you'll have D linked lists)
"""
from collections import OrderedDict

from chapter4.datastructures import *


def buildListOfLevelsBFS(root: TreeNode):
    if root is None:
        return None
    dol = OrderedDict()
    queue = [(root, 0)]
    while len(queue) > 0:
        node, lvl = queue.pop(0)
        if lvl in dol:
            l = dol[lvl]
        else:
            l = []
            dol[lvl] = l
        l.append(node.value)
        if node.left is not None:
            queue.append((node.left, lvl + 1))
        if node.right is not None:
            queue.append((node.right, lvl + 1))
    return [v for k, v in dol.items()]


def buildListOfLevelsRecursive(root: TreeNode):
    def buildLoL(node: TreeNode, dol, lvl):
        if node is None:
            return
        if lvl in dol:
            l = dol[lvl]
        else:
            l = []
            dol[lvl] = l
        l.append(node.value)
        buildLoL(node.left, dol, lvl + 1)
        buildLoL(node.right, dol, lvl + 1)

    if root is None:
        return None
    dol = OrderedDict()
    buildLoL(root, dol, 0)
    return [v for k, v in dol.items()]


def buildMinBSTFromSortedList(l: list):
    def buildMinTree(l: list, start, end):
        if l is None or len(l) == 0:
            return None
        if start >= end:
            return None
        middle = int((end + start) / 2)
        if middle < end:
            node = TreeNode(l[middle])
            node.left = buildMinTree(l, start, middle)
            node.right = buildMinTree(l, middle + 1, end)
            return node

    if l is None or len(l) == 0:
        return None
    return buildMinTree(l, 0, len(l))


if __name__ == '__main__':
    l = list(range(0, 31))
    root = buildMinBSTFromSortedList(l)
    print('depth:', getTreeDepth(root))
    print('BSF:')
    lol = buildListOfLevelsBFS(root)
    for l in lol:
        print(l)
    print('Recursion:')
    lol = buildListOfLevelsRecursive(root)
    for l in lol:
        print(l)

