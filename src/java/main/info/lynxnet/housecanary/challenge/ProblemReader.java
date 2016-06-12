package info.lynxnet.housecanary.challenge;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemReader {
    public static List<Problem> read(String fileName) {
        List<Problem> result = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String strNoOfTasks = reader.readLine();
            int noOfTasks = Integer.parseInt(strNoOfTasks);
            for (int i = 0; i < noOfTasks; i++) {
                Problem problem = readTask(reader);
                result.add(problem);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Problem readTask(BufferedReader reader) throws IOException {
        String str = reader.readLine();
        String[] parts = str.split(" ");
        int windowRows = Integer.parseInt(parts[0]);
        int windowCols = Integer.parseInt(parts[1]);
        str = reader.readLine();
        parts = str.split(" ");
        int rows = Integer.parseInt(parts[0]);
        int cols = Integer.parseInt(parts[1]);
        long[][] data = new long[rows][cols];
        for (int i = 0; i < rows; i++) {
            str = reader.readLine();
            parts = str.split(" ");
            for (int j = 0; j < cols; j++) {
                data[i][j] = Integer.parseInt(parts[j]);
            }
        }
        return new Problem(data, windowRows, windowCols);
    }
}
