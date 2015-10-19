package info.lynxnet.cracking.chapter17;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BucketFiller {
    public static BucketList loadBucket(String fileName) {
        BucketList result = new BucketList();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String s;
            do {
                s = reader.readLine();
                if (s != null) {
                    s = s.toLowerCase().trim();
                    if (s.length() > 0) {
                        result.addWord(s);
                    }
                }
            } while (s != null);
        } catch (IOException e) {
            return null;
        }
        return result;
    }
}
