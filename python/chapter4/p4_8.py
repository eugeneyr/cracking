"""
First Common Ancestor:

Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
Avoid storing additional nodes in a data structure.

NOTE:
This is not necessarily a binary search tree.

"""

from python.chapter4.datastructures import *

def findFirstCommonAncestor(root, node1, node2):
    def findNode(root, node):
        if root is None:
            return None
        if root == node:
            return root
        found = findNode(root.left, node)
        if found is None:
            found = findNode(root.right, node)
        return found

    if root is None or node1 is None or node2 is None:
        return None
    if root == node1 or root == node2:
        return root
    node1InLeft = findNode(root.left, node1)
    if node1InLeft is not None:
        node2InRight = findNode(root.right, node2)
        if node2InRight is not None:
            return root
        return findFirstCommonAncestor(root.left, node1, node2)
    else:
        node2InLeft = findNode(root.left, node2)
        if node2InLeft is not None:
            return root
        return findFirstCommonAncestor(root.right, node1, node2)


if __name__ == '__main__':
    l = [c for c in 'abcdefgijklmnop']
    tree = buildMinBSTFromSortedList(l)
    printTree(tree)
    a = findNode(tree, 'l')
    g = findNode(tree, 'b')
    node = findFirstCommonAncestor(tree, a, g)
    if node is not None:
        print(str(node))
    else:
        print('Nothing.')
