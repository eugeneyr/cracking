def textJustification(words, l):
    # 1) Split the list of words into lines no longer than l if spaced with a single space
    lines = []
    line = []
    for word in words:
        if sum(len(w) for w in line) + len(line) + len(word) > l:
            lines.append(line)
            line = []
        line.append(word)
    if line:
        lines.append(line)
    # 2) Space each line so it is fully justified
    ret_val = []
    for line_no, line in enumerate(lines):
        total_length = sum(len(w) for w in line)
        word_count = len(line)
        if word_count == 1 or line_no == len(lines) - 1:
            ret_val.append(' '.join(line).ljust(l))
        else:
            spaces_needed = l - total_length
            gap_width = spaces_needed // (word_count - 1)
            remainder = spaces_needed % (word_count - 1)
            ret_val.append((' ' * gap_width).join([(w + ' ') for w in line[:remainder]] + line[remainder:]))
    return ret_val


if __name__ == '__main__':
    # print(textJustification(['a', 'bb', 'c', 'd'], 3))
    # print(textJustification(["Two", "words."], 11))
    print(textJustification(["Looks",
                             "like",
                             "it",
                             "can",
                             "be",
                             "a",
                             "tricky",
                             "test"], 25))
