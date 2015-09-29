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

def sort_boxes(list_of_boxes:list):
    return sorted(list_of_boxes, operator.attrgetter('width', 'depth', 'height'), reverse=True)






