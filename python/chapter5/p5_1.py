"""
Insertion:

You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to insert M into N such that
M starts at bit j and ends at bit i. You can assume that the bits j though i have enough space to fill all of M.
That is, if M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for example, have
j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
"""

import ctypes

def insertBitwise(n, m, i, j):
    if i > j or i < 0 or j < 0:
        return None
    mask = ctypes.c_ulong(0xffffffff).value ^ ((1 << (j - i + 1)) - 1) << i
    return (n & mask) | (m << i)


if __name__ == '__main__':
    n = 0b100000000000
    m = 0b10011
    i = 2
    j = 6
    print(bin(insertBitwise(n, m, i, j)))

