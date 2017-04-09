def glob_matcher(pattern, string):
    if pattern is None or string is None:
        return False
    if not string:
        return not pattern or all(x == '*' for x in pattern)
    if not pattern:
        return False
    if pattern[0] == '?':
        return glob_matcher(pattern[1:], string[1:])
    elif pattern[0] == '*':
        return (glob_matcher(pattern[1:], string)
                or glob_matcher(pattern, string[1:])
                or glob_matcher(pattern[1:], string[1:]))
    elif pattern[0] == string[0]:
        return glob_matcher(pattern[1:], string[1:])
    return False


if __name__ == '__main__':
    pairs = [
        ('abc', 'abcd'),
        ('ab?', 'abc'),
        ('ab*def', 'abcdef'),
        ('ab?def', 'abcdef'),
        ('ab*f', 'abcdef'),
        ('ab*f', 'abcde'),
        ('*', 'abcde'),
        ('a?*f', 'abcdef'),
        ('*', ''),
        ('**', ''),
        ('**', 'abc'),
        ('*?*', 'abc')
    ]
    for pattern, string in pairs:
        print(pattern, string, glob_matcher(pattern, string))
