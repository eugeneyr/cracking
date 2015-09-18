"""
Binary to String:
Given a real number between 0 and 1 (e.g., 0.72) that is passed as a double, print the binary representation.
 If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR"
"""

def fractionToBinaryStr(n):
    if not 0 <= n < 1:
        return 'ERROR'
    if n == 0:
        return '0.0'
    remainder = n
    rv = ''
    divisor = 0.5
    while remainder > 0:
        rv += '1' if remainder >= divisor else '0'
        if remainder >= divisor:
            remainder -= divisor
        divisor /= 2
        if len(rv) > 32:
            return 'ERROR'
    return '0.' + rv

if __name__ == '__main__':
    print(fractionToBinaryStr(0.5))
    print(fractionToBinaryStr(0.75))
    print(fractionToBinaryStr(0.25))
    print(fractionToBinaryStr(3 / 8))
    print(fractionToBinaryStr(1 / 8))

    print(fractionToBinaryStr(5 / 16))
    print(fractionToBinaryStr(0.1))

    for i in range (1, 8):
        print(fractionToBinaryStr(i / 8))


