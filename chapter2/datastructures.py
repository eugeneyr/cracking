class ListNode:
    def __init__(self, value, next=None):
        self.value = value
        self.next = next

    def deleteThis(self):
        if self.next is not None:
            self.value = self.next.value
            self.next = self.next.next
        else:
            raise ValueError('No can do, the next node is None')

    def deleteNext(self):
        if self.next is not None:
            self.next = self.next.next

    def __str__(self):
        return 'ListNode({})'.format(self.value)

    @staticmethod
    def buildFromIterable(iter):
        if iter is not None:
            l = list(iter)
            l.reverse()
            prev = None
            for elem in l:
                prev = ListNode(elem, prev)
            return prev

def printLinkedList(node):
    strList = '['
    while node is not None:
        strList += ' ' + str(node.value)
        node = node.next
    strList += ' ]'
    print(strList)

