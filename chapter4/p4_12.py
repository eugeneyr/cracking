"""
Paths with Sum:

You are given a binary tree in which each node contains an integer value (which might be positive or negative)
Design the algorithm to count the number of paths that sum to a given value. The path does not bneed to start or end at
the root of a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

"""

from chapter4.datastructures import *


def countPathsWithSum(node: TreeNode, theSum: int):
    def countPaths(node, theSum, path: list):
        if node is None:
            return 0
        path.insert(0, node.value)
        retval = 0
        currSum = 0
        for i, val in enumerate(path):
            currSum += val
            if currSum == theSum:
                retval += 1
                print(path[:i + 1])
        retval += countPaths(node.left, theSum, path)
        retval += countPaths(node.right, theSum, path)
        path.pop(0)
        return retval

    return countPaths(node, theSum, [])


import unittest


class TestPathCounter(unittest.TestCase):
    def test_countPaths(self):
        l = [i for i in range(0, 15)]
        tree = buildMinBSTFromSortedList(l)
        printTree(tree)
        ct = countPathsWithSum(tree, 13)
        print(ct)
        self.assertEqual(ct, 2)


if __name__ == '__main__':
    unittest.main()
