package info.lynxnet.crossword;

import java.util.*;
import java.util.concurrent.*;

public class ParallelBeautifulCrossword extends BeautifulCrossword {
    protected BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>(100000);
    protected ExecutorService service = new ThreadPoolExecutor(
            2, Runtime.getRuntime().availableProcessors(),
            10, TimeUnit.SECONDS, queue, new ThreadPoolExecutor.CallerRunsPolicy());

    public ExecutorService getService() {
        return service;
    }

    public long getActiveCount() {
        return ((ThreadPoolExecutor) service).getActiveCount();
    }

    public long getQueuedSubmissionCount() {
        return queue.size();
    }

    @Override
    public String getState() {
        return String.format("%s active tasks = %d queued submissions = %d ***",
                super.getState(), getActiveCount(), getQueuedSubmissionCount());
    }

    @Override
    public synchronized void addKnownPuzzle(Board board) {
        super.addKnownPuzzle(board);
    }

    @Override
    public synchronized void printBoard(Board board) {
        super.printBoard(board);
    }

    @Override
    public synchronized boolean isSubsetOfAKnownPuzzle(Board board) {
        return super.isSubsetOfAKnownPuzzle(board);
    }

    public ParallelBeautifulCrossword() {
    }

    @Override
    protected synchronized boolean checkAndAddBuilder(CrosswordBuilder builder) {
        return super.checkAndAddBuilder(builder);
    }

    public void execute(CrosswordBuilder builder) {
        try {
            service.submit(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String[] generateCrossword(int N, int[] weights) {
        n = N;
        this.weights = weights;
        Board board = new Board(n);
        execute(new CrosswordBuilder(this, board, n, 0, Direction.ACROSS));
        while (!queue.isEmpty()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Board> puzzles = new ArrayList<>(getBestPuzzles());
        return puzzles.size() > 0 ? puzzles.get(puzzles.size() - 1).asStringArray() : null;
    }

    public static void main(String[] args) {
        String fileName = args[0];
        ParallelBeautifulCrossword bc = new ParallelBeautifulCrossword();
        bc.generateCrossword(11, fileName, new int[]{6, 8, 7, 10});
        List<Board> puzzles = new ArrayList<>(bc.getBestPuzzles());
        Collections.sort(puzzles, new WeightComparator(bc));

        if (puzzles.size() > 0) {
            Board best = puzzles.get(puzzles.size() - 1);
            System.out.println("Best:");
            bc.printBoard(best);
        }

        // By the number of words
        puzzles = new ArrayList<>(bc.getKnownPuzzles());
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
            Board best = puzzles.get(puzzles.size() - 1);
            System.out.println("Most words: " + best.getWords().size());
            bc.printBoard(best);
        }
    }
}
