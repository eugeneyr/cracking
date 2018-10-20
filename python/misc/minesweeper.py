def minesweeper(matrix):
    rows = len(matrix)
    cols = len(matrix[0])

    def count_adjacent_mines(row, col):
        coords = [
            (r, c) for r in range(max(0, row - 1), min(rows, row + 2))
            for c in range(max(0, col - 1), min(cols, col + 2))
            if not (r == row and c == col)
        ]
        return sum((1 for coord in coords if matrix[coord[0]][coord[1]]))

    rv = []
    for r in range(0, rows):
        rv_row = []
        for c in range(0, cols):
            rv_row.append(count_adjacent_mines(r, c))
        rv.append(rv_row)
    return rv
