"""
Check Subtree:

T1 and T2 are two very large binary trees, with T1 much bigger than T2.
Create an algorithm to determine if T2 is a subtree of T1.
A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2.
  That is, if you cut off the tree at node n, the two trees would be identical.

"""

# Several algorithms come to my mind:

# 1) A naive one:
#     for each n in T1 check if subtree(T1, n) == T2.
# Complexity: O(M*N)

# 2) An enhanced one:
#     - calculate depth of T2, store depth of each node in the process
#     - calculate depth of T1, store depth of each node in the process
#     - traverse T1, looking for nodes n such that n.value == T2.head.value and depth(n) == depth(T2).
#       Don't go to nodes with depth less than depth(T2).
#          - for each node n:
#                - compare subtree(n) with T2
# The worst case complexity is still O(M*N) and required additional storage is O(M+N),
# but on most "real-life" trees it would perform much better.

# 3) A pretty inefficient one, suggested by someone on the Internetz:
#     - build node value arrays by pre-order and in-order traversals of T1 and T2
#     - if both in-order and post-order arrays for T2 are subarrays of respective arrays for T1,
#       then T2 is a subtree of T1.
#  Requires O(N+M) of additional storage space. Its time complexity depends on how the sub-array search is implemented.
#  Bayer-Moore or Knuth-Morris-Pratt can get O(N+M) by the price of additional storage (O(M)? Verify)

from python.chapter4.datastructures import *


def areTreesEqual(node1: TreeNode, node2: TreeNode):
    if node1 is None:
        return node2 is None
    if node2 is None:
        return node1 is None
    if node1 == node2:
        return True
    if node1.value == node2.value:
        return areTreesEqual(node1.left, node2.left) and areTreesEqual(node1.right, node2.right)
    return False


def isSubtree_naive(t1, t2):
    if t1 is None or t2 is None:
        return False
    return areTreesEqual(t1, t2) or isSubtree_naive(t1.left, t2) or isSubtree_naive(t1.right, t2)


def storeTreeDepth(node: TreeNode):
    if node is None:
        return 0
    depth = 1 + max(storeTreeDepth(node.left), storeTreeDepth(node.right))
    node.depth = depth
    return depth


def isSubtree_moreComplex(t1, t2):
    def traverseAndCompare(t1, t2):
        if t1 is None or t2 is None:
            return False
        if t1.depth < t2.depth:
            return False
        if t1.depth > t2.depth:
            return traverseAndCompare(t1.left, t2) or traverseAndCompare(t1.right, t2)
        if t1.value == t2.value:
            return areTreesEqual(t1, t2)

    depthT1 = storeTreeDepth(t1)
    depthT2 = storeTreeDepth(t2)
    if depthT1 < depthT2:
        return False
    return traverseAndCompare(t1, t2)

import random
import copy

if __name__ == '__main__':
    random.seed()
    l = [i for i in range(0, 1000000)] * 2
    random.shuffle(l)
    t1 = buildMinBSTFromSortedList(l)
    t2 = findNode(t1, 4500)
    t2 = copy.deepcopy(t2)

    print(isSubtree_naive(t1, t2))
    print(isSubtree_moreComplex(t1, t2))

    # t1 = buildMinBSTFromSortedList('abcdefghijklmnop')
    # t2 = findNode(t1, 'l')
    # t2 = copy.deepcopy(t2)
    #
    # print(isSubtree_naive(t1, t2))
    # print(isSubtree_moreComplex(t1, t2))
    #
    # t1 = buildMinBSTFromSortedList('abcdefghijklmnop')
    # t2 = findNode(t1, 'l')
    # t2 = copy.deepcopy(t2)
    #
    # print(isSubtree_naive(t1, t2))
    # print(isSubtree_moreComplex(t1, t2))
    #
    # t2 = buildMinBSTFromSortedList(random.shuffle([i for i in 'efghijkl']))
    #
    # print(isSubtree_naive(t1, t2))
    # print(isSubtree_moreComplex(t1, t2))
    #
    #
    #
    #
