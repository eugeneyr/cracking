# Eliminates all consecutive pairs of characters in the string.
# Does it until there are no pairs to remove, so 'abba' turns into an empty string
# 'abcba' => 'abcba'
# 'aabc' => 'bc'

def remove_dups_recursively(s):
    if s is None:
        return None
    if len(s) < 2:
        return s
    head = s[0]
    tail = s[1:]
    res = remove_dups_recursively(tail)
    if len(res) > 0:
        res_head = res[0]
        if res_head == head:
            return res[1:]
    return head + res

def remove_dups_with_stack(s):
    stack = [c for c in s]
    rv = []
    while len(stack) > 0:
        c_before = stack.pop()
        if len(rv) > 0:
            c_after = rv[0]
            if c_after == c_before:
                rv.pop(0)
                continue
        rv.insert(0, c_before)
    return ''.join(rv)

import unittest
import collections

class TestRemovals(unittest.TestCase):
    def setUp(self):
        self.data = collections.OrderedDict((('',''),
                                            ('abc','abc'),
                                            ('abba', ''),
                                            ('a', 'a'),
                                            ('abcba', 'abcba'),
                                            ('aabc', 'bc'),
                                            ('abccba', ''),
                                            ('aaaaaaa', 'a'),
                                            ('abccbabaab', ''),
                                            ('abccbadbaab', 'd')))
        self.funcs = (remove_dups_recursively, remove_dups_with_stack)

    def test_all(self):
        for func in self.funcs:
            for k, v in self.data.items():
                res = func(k)
                print('{}(\'{}\') = \'{}\''.format(func.__name__, k, res))
                self.assertEqual(res, v)

if __name__ == '__main__':
    unittest.main()
