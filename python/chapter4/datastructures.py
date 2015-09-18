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

def searchInBST(node, elem):
    if node is None:
        return None
    if node.value == elem:
        return node
    elif node.value > elem:
        return searchInBST(node.left, elem)
    else:
        return searchInBST(node.right, elem)


def buildBST(l: list):
    def insertNode(node, elem):
        if node is None:
            return TreeNode(elem)
        if node.value == elem:
            return node
        elif node.value > elem:
            node.left = insertNode(node.left, elem)
        else:
            node.right = insertNode(node.right, elem)
        return node

    if l is None:
        return None
    node = None
    for i in l:
        # n = search(node, i)
        # if n is None:
        node = insertNode(node, i)
    return node

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
