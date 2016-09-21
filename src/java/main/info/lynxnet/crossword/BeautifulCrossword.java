package info.lynxnet.crossword;

import java.util.*;

/*

Problem Statement

    You are given a square board of NxN cells and a list of words.
    Your task is to create a crossword on this board using only these words so that it scores as high as possible
    on the given criteria of "beauty".

You are given the size of the board N, the list of allowed words and the list of weights used to
balance the scores on individual criteria.
You have to return a String[] which contains your crossword. Each element corresponds to one row of the board.
'.' mark empty cells, and uppercase letters mark the cells in which letters are written (letter cells).
Rows must be listed from top to bottom, and cells within each row must be listed from left to right.
Each maximal vertical sequence (when read from top to bottom) and horizontal sequence (when read from left to right)
of two and more letters must be a valid word (i.e., it must be present in words).
Each letter must be a part of at least one word (vertical or horizontal).
Each word can be used at most once.

A valid crossword will be scored by calculating the scores on 4 criteria:

Board filling score is the total number of letter cells, divided by the total number of cells on the board (N*N).

Rows/columns filling score is the number of columns which contain at least one letter cell,
multiplied by the number of rows which contain at least one letter cell, divided by N*N.

Symmetry score is calculated as follows:
for each cell of 1/8 of the grid the number of empty cells among itself and 7 cells symmetrical to it
(horizontally, vertically, diagonally and combinations) is calculated.
More exactly, let (i, j) be the cell in row i, column j of the board
(both rows and columns are numbered from 0 to N-1).
Then for each cell (i, j), such that 0 <= i <= N-i-1, 0 <= j <= i,
the number of empty cells is calculated among the following eight cells:
(i, j), (j, i), (N-i-1, j), (j, N-i-1), (i, N-j-1), (N-j-1, i), (N-i-1, N-j-1) and (N-j-1, N-i-1).
If some cell occures several times among these 8 cell and it is empty, then each occurrence contributes
independently towards the number of empty cells.
Next, values 0 or 8 add 1.0 to the score, values 1 or 7 add 0.5 to the score, values 2 or 6 add 0.1 to the score.
Finally, the score is divided by the number of cells in 1/8 of the grid, i.e., the number of cells (i, j),
such that 0 <= i <= N-i-1, 0 <= j <= i.

Crossings score is the number of crossing cells, divided by the total number of letter cells.
A letter cell is a crossing cell if it is a part of both vertical and horizontal word.
If there are no letter cells in a crossword, then its crossings score is 0.
Your score for the test case will be the weighted average of these values with weights given in weights.
In other words, if your board filling, rows/columns filling, symmetry and crossings scores are A, B, C and D,
then your score for this test case is
(A*weights[0] + B*weights[1] + C*weights[2] + D*weights[3]) / (weights[0] + weights[1] + weights[2] + weights[3]).

Your overall score will be the sum of individual scores over all test cases. Invalid returns are given zero score.

A tester is provided for offline testing. See its source code for details of test case generation and scoring.

Definition

Class:	BeautifulCrossword
Method:	generateCrossword
Parameters:	int, String[], int[]
Returns:	String[]
Method signature:	String[] generateCrossword(int N, String[] words, int[] weights)
(be sure your method is public)


Notes
-	words is generated from this list (which is effectively TWL06 list with words of length 2 removed) using the following algorithm.
    Value prob is chosen between 0.001 and 0.01, and each word from the list is included in words with this probability.
    words is always sorted in alphabetical order. All elements of words will be uppercase.
-	N and elements of weights will be chosen uniformly at random.
-	The memory limit is 1024 MB and the time limit is 10 seconds per test case (which includes only time spent in your code).
    There is no explicit code size limit. The implicit source code size limit is around 1 MB
    (it is not advisable to submit codes of size close to that or larger).
-	There are 10 example test cases and 100 full submission test cases.

Constraints
-	N will be between 20 and 100, inclusive (except example 0).
-	weights will contain exactly 4 elements, each element being between 1 and 10, inclusive.
-	Your return must contain N elements, each element must contain exactly N characters.
-	Each character of your return must be 'A'..'Z' or '.'.

Examples
0)

N = 11
Number of words = 179
Weights are  6  8  7  10
One of the possible crosswords for this test case is

BORER.BOTAS
R......V..I
A....C.E..N
S.F..O.R..E
H.U..C..T.W
..STACKER..
..E..O..A..
S.L..I..Y.U
I..INDEX..M
M.........P
AHED..SINUS

1)

N = 74
Number of words = 1258
Weights are  9  7  7  3
2)

N = 78
Number of words = 645
Weights are  5  10  1  8
3)

N = 41
Number of words = 903
Weights are  7  4  2  10
4)

N = 43
 Number of words = 198
 Weights are  2  1  10  2
5)

N = 71
 Number of words = 489
 Weights are  7  9  4  9
6)

N = 80
 Number of words = 1578
 Weights are  2  2  9  1
7)

N = 24
 Number of words = 1120
 Weights are  9  4  10  9
8)

N = 84
 Number of words = 398
 Weights are  7  8  4  7
9)

N = 87
 Number of words = 289
 Weights are  4  10  2  2

*/
public class BeautifulCrossword {
    private WordStore store;
    private int n;
    private int[] weights;
    private Set<Board> knownPuzzles = new HashSet<>();

    public void addKnownPuzzle(Board board) {
        if (!isSubsetOfAKnownPuzzle(board)) {
            knownPuzzles.add(board);
            String[] b = board.asStringArray();
            System.out.println("*** FOUND *** SCORE = " + calculateScore(b));
            for (String s : b) {
                System.out.println(s);
            }
        }
    }

    public void printBoard(Board board) {
        String[] b = board.asStringArray();
        System.out.println("SCORE = " + calculateScore(b));
        for (String s : b) {
            System.out.println(s);
        }
    }

    public boolean isSubsetOfAKnownPuzzle(Board board) {
        for (Board puzzle : knownPuzzles) {
            if (puzzle.isSupersetOf(board)) {
                return true;
            }
        }
        return false;
    }

    public WordStore getStore() {
        return store;
    }

    public int getN() {
        return n;
    }

    public int[] getWeights() {
        return weights;
    }

    public Set<Board> getKnownPuzzles() {
        return knownPuzzles;
    }

    public void execute(CrosswordBuilder builder) {
        try {
            builder.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] generateCrossword(int N, String[] words, int[] weights) {
        store = new WordStore(words);
        n = N;
        this.weights = weights;
        Board board = new Board(n);
        execute(new CrosswordBuilder(this, board, n, 0, 0, Direction.ACROSS));
        return null;
    }

    public String[] generateCrossword(int N, String wordFileName, int[] weights) {
        store = new WordStore(wordFileName);
        n = N;
        this.weights = weights;
        Board board = new Board(n);
        execute(new CrosswordBuilder(this, board, n, 0, 0, Direction.ACROSS));

        return null;
    }

    void addFatalError(String message) {
        // System.out.println(message);
    }

    public double calculateScore(String[] puzzle) {
        int i = 0;
        int j = 0;

        char[][] board = new char[n][n];
        for (i = 0; i < n; i++) {
            if (puzzle[i] == null || puzzle[i].length() != n) {
                addFatalError("Element " + i + " of your return contained invalid number of characters.");
                return 0;
            }
            for (j = 0; j < n; j++) {
                board[i][j] = puzzle[i].charAt(j);
                if ((board[i][j] < 'A' || board[i][j] > 'Z') && board[i][j] != '.') {
                    addFatalError("Character [" + i + "][" + j + "] of your return was invalid.");
                    return 0;
                }
            }
        }

        String[] words = store.getWords().toArray(new String[0]);

        // score the return
        // 0. validity with respect to uniqueness of words used and words
        // coverage for all sequences of at least 2 letters in row
        int totalLetters = 0;
        boolean[] usedWord = new boolean[words.length];
        Arrays.fill(usedWord, false);
        StringBuffer sb = new StringBuffer();
        for (i = 0; i < n; i++) {
            sb.append(new String(board[i]));
            sb.append(".");
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                sb.append(board[j][i]);
            sb.append(".");
        }
        String[] met = sb.toString().split("[\\.]+");
        for (i = 0; i < met.length; i++) {
            totalLetters += met[i].length();
            if (met[i].length() >= 2) { // check only words of length 2 and
                // more
                j = Arrays.binarySearch(words, met[i]);
                // check whether this is a word
                if (j < 0) {
                    addFatalError("Your crossword contains word \""
                            + met[i] + "\" which is not present in dictionary.");
                    return 0;
                }
                // check whether this word was already used
                if (usedWord[j]) {
                    addFatalError("Your crossword contains word \"" + met[i] + "\" twice or more.");
                    return 0;
                }
                // mark the word as used
                usedWord[j] = true;
            }
        }
        totalLetters /= 2; // each letter was counted twice

        // 0. validity with respect to "each letter must be part of a word"
        // = each letter cell must have a letter cell neighbor
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                if (board[i][j] != '.'
                        && (i == 0 || board[i - 1][j] == '.')
                        && (i == n - 1 || board[i + 1][j] == '.')
                        && (j == 0 || board[i][j - 1] == '.')
                        && (j == n - 1 || board[i][j + 1] == '.')) {
                    // all neighbors are . or outside of the board
                    addFatalError("Your crossword contains a letter which is not part of any word (at "
                            + i + ", " + j + ").");
                    return 0;
                }

        // 1. board filling score = no of letters / no of cells
        double boardFillingScore = totalLetters * 1.0 / (n * n);
        addFatalError("Board filling score = " + boardFillingScore);

        // 2. rows/cols filling score - no of cols with at least 1 char * no
        // of rows with at least 1 char / n*n
        int filledCols = 0, filledRows = 0;
        boolean emptyCol, emptyRow;
        for (i = 0; i < n; i++) {
            emptyCol = emptyRow = true;
            for (j = 0; j < n; j++) {
                if (board[i][j] != '.')
                    emptyRow = false;
                if (board[j][i] != '.')
                    emptyCol = false;
            }
            if (!emptyCol)
                filledCols++;
            if (!emptyRow)
                filledRows++;
        }
        double rcFillingScore = filledCols * filledRows * 1.0 / (n * n);
        addFatalError("Rows/columns filling score = " + rcFillingScore);

        // 3. symmetry score
        double symmetryScore = 0.0, nc = 0, cellScore;
        int nEqual;
        for (i = 0; i < (n + 1) / 2; i++)
            for (j = 0; j <= i; j++) {
                nEqual = (board[i][j] == '.' ? 1 : 0)
                        + (board[i][n - j - 1] == '.' ? 1 : 0)
                        + (board[n - i - 1][j] == '.' ? 1 : 0)
                        + (board[n - i - 1][n - j - 1] == '.' ? 1 : 0)
                        + (board[j][i] == '.' ? 1 : 0)
                        + (board[j][n - i - 1] == '.' ? 1 : 0)
                        + (board[n - j - 1][i] == '.' ? 1 : 0)
                        + (board[n - j - 1][n - i - 1] == '.' ? 1 : 0);
                nEqual = Math.max(nEqual, 8 - nEqual);
                cellScore = 0;
                if (nEqual == 8)
                    cellScore = 1;
                if (nEqual == 7)
                    cellScore = 0.5;
                if (nEqual == 6)
                    cellScore = 0.1;
                symmetryScore += cellScore;
                nc++;
                // System.out.println(i+" "+j+" "+nEqual+": "+board[i][j]+board[i][n-j-1]+board[n-i-1][j]+board[n-i-1][n-j-1]+board[j][i]+board[j][n-i-1]+board[n-j-1][i]+board[n-j-1][n-i-1]+" -> "+cellScore);
            }
        symmetryScore /= nc;
        addFatalError("Symmetry score = " + symmetryScore);

        // 4. words crossings score - no of letters which are parts of 2
        // words, divided by no of letters overall
        // for each letter, check whether it's part of a vertical word, and
        // part of horizontal word
        double crossingsScore = 0;
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                if (board[i][j] != '.'
                        && (i > 0 && board[i - 1][j] != '.' || i < n - 1
                        && board[i + 1][j] != '.')
                        && (j > 0 && board[i][j - 1] != '.' || j < n - 1
                        && board[i][j + 1] != '.'))
                    crossingsScore++;
        if (totalLetters > 0)
            crossingsScore /= totalLetters;
        addFatalError("Crossings score = " + crossingsScore);

        return (boardFillingScore * weights[0] + rcFillingScore
                * weights[1] + symmetryScore * weights[2] + crossingsScore
                * weights[3])
                / (weights[0] + weights[1] + weights[2] + weights[3]);
    }

    public static void main(String[] args) {
        String fileName = args[0];
        BeautifulCrossword bc = new BeautifulCrossword();
        bc.generateCrossword(5, fileName, new int[]{1, 1, 1, 1});
        List<Board> puzzles = new ArrayList<>(bc.getKnownPuzzles());
        Collections.sort(puzzles, new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                double score1 = bc.calculateScore(o1.asStringArray());
                double score2 = bc.calculateScore(o2.asStringArray());
                return score1 == score2 ? 0: score1 < score2 ? -1 : 1;
            }
        });

        if (puzzles.size() > 0) {
            Board worst = puzzles.get(0);
            Board best = puzzles.get(puzzles.size() - 1);
            System.out.println("Best:");
            bc.printBoard(best);
        }
        // By the number of words
        Collections.sort(puzzles, new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                double l1 = o1.getWords().size();
                double l2 = o2.getWords().size();
                return l1 == l2 ? 0 : l1 < l2 ? -1 : 1;
            }
        });

        if (puzzles.size() > 0) {
            Board worst = puzzles.get(0);
            Board best = puzzles.get(puzzles.size() - 1);
            System.out.println("Most words: " + best.getWords().size());
            bc.printBoard(best);
        }

    }

}
