def glob_matcher(p, s):
    if p is None or s is None:
        return False
    if not s:
        return not p or (p[0] == '*' and glob_matcher(p[1:], s))
    if not p:
        return False
    if p[0] == '?':
        return glob_matcher(p[1:], s[1:])
    elif p[0] == '*':
        return (glob_matcher(p[1:], s) or glob_matcher(p, s[1:]) or glob_matcher(p[1:], s[1:]))
    elif p[0] == s[0]:
        return glob_matcher(p[1:], s[1:])
    return False


if __name__ == '__main__':
    pairs = [
        ('abc', 'abcd', False),
        ('ab?', 'abc', True),
        ('ab*def', 'abcdef', True),
        ('ab?def', 'abcdef', True),
        ('ab*f', 'abcdef', True),
        ('ab*f', 'abcde', False),
        ('*', 'abcde', True),
        ('a?*f', 'abcdef', True),
        ('*', '', True),
        ('**', '', True),
        ('*?*d', 'abc', False),
        ('*f*', 'abfer', True),
        ('**', 'abc', True),
        ('*?*', 'abc', True)
    ]
    for pattern, string, result in pairs:
        print(pattern, string, glob_matcher(pattern, string))
        assert(result == glob_matcher(pattern, string))
