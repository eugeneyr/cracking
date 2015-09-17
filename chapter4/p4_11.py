"""
Random Node:
You are implementing a binary tree class from scratch which, in addition to insert, find, and delete, has a method
getRandomNode() which returns a random node from the tree.
All nodes should be equally likely to be chosen. Design and implement an algorithm for getRandomNode,
and explain how you would implement the rest of the methods.
"""

from chapter4.datastructures import *

import random
random.seed()

class BinaryTree:
    def __init__(self, root:TreeNode = None):
        self.root = root
        if self.root is not None:
            self.count = self.countNodes(self.root)
        else:
            self.count = 0

    def insert(self, value):
        pass

    def find(self, value):
        pass

    def delete(selfself, value):
        pass

    def countNodes(self, node):
        if node is None:
            return 0
        return 1 + self.countNodes(node.left) + self.countNodes(node.right)

    def getRandomNode(self):
        def findNodeWithIdx(node, idx, currIdx):
            if node is None:
                # not supposed to happen
                return None
            if currIdx > idx:
                # overshot??? Not supposed to happen either
                return None
            if idx == currIdx:
                return node
            retval = currIdx
            if node.left is not None:
                retval = findNodeWithIdx(node.left, idx, retval + 1)
            if isinstance(retval, int) and node.right is not None:
                retval = findNodeWithIdx(node.right, idx, retval + 1)
            return retval

        if self.root is None:
            return None
        idx = random.randint(0, self.count - 1)
        return findNodeWithIdx(self.root, idx, 0)

#import scipy.stats


if __name__ == '__main__':
    l = [i for i in range(0, 100)]
    root = buildMinBSTFromSortedList(l)
    tree = BinaryTree(root)
    vals = []
    for i in range(0, 100):
        val = tree.getRandomNode().value
        print(val)
        vals.append(val)

