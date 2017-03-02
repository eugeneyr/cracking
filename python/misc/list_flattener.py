def list_flattener(a):
    if not isinstance(a, list):
        return a
    rv = []
    for elem in a:
        if isinstance(elem, list):
            rv.extend(list_flattener(elem))
        else:
            rv.append(elem)
    return rv

def nonrec_list_flattener(a):
    if not isinstance(a, list):
        return a
    stack = a[:]
    rv = []
    while len(stack):
        item = stack.pop()
        if isinstance(item, list):
            for x in item:
                stack.append(x)
        else:
            rv.append(item)
    rv.reverse()
    return rv


if __name__ == '__main__':
    print(list_flattener([['a', 'b'], 'c', ['d', [[], [['e'], 'f'], 'g']]]))
    print(nonrec_list_flattener([['a', 'b'], 'c', ['d', [[], [['e'], 'f'], 'g']]]))
