# Rotate Matrix:
# Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
# write a method to rotate the image by 90 degrees. Can you do it in place?

def rotate90(matrix, clockwise=True):
    def nextPoint(point, n):
        res = (n - 1 - point[1], point[0])
        #print(point, ' => ', res)
        return res

    if matrix is None:
        return
    m = len(matrix)
    n = len(matrix[0])
    if m != n:
        raise ValueError('The matrix does not seem  to be square')
    for j in range(0, int(n / 2)):
        for i in range(j, n - 1 - j):
            pt0 = i, j
            #print(i, j)
            q0 = matrix[pt0[0]][pt0[1]]
            pt1 = nextPoint(pt0, n)
            q1 = matrix[pt1[0]][pt1[1]]
            pt2 = nextPoint(pt1, n)
            q2 = matrix[pt2[0]][pt2[1]]
            pt3 = nextPoint(pt2, n)
            q3 = matrix[pt3[0]][pt3[1]]
            if clockwise:
                matrix[pt3[0]][pt3[1]] = q0
                matrix[pt2[0]][pt2[1]] = q3
                matrix[pt1[0]][pt1[1]] = q2
                matrix[pt0[0]][pt0[1]] = q1
            else:
                matrix[pt3[0]][pt3[1]] = q2
                matrix[pt2[0]][pt2[1]] = q1
                matrix[pt1[0]][pt1[1]] = q0
                matrix[pt0[0]][pt0[1]] = q3
    return matrix


TEST_DATA = []

for i in range(0, 4):
    col = []
    for j in range(0, 4):
        col.append(j * 10 + i)
    TEST_DATA.append(col)


def prettyPrint(matrix):
    if matrix is None:
        return
    m = len(matrix)
    n = len(matrix[0])
    for i in range(0, m):
        row = ''
        for j in range(0, n):
            row += ('0' if int(matrix[i][j] / 10) == 0 else '') + str(matrix[i][j]) + ' '
        print(row)


if __name__ == '__main__':
    print('before clockwise:')
    prettyPrint(TEST_DATA)
    print('after clockwise:')
    prettyPrint(rotate90(TEST_DATA))
    print('back:')
    prettyPrint(rotate90(TEST_DATA, False))
    print('counterclockwise:')
    prettyPrint(rotate90(TEST_DATA, False))
    print('back:')
    prettyPrint(rotate90(TEST_DATA))



