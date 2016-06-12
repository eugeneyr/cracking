package info.lynxnet.housecanary.challenge;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ProblemSolver {
    private Problem problem;

    public ProblemSolver(Problem problem) {
        this.problem = problem;
    }

    private long sum(long[] arr, int start, int end) {
        long tmp = 0;
        for (int i = start; i < end; i++) {
            tmp += arr[i];
        }
        return tmp;
    }

    public Long solve() {
        if (problem == null) {
            return null;
        }
        if (problem.getData() == null) {
            return null;
        }
        long[][] data = problem.getData();
        if (problem.getWindowRows() <= 0 || problem.getWindowCols() <= 0) {
            return null;
        }
        if (data.length < problem.getWindowRows()) {
            return null;
        }
        if (data[0] == null) {
            return null;
        }
        int rows = data.length;
        int cols = data[0].length;
        if (cols < problem.getWindowCols()) {
            return null;
        }
        int windowRows = problem.getWindowRows();
        int windowCols = problem.getWindowCols();
        long maxSum = 0;

        long[][] rowSums = new long[rows][cols - windowCols + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - windowCols + 1; j++) {
                if (j == 0) {
                    rowSums[i][j] = sum(data[i], j, j + windowCols);
                } else {
                    rowSums[i][j] = rowSums[i][j - 1] + data[i][j + windowCols - 1] - data[i][j - 1];
                }
            }
        }
        for (int j = 0; j < cols - windowCols + 1; j++) {
            long [] sumCol = new long[rows - windowRows + 1];
            for (int i = 0; i < rows - windowRows + 1; i++) {
                long theSum;
                if (i == 0) {
                    long tmpSum = 0;
                    for (int i1 = 0; i1 < windowRows; i1++) {
                        tmpSum += rowSums[i1][j];
                    }
                    theSum = tmpSum;
                } else {
                    theSum = sumCol[i - 1] - rowSums[i - 1][j] + rowSums[i + windowRows - 1][j];
                }
                if (theSum > maxSum) {
                    maxSum = theSum;
                }
                sumCol[i] = theSum;
            }
        }

        return maxSum;
    }

    public static void solveProblems(String dirName) {
        for (int i = 0; i < 4; i++) {
            String fileName = MessageFormat.format("{0}/inputs/input{1}.txt", dirName, i);
            List<Problem> problems = ProblemReader.read(fileName);
            List<Long> solutions = new ArrayList<>();
            System.out.println(fileName);
            for (Problem problem: problems) {
                ProblemSolver solver = new ProblemSolver(problem);
                Long result = solver.solve();
                System.out.println(result != null ? result : "None");
                solutions.add(result);
            }
        }
    }

    public static void main(String[] args) {
        solveProblems(args[0]);
    }
}
