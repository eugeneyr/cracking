package info.lynxnet.crossword;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordBucketFiller {
    public static WordBucket loadBucket(String fileName) {
        WordBucket result = new WordBucket();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String s;
            do {
                s = reader.readLine();
                if (s != null) {
                    s = s.toUpperCase().trim();
                    if (s.length() > 0) {
                        result.addWord(s);
                    }
                }
            } while (s != null);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return null;
        }
        return result;
    }

    public static WordBucket loadBucket(String[] dictionary) {
        WordBucket result = new WordBucket();
        if (dictionary != null) {
            for (String s : dictionary) {
                if (s == null) {
                    continue;
                }
                s = s.toUpperCase().trim();
                if (s.length() > 0) {
                    result.addWord(s);
                }
            }
        }
        return result;
    }

}
