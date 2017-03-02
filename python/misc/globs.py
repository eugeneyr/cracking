def glob_matcher(pattern, string):
    if string is None or pattern is None:
        return False
    if string == '' and pattern == '':
        return True
    if string == '' or pattern == '':
        return False
    if pattern[0] == '?':
        return glob_matcher(pattern[1:], string[1:])
    elif pattern[0] == '*':
        for i in range(0, len(string) + 1):
            if glob_matcher(pattern[1:], string[i:]):
                return True
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
        ('a?*f', 'abcdef')
    ]
    for pattern, string in pairs:
        print(pattern, string, glob_matcher(pattern, string))
