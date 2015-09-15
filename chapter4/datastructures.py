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
