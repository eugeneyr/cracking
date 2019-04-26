"""
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.
 For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Example:

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
"""

def interval_intersection(a, b):
    b_idx = 0
    a_idx = 0
    rv = []
    while True:
        while b_idx < len(b) and b[b_idx][1] < a[a_idx][0]:
            b_idx += 1
        if b_idx == len(b):
            break
        while a_idx < len(a) and a[a_idx][1] < b[b_idx][0]:
            a_idx += 1
        if a_idx == len(a):
            break
        int_start = max(a[a_idx][0], b[b_idx][0])
        int_end = min(a[a_idx][1], b[b_idx][1])
        if int_start <= int_end:
            rv.append([int_start, int_end])
            move_a = a[a_idx][1] == int_end
            move_b = b[b_idx][1] == int_end
            if move_a:
                a_idx += 1
            if move_b:
                b_idx += 1
            if a_idx >= len(a) or b_idx >= len(b):
                break
    return rv


if __name__ == '__main__':
    res = interval_intersection(
        [[0, 2], [5, 10], [13, 23], [24, 25]],
        [[1, 5], [8, 12], [15, 24], [25, 26]]
    )
    expected = [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
    print(res)
    print(expected)
    print(res == expected)
