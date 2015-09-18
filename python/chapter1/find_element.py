TEST_DATA = [[1,  2,  3,  4,  5,  6],
 [ 7,  8,  9, 10, 11, 12],
 [13, 14, 15, 16, 17, 18]]

def isInSortedMatrix(num, matrix):
    def findRow(num, matrix, start, end):
        if end < 0 or start > len(matrix) - 1:
            return None
        if start >= end and start in range(0, len(matrix)):
            return matrix[start]
        middle = int((end + start) / 2)
        middleRow = matrix[middle]
        if middleRow[-1] >= num:
            return findRow(num, matrix, start, middle)
        else:
            return findRow(num, matrix, middle + 1, end)

    def findElement(num, row, start, end):
        if end < 0 or start > len(row) - 1:
            return None
        if start >= end and start in range(0, len(row)):
            if row[start] == num:
                return start
            else:
                return None
        middle = int((end + start) / 2)
        if row[middle] >= num:
            return findElement(num, row, start, middle)
        else:
            return findElement(num, row, middle + 1, end)

    # 1) Find the row:
    begin = 0
    end = len(matrix) - 1

    # 2) Find the element:
    row = findRow(num, matrix, begin, end)
    if row is not None:
        idx = findElement(num, row, 0, len(row) - 1)
        return idx is not None

if __name__ == '__main__':
    print(isInSortedMatrix(2, TEST_DATA))
    print(isInSortedMatrix(20, TEST_DATA))
    print(isInSortedMatrix(5.5, TEST_DATA))