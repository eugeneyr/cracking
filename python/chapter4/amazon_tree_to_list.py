"""
Connect nodes at same level of a binary tree recursively using O(1) space (we can ignore stack space used for recursion)
Tree node is like following:

struct node {
  int data;
  struct node* left;
  struct node* right;
  struct node* nextRight;
}

Initially, all the nextRight pointers point to garbage values.
Your function should set these pointers to point next right for each node.
You can use only constant extra space.
"""

from python.chapter4.datastructures import *


class SkeweredTreeNode(TreeNode):
    def __init__(self, value, left=None, right=None, nextRight=None):
        super().__init__(value, left, right)
        self.nextRight = nextRight

    def __str__(self):
        rv = str(self.value)
        if self.nextRight is not None:
            rv += ' => ' + str(self.nextRight.value)
        return rv


def printSkeweredTree(node: TreeNode, prefix=''):
    if node is None:
        return
    print(prefix, str(node))
    printSkeweredTree(node.left, prefix + '    ')
    printSkeweredTree(node.right, prefix + '    ')


def buildTreeFromList(l: list):
    def buildMinTree(l: list, parent, start, end):
        if l is None or len(l) == 0:
            return None
        if start >= end:
            return None
        middle = int((end + start) / 2)
        if middle < end:
            node = SkeweredTreeNode(l[middle])
            node.left = buildMinTree(l, node, start, middle)
            node.right = buildMinTree(l, node, middle + 1, end)
            return node

    if l is None or len(l) == 0:
        return None
    return buildMinTree(l, None, 0, len(l))


def skewerTreeWithO1Space(node: SkeweredTreeNode):
    def skewerWithLevels(node: SkeweredTreeNode, level, targetLevel, prevNode: SkeweredTreeNode):
        if node is None:
            return None
        if level == targetLevel:
            if prevNode is None:
                return node
            else:
                prevNode.nextRight = node
            return node

        if node.left is not None:
            prevNode = skewerWithLevels(node.left, level + 1, targetLevel, prevNode)
        if node.right is not None:
            prevNode = skewerWithLevels(node.right, level + 1, targetLevel, prevNode)
        return prevNode

    maxDepth = getTreeDepth(node)
    for i in range(0, maxDepth):
        skewerWithLevels(node, 0, i, None)
    return node


def skewerTreeWithONSpace(node: SkeweredTreeNode):
    def skewerWithLevels(node: SkeweredTreeNode, level, prevNodes):
        if node is None:
            return
        if level in prevNodes:
            prevNode = prevNodes[level]
            prevNode.nextRight = node
        prevNodes[level] = node
        skewerWithLevels(node.left, level + 1, prevNodes)
        skewerWithLevels(node.right, level + 1, prevNodes)

    skewerWithLevels(node, 0, {})
    return node

if __name__ == '__main__':
    l = [i for i in range(0, 31)]
    tree = buildTreeFromList(l)
    skewerTreeWithO1Space(tree)
    printSkeweredTree(tree)

    tree = buildTreeFromList(l)
    skewerTreeWithONSpace(tree)
    printSkeweredTree(tree)

