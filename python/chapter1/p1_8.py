# Zero Matrix:
# Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0

def zeroMatrix(matrix):
    if matrix is None:
        return
    m = len(matrix)
    n = len(matrix[0])
    zeroRows = set()
    zeroColumns = set()

    for j, row in enumerate(matrix):
        for i, val in enumerate(row):
            if val == 0:
                zeroRows.add(j)
                zeroColumns.add(i)
    for i in zeroColumns:
        for j, row in enumerate(matrix):
            row[i] = 0
    for j in zeroRows:
        row = matrix[j]
        for i in range(0, len(row)):
            row[i] = 0
    return matrix

TEST_DATA = [
    [0, 2, 3, 4, 5],
    [6, 7, 0, 9, 10],
    [11, 12, 13, 0, 15],
    [16, 17, 18, 19, 20]
]

import math

def prettyPrint(matrix):
    if matrix is None:
        return
    m = len(matrix)
    n = len(matrix[0])
    for i in range(0, m):
        row = ''
        for j in range(0, n):
            row += (' ' * (4 - int(math.log10(matrix[i][j] if matrix[i][j] != 0 else 1)))) + str(matrix[i][j]) + ' '
        print(row)

if __name__ == '__main__':
    print('before zero:')
    prettyPrint(TEST_DATA)
    print('after zero:')
    prettyPrint(zeroMatrix(TEST_DATA))
