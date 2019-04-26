"""
 ASCII Plotter:
 You need to print the graph of a mathematical function y = f(x) of a single (real) variable using an ancient
 line printer.
 Obviously, accuracy of the graph would be far from perfect.
 A space character would represent a "pixel" on the printed page through which the function does not pass, while
  "*" would represent a "pixel" that has some (x, y) pairs where y = f(x).

 Write a function that takes the following arguments:

 fn: a Python callable with a single argument
 x0, x1, y0, y1: the "window" we need to plot: [x0, x1] on the X axis and [y0, y1] on the Y axis
 rows, cols: the number of rows and columns on paper

 Output:
 the list of strings that can be sent to the printer line by line.
 The length of the list is <i>rows</i>, the length of each string is <i>cols</i>.

 For example, a call to draw_plot(math.sin, -math.pi, math.pi, -1.1, 1.1, 25, 80)
 would produce a plot similar to this:


                                                         ******
                                                      ***      ***
                                                    **            **
                                                  **                **
                                                **                    **
                                               *                        *
                                              *                          *
                                             *                            *
                                           **                              **
                                          *                                  *
                                         *                                    *
                                        *                                      *
*                                      *
 *                                    *
  *                                  *
   **                              **
     *                            *
      *                          *
       *                        *
        **                    **
          **                **
            **            **                                                    
              ***      ***
                 ******

"""
import math
import unittest

def draw_plot(fn, x0, x1, y0, y1, rows, cols):
    from collections import defaultdict

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


class PlotterTest(unittest.TestCase):
    def test_sin(self):
        lines = draw_plot(math.sin, -math.pi, math.pi, -1.1, 1.1, 25, 80)
        self.assertEqual(len(lines), 25)
        for line in lines:
            self.assertEqual(len(line), 80)
            print(line)


if __name__ == '__main__':
    unittest.main()
