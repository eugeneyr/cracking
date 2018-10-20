def treeBottom(tree:str):
    curr_depth = 0
    layers = {}
    stump = tree
    # traverse tree, remember nodes and which layer they were found on - we don't care about parent-child relationship
    while stump:
        if stump[0] == '(':
            stump = stump[1:]
            curr_depth += 1
        elif stump[0] == ')':
            stump = stump[1:]
            curr_depth -= 1
        elif stump[0] == ' ':
            stump = stump[1:]
        else:
            stump_parts = stump.split(maxsplit=1)
            layers.setdefault(curr_depth, []).append(int(stump_parts[0]))
            stump = stump_parts[1]
    # return the deepest layer
    if layers:
        return layers[max(layers.keys())]
    return []


if __name__ == '__main__':
    print(treeBottom('(2 (7 (2 () ()) (6 (5 () ()) (11 () ()))) (5 () (9 (4 () ()) ())))'))

