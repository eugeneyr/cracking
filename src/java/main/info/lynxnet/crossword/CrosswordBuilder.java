package info.lynxnet.crossword;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class CrosswordBuilder implements Callable {
    private int n;
    private BeautifulCrossword context;
    private int i;
    private int j;
    private Direction direction;
    private CrosswordBuilder parent;
    private Board board;

    public CrosswordBuilder(BeautifulCrossword context, Board board, int n, int i, int j, Direction direction) {
        this.n = n;
        this.context = context;
        this.i = i;
        this.j = j;
        this.direction = direction;
        this.board = board;
    }

    public void setParent(CrosswordBuilder parent) {
        this.parent = parent;
    }

    public int getN() {
        return n;
    }

    public BeautifulCrossword getContext() {
        return context;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Direction getDirection() {
        return direction;
    }

    public CrosswordBuilder getParent() {
        return parent;
    }

    public Board getBoard() {
        return board;
    }

    /*
         The outline of the algorithm to implement:

         1) The main loop in more general understanding,
            or the recursive movement, is along the main diagonal: for (int i = 0; i < N; i++)
         2) For each position (i, i) on the diagonal we try to allocate words first in the Ith row (the words "across")
            and then the Ith column (the words "down").
         3) Processing each row / column is as follows:
            - Maintain the current cell index J. Initially set J to 0.
              The "loop":
              Initially, set the "current" position J to 0 - the first cell in the Ith row or Ith column,
              depending in the direction.

              For the current value of J, find the set of the patterns that can be replaced with words
                in the row (column) starting with the cell (I, J)  (or (J, I)).

              If the set is empty:
                  and we are still on the board, do the "increase J or I OR backtrack" routine.

              Otherwise,
                - find all words in D_curr that match the patterns in the set.
                - define the list of possible "actions" as each candidate word from D_curr plus an "empty action" equivalent
                 to placing a word of length 0 on the board, effectively increasing J by 1
                - for each "action" in the list:
                    - place the word w in the action on the board
                    - check if none of the crossword puzzles we already built contain the current board as a subset
                    - if it does, skip to the next action
                    - increase J to J + L(w) + 1
                    - spawn a new instance of the search algorithm using I, J, N, and the copy of the board data

          The "increase J or I OR backtrack" routine:
            - if the current direction == ACROSS, set J to 0 and the direction to DOWN.
            - if the direction is DOWN, set I to I + 1.
            - if I == N - 1, add the current board to the global list of crossword puzzles we built and terminate the current search instance.

            Questions:
            - optimal data structures
            - an efficient way to filter available words by letters in certain positions (prefill some kind of "word buckets")
         */

    @Override
    public Object call() throws Exception {
        context.printBoard(board);
        if (i >= n) {
            /*
            - if I == N - 1, add the current board to the global list of crossword puzzles we built
              and terminate the current search instance.
            */
            context.addKnownPuzzle(board);
            return null;
        }
        if (j >= n) {
            /*
            We reached the end of the "line" (row/column).
            */
            CrosswordBuilder newBuilder = new CrosswordBuilder(context, board, n, i + 1, 0,
                    direction == Direction.ACROSS ? Direction.DOWN : Direction.ACROSS);
            context.execute(newBuilder);
            return null;
        }
        /*
           For the current value of J, find the set of the patterns that can be replaced with words
           in the row (column) starting with the cell (I, J) (or (J, I)).
         */
        Collection<String> patterns = board.getAvailablePatterns(i, j, direction);
        Set<String> candidates = new HashSet<>();
        for (String pattern : patterns) {
            candidates.addAll(context.getStore().getWordsByPattern(pattern));
        }
        candidates.removeAll(board.getWords());
        /*
            - for each "action" in the list:
            - place the word w in the action on the board
            - check if none of the crossword puzzles we already built contain the current board as a subset
            - if it does, skip to the next action
            - increase J to J + L(w) + 1
            - spawn a new instance of the search algorithm using I, J, N, and the copy of the board data
        */
        for (String candidate: candidates) {
            Board newBoard = board.clone();
            WordPlacement newPlacement = new WordPlacement(candidate, i, j, direction);
            newBoard.addWordPlacement(newPlacement);
            if (!context.isSubsetOfAKnownPuzzle(newBoard)) {
                CrosswordBuilder newBuilder = new CrosswordBuilder(context, newBoard, n, i, j + candidate.length() + 1, direction);
                context.execute(newBuilder);

                if (direction == Direction.ACROSS) {
                    CrosswordBuilder downBuilder = new CrosswordBuilder(context, newBoard, n, i, 0, Direction.DOWN);
                    context.execute(downBuilder);
                }
            }
        }
        // the "empty placement" / shift by one
        CrosswordBuilder newBuilder = new CrosswordBuilder(context, board, n, i, j + 1, direction);
        context.execute(newBuilder);

        if (direction == Direction.ACROSS) {
            CrosswordBuilder downBuilder = new CrosswordBuilder(context, board, n, i, 0, Direction.DOWN);
            context.execute(downBuilder);
        }

        return null;
    }
}
