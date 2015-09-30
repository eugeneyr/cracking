"""
Stacks of Boxes:
You have a stack of n boxes, with widths w_i, heights h_i, and depths d_i.
The boxes cannot be rotated and and can only be stacked on top of one another if each box in the stack is
larger than the box above it in width, height, and depth.
Implement a method to compute the height of the tallest possible stack.
The height of a stack is the sum of heights of each box.
"""

import operator

class Box:
    def __init__(self, width, height, depth):
        self.width = width
        self.height = height
        self.depth = depth

    def fitsOnTopOf(self, box):
        return self.width < box.width and self.depth < box.depth and self.height < box.height

    def __str__(self):
        return 'Box({}, {}, {})'.format(self.width, self.depth, self.height)

def sort_boxes(list_of_boxes:list):
    return sorted(list_of_boxes, key=operator.attrgetter('width', 'depth', 'height'), reverse=True)

def findTallestStack(boxes:list):
    tallestTuple = None
    tallestStacks = {}
    boxes = sort_boxes(boxes)
    maxHeight = 0
    for i, box in enumerate(boxes):
        bestBet = None
        maxStackIdx = None
        for j in range(0, i):
            height, stack = tallestStacks[j]
            if box.fitsOnTopOf(stack[-1]) and (bestBet is None or (height + box.height > bestBet)):
                bestBet = height + box.height
                maxStackIdx = j
        if maxStackIdx is not None:
            height, stack = tallestStacks[maxStackIdx]
            newStack = stack[:]
            newStack.append(box)
            tallestStacks[i] = (height + box.height, newStack)
        else:
            tallestStacks[i] = (box.height, [box])
            bestBet = box.height
        if maxHeight < bestBet:
            maxHeight = bestBet
            tallestTuple = tallestStacks[i]
    return tallestTuple


if __name__ == '__main__':
    # boxes = [Box(1, 1, 1), Box(2, 2, 2), Box(3, 3, 3)]
    #
    boxes = [Box(1, 1, 1), Box(2, 1, 2), Box(3, 2, 3), Box(3, 3, 3), Box(3, 4, 4)]
    tallest = findTallestStack(boxes)
    print(tallest[0], [str(box) for box in tallest[1]])










