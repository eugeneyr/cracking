"""
BST Sequences:
A binary search tree was created by traversing through an array from left to right and inserting each element.
Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
EXAMPLE:
Input:
         2
     1       3

Output:
       {2, 1, 3}, {2, 3, 1}
"""

from python.chapter4.datastructures import *

def multiplyArrays(arr1, arr2):
    result = []
    if arr1 == []:
        return [arr2]
    if arr2 == []:
        return [arr1]
    head1 = arr1[0]
    tail1 = arr1[1:]
    for i in range(0, len(arr2) + 1):
        prefix = arr2[0:i] + [head1]
        allSuffixes = multiplyArrays(tail1, arr2[i:])
        for suffix in allSuffixes:
            result.append(prefix + suffix)
    return result

def multiplyArraySets(leftArrays, rightArrays):
    result = []
    for arr1 in leftArrays:
        for arr2 in rightArrays:
            result.extend(multiplyArrays(arr1, arr2))
    return result

def buildAllPossibleArrays(node):
    if node is None:
        return [[]]
    leftArrays = buildAllPossibleArrays(node.left)
    rightArrays = buildAllPossibleArrays(node.right)
    arrays = []
    suffixes = multiplyArraySets(leftArrays, rightArrays)
    for suffix in suffixes:
        arr = [node.value]
        arr.extend(suffix)
        arrays.append(arr)
    return arrays

if __name__ == '__main__':
    # print(multiplyArrays([1, 2], [3, 4]))
    # print(multiplyArraySets([[1, 2]], [[3, 4]]))
    # print(multiplyArraySets([], []))
    # print(multiplyArraySets([[], []], [[]]))
    for k in range(2, 11):
        l = [i for i in range(1, k)]
        tree = buildMinBSTFromSortedList(l)
        #printTree(tree)
        arrs = buildAllPossibleArrays(tree)

        for arr in arrs:
            #print(arr)
            t = buildBST(arr)
            #printTree(t)
            print(areTreesEqual(tree, t))
        print(len(l), ' => ', len(arrs))