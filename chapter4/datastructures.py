class Vertex:
    def __init__(self, value, adjacent_vertices=None):
        self.value = value
        self.adjacent_vertices = []
        if adjacent_vertices is not None:
            self.adjacent_vertices.extend(adjacent_vertices)

    def __str__(self):
        return 'Vertex({})'.format(self.value)


class TreeNode:
    def __init__(self, value, left=None, right=None):
        self.value = value
        self.left = left
        self.right = right
    def __str__(self):
        return 'TreeNode({})'.format(self.value)


def getTreeDepth(node: TreeNode):
    if node is None:
        return 0
    return 1 + max(getTreeDepth(node.left), getTreeDepth(node.right))


def printTree(node: TreeNode, prefix=''):
    if node is None:
        return
    print(prefix, node.value)
    printTree(node.left, prefix + '    ')
    printTree(node.right, prefix + '    ')

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

def findNode(node: TreeNode, value):
    if node is None:
        return None
    if node.value == value:
        return node
    rv = findNode(node.left, value)
    if rv is None:
        rv = findNode(node.right, value)
    return rv
