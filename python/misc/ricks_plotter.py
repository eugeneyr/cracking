# def plot(fn,  x0, x1, y0, y1):
#     M = 80
#     N = 25
#     matrix = [[0 for x in range(0, M)] for y in range(0, N)]
#     xstep = (x1 - x0) / float(M)
#     ystep = (y1 - y0) / float(N)
#     for i in range(M):
#         x = i * xstep
#         y = fn(x)
#         matrix[y][x] = 1
#
#
#     return []

import math

def plot(fn, x0, x1, y0, y1):
    M = 80
    N = 25
    matrix = [[0 for _ in range(0, M)] for _ in range(0, N)]
    xstep = (x1 - x0) / float(M)
    ystep = (y1 - y0) / float(N)
    for i in range(M):
        x = i * xstep
        y = fn(x)
        matrix[int((y - y0) / ystep)][i] = 1

    return draw(matrix)

def draw(matrix):
    return [''.join(['*' if cell else ' ' for cell in row]) for row in matrix]

if __name__ == '__main__':
    for line in plot(math.sin, -math.pi, math.pi, -1, 1):
        print(line)
