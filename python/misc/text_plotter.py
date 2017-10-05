from collections import defaultdict

import math


def draw_plot(fn, x0, x1, y0, y1, rows=25, cols=80):
    rv = []
    points = defaultdict(list)
    x_step = (x1 - x0) / float(cols)
    y_step = (y1 - y0) / float(rows)
    for i in range(cols):
        x = x0 + i * x_step + x_step / 2
        y = fn(x)
        if not (y0 <= y <= y1):
            continue
        screen_y = rows - round((y - y0) / y_step)
        if 0 <= screen_y < rows:
            points[screen_y].append(i)

    for i in range(rows):
        row = list(' ' * 80)
        if i in points:
            for screen_x in points[i]:
                row[screen_x] = '*'
        rv.append(''.join(row))

    return rv

lines = draw_plot(math.sin, -math.pi, math.pi, -1.1, 1.1)

for l in lines:
    print(l)
