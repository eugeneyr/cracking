package info.lynxnet.crossword;

import java.util.*;
import java.util.concurrent.Callable;

public class CrosswordBuilder implements Callable<Void> {
    private int n;
    private BeautifulCrossword context;
    private int i;
    private Board board;
    private Direction direction;

    public CrosswordBuilder(BeautifulCrossword context, Board board, int n, int i, Direction direction) {
        this.n = n;
        this.context = context;
        this.i = i;
        this.board = board;
        this.direction = direction;
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

    public Direction getDirection() {
        return direction;
    }

    public Board getBoard() {
        return board;
    }

    /*
         The outline of the algorithm to implement:

         * The direction is along he main diagonal, from (0, 0) to (N - 1, N - 1)

         * The prerequisites for each step:
           - the "current" board B0
           - the value of I

         * The "action":
           - if I = N, add B0 to the list of results.

           - generate all possible ways to place words not used in B1 on the Ith row of B0.
             The outcome is a collection (list) WPaccr of WordPlacement lists.
             (One of the elements will be an empty list)

           - For each element (WordPlacement list) WPj in WPaccr:
             - create a new board B1 by placing WPj on B0
             - generate all possible ways to place words not used in B1 on the Ith column of B1.
               The outcome is a collection (list) WPdown of WordPlacement lists.
               (One of the elements will be an empty list)

               - For each element WPk in WPdown:
                 - create a new board B2 by placing WPk on B1 and launch a new instance of the builder with B2, I + 1

         * Postprocessing:
             - range the result list by the score.
             - pick the top score one.
             + Variation: store only the result with the highest score.

         For first few rows the number of elements in WPaccr and WPdown
         */

    @Override
    public Void call() throws Exception {
        long myNo = Metrics.builderInstances.incrementAndGet();
        if (myNo % 1000000 == 0) {
            System.out.printf("Instantiated builders = %d %s\nCurrent Board:\n",
                    myNo,
                    context.getState());
            context.printBoard(board);
        }
        // Prevent repeatedly going down same search trees.
//        if (context.isSubsetOfAKnownPuzzle(board)) {
//            long reps = Metrics.identicalBoards.incrementAndGet();
//            if (reps % 1000 == 0) {
//                System.out.println(String.format("Prevented repeats = %d *** I = %d *** DIR = %s", reps, i, direction.toString()));
//            }
//            return null;
//        }

        // - if I = N, add B0 to the list of results.
        if (i >= n) {
            context.addKnownPuzzle(board);
            return null;
        }

        Collection<Collection<WordPlacement>> permutations = getAllPermutations(board, i, direction);

        for (Collection<WordPlacement> perm : permutations) {
            Board newBoard = board.clone();
            for (WordPlacement wp : perm) {
                try {
                    newBoard.addWordPlacement(wp);
                } catch (IllegalArgumentException e) {
                    System.out.println("WRONGER:");
                    context.printBoard(board);
                    System.out.println("BAD APPLE: " + wp.toString());
                    e.printStackTrace(System.out);
                    System.exit(-1);
                }
            }
            CrosswordBuilder newBuilder = null;
            switch (direction) {
                case ACROSS:
                    newBuilder = new CrosswordBuilder(context, newBoard, n, i, Direction.DOWN);
                    break;
                case DOWN:
                    newBuilder = new CrosswordBuilder(context, newBoard, n, i + 1, Direction.ACROSS);
                    break;
            }
            context.execute(newBuilder);
        }
        return null;
    }

    static class PermutationComparator implements Comparator<Collection<WordPlacement>> {
        private Board board;
        private int n;
        private int i;
        private Direction direction;

        public PermutationComparator(Board board, int n, int i, Direction direction) {
            this.board = board;
            this.n = n;
            this.i = i;
            this.direction = direction;
        }

        @Override
        public int compare(Collection<WordPlacement> o1, Collection<WordPlacement> o2) {
            // @TODO Build a comparator that picks the best permutations for the current row / column.
            // Further @TODO: Figure the way to use weights in it.
            return 0;
        }
    }
    public Collection<Collection<WordPlacement>> getAllPermutations(Board board, int i, Direction direction) {
        Set<Collection<WordPlacement>> result = new TreeSet<>(new PermutationComparator(board, n, i, direction));
        int x = direction == Direction.ACROSS ? 0 : i;
        int y = direction == Direction.ACROSS ? i : 0;

        generatePermutations(board, x, y, direction, Collections.EMPTY_SET, result, board.getWords());

        return result;
    }

    private void generatePermutations(
            Board board, int x, int y, Direction direction, Collection<WordPlacement> perm,
            Collection<Collection<WordPlacement>> result,
            Collection<String> blacklist) {
        if (x >= n || y >= n) {
            result.add(perm);
            return;
        }
        Map<String, Collection<String>> candidates = getWordPlacementCandidates(board, x, y, direction, blacklist);
        for (Map.Entry<String, Collection<String>> entry : candidates.entrySet()) {
            Collection<String> words = entry.getValue();
            for (String word : words) {
                WordPlacement newPlacement = new WordPlacement(word, x, y, direction);
                Set<WordPlacement> newPerm = new HashSet<>(perm);
                newPerm.add(newPlacement);
                Set<String> newBlackList = new HashSet<>(blacklist);
                newBlackList.add(word);
                int newX = direction == Direction.ACROSS ? x + word.length() + 1 : x;
                int newY = direction == Direction.DOWN ? y + word.length() + 1 : y;
                generatePermutations(board, newX, newY, direction, newPerm, result, newBlackList);
            }
        }
        // handle the empty string/set case
        int newX = direction == Direction.ACROSS ? x + 1 : x;
        int newY = direction == Direction.DOWN ? y + 1 : y;
        generatePermutations(board, newX, newY, direction, perm, result, blacklist);
    }

    private Map<String, Collection<String>> getWordPlacementCandidates(
            Board board, int i, int j, Direction direction, Collection<String> blacklist) {
        Collection<String> patterns = board.getAvailablePatterns(i, j, direction);
        Map<String, Collection<String>> candidates = new HashMap<>();
        if (blacklist == null) {
            blacklist = board.getWords();
        }
        for (String pattern : patterns) {
            Set<String> words = context.getStore().getWordsByPattern(pattern, blacklist);
            if (!words.isEmpty()) {
                candidates.put(pattern, context.getStore().getWordsByPattern(pattern, blacklist));
            }
        }
        return candidates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        System.out.println("************************************ CrosswordBuilder.equals: *****************************************");
//        System.out.println(this.toString());
//        System.out.println(o.toString());
//        new Exception().printStackTrace(System.out);
//        System.out.println("*******************************************************************************************************");
        CrosswordBuilder that = (CrosswordBuilder) o;
//        if (Objects.equals(board, that.board)) {
//            Metrics.identicalBoards.incrementAndGet();
//            System.out.printf(
//                    "Identical boards, this.i = %d that.i = %d this.dir = %s that.dir = %s\n",
//                    i, that.i, direction, that.direction);
//        }
        return n == that.n &&
                i == that.i &&
                Objects.equals(board, that.board) &&
                direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, i, board, direction);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CrosswordBuilder{");
        sb.append("n=").append(n);
        sb.append(", i=").append(i);
        sb.append(", direction=").append(direction);
        sb.append(", board=\n").append(String.join("\n", board.asStringArray()));
        sb.append("\n}");
        return sb.toString();
    }
}
