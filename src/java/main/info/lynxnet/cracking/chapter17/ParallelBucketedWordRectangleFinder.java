package info.lynxnet.cracking.chapter17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Word Rectangle:
 * Given a list of millions of words, design an algorithm to create the largest possible rectangle of letters
 * such that every row forms a word (reading from left to right) and every column forms a word (reading top to bottom).
 * The words need not be chosen consecutively from the list but all rows must be the same length and all columns
 * must be the same height.
 */
public class ParallelBucketedWordRectangleFinder {
    public static final String WORDS_FILE = "data/words_long.txt";
    public static final int MAX_WORD_LENGTH = 30;
    private AtomicLong counter = new AtomicLong(0);
    private int largestFound;
    private Set<String> wordList;
    private BucketList buckets;

    public ParallelBucketedWordRectangleFinder(Set<String> wordList, BucketList buckets) {
        this.wordList = wordList;
        this.buckets = buckets;
    }

    public static void getCrossWords(List<String> words, List<String> crossWords) {
        crossWords.clear();
        if (!words.isEmpty()) {
            int wordLen = words.get(0).length();
            for (int i = 0; i < wordLen; i++) {
                String s = "";
                for (String word : words) {
                    s += word.charAt(i);
                }
                crossWords.add(s);
            }
        }
    }

    public static TrieNode loadTrie(String fileName) {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            TrieNode root = new TrieNode();
            String line;
            do {
                line = input.readLine();
                if (line != null && !line.isEmpty()) {
                    line = line.toLowerCase();
                    root.addWord(line);
                }
            } while (line != null);
            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void loadWordList(String fileName, Set<String> words, int maxWordLength) {
        words.clear();
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line;
            do {
                line = input.readLine();
                if (line != null && !line.isEmpty() && (maxWordLength == 0 || line.length() <= maxWordLength)) {
                    line = line.toLowerCase();
                    words.add(line);
                }
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void testLoadWordList() {
        Set<String> words = new HashSet<>();
        loadWordList("data/words_small.txt", words, 0);
        for (String word : words) {
            System.out.println(word);
        }
    }


    static void testLoadTrie() {
        Set<String> words = new HashSet<>();
        loadWordList("data/words_small.txt", words, 0);
        for (String word : words) {
            System.out.println(word);
        }
        TrieNode root = loadTrie("data/words_small.txt");
        for (String word : words) {
            System.out.println(word + ": isPrefix? " + root.hasPrefix(word) + " isWord? " + root.hasWord(word));
        }
        for (String word : words) {
            word += "x";
            System.out.println(word + ": isPrefix? " + root.hasPrefix(word) + " isWord? " + root.hasWord(word));
        }
    }

    void findLargestRectangle(List<List<String>> found,
                              int minWidth, int minHeight,
                              String fileName,
                              boolean ignoreSmaller) {
        Map<Integer, TrieNode> triesByLength = new HashMap<>();
        Map<Integer, List<String>> wordsByLength = new HashMap<>();
        int maxWordLength = 0;
        try (PrintWriter output = new PrintWriter(new FileWriter(fileName != null ? fileName : "/dev/null"))) {
            long timestamp = System.currentTimeMillis();
            found.clear();
            for (String word : wordList) {
                TrieNode trie;
                if (triesByLength.containsKey(word.length())) {
                    trie = triesByLength.get(word.length());
                } else {
                    trie = new TrieNode();
                    triesByLength.put(word.length(), trie);
                }
                trie.addWord(word);

                if (wordsByLength.containsKey(word.length())) {
                    wordsByLength.get(word.length()).add(word);
                } else {
                    List<String> words = new ArrayList<>();
                    words.add(word);
                    wordsByLength.put(word.length(), words);
                }

                if (maxWordLength < word.length()) {
                    maxWordLength = word.length();
                }
            }

            for (int i = maxWordLength; i > minWidth; i--) {
                List<String> horizontalWords = wordsByLength.get(i);
                if (horizontalWords == null) {
                    System.out.println("No words with the length " + i);
                    continue;
                }
                ExecutorService svc = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                try {
                    for (int j = i; j > i - 2; j--) {
                        final int fi = i;
                        final int fj = j;
                        long start = System.currentTimeMillis();
                        System.out.println("Searching for rectangles " + fi + "x" + fj);

                        int chunkSize = (int) Math.round(Math.ceil((double) wordsByLength.get(fi).size() / (double) Runtime.getRuntime().availableProcessors()));

                        List<Callable> callables = new ArrayList<>();
                        final int noOfCPUs = Runtime.getRuntime().availableProcessors();
                        System.out.println(String.format("Will use %d parallel workers", noOfCPUs));
                        for (int idx = 0; idx < noOfCPUs; idx++) {
                            if (idx * chunkSize > wordsByLength.get(fi).size()) {
                                break;
                            }
                            final List<String> chunk = new ArrayList<>(wordsByLength.get(fi).subList(
                                    idx * chunkSize, Math.min(wordsByLength.get(fi).size(), (idx + 1) * chunkSize)));

                            Callable<Void> c = new Callable<Void>() {
                                @Override
                                public Void call() throws Exception {
                                    List<String> words = new ArrayList<>();
                                    searchRectangles(found,
                                            triesByLength,
                                            chunk,
                                            output,
                                            ignoreSmaller,
                                            timestamp, fi, fj, words);
                                    return null;
                                }
                            };
                            callables.add(c);
                        }

                        List<Future<Void>> futures = new ArrayList<>();
                        for (Callable<Void> callable : callables) {
                            Future<Void> f = svc.submit(callable);
                            futures.add(f);
                        }

                        for (Future<Void> f : futures) {
                            f.get();
                        }

                        long end = System.currentTimeMillis();
                        System.out.println("Time spent: " + ((end - start) / 1000));
                    }
                } finally {
                    try {
                        svc.shutdown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void searchRectangles(List<List<String>> found, Map<Integer, TrieNode> triesByLength,
                          List<String> initialWords,
                          PrintWriter output,
                          boolean ignoreSmaller,
                          long timestamp,
                          int length, int height, List<String> words) {
        long counterVal = counter.incrementAndGet();
        if (counterVal % 10000 == 1) {
            long now = System.currentTimeMillis();
            long difference = now - timestamp;
            System.out.println(String.format("Time spent so far: %.3f s", (double) difference / 1000));
            System.out.println(String.format("Variants checked: %d (%.3f per second)", counterVal, counterVal * 1000 / (double) (difference > 0 ? difference : 1)));
        }

        if (ignoreSmaller && (height * length < largestFound)) {
            return;
        }

        if (words.size() > height) {
            return;
        }

        if ((height > 5) && (height - words.size() < 3)) {
            System.out.println("==== Current, tries = " + counterVal + " ====");
            for (String word : words) {
                System.out.println(word);
            }
        }

        if (height == words.size()) {
            synchronized (found) {
                if (largestFound < height * length) {
                    largestFound = height * length;
                }
                List<String> result = new ArrayList<>(words);
                found.add(result);
                System.out.println("==== Found ====");
                output.println("==== Found ====");
                for (String word : words) {
                    System.out.println(word);
                    output.println(word);
                }
            }
            return;
        }

        TrieNode trie = triesByLength.get(height);
        Collection<String> availableWords;

        if (trie != null) {
            if (words.size() == 0) {
                availableWords = initialWords;
            } else {
                List<String> crossWords = new ArrayList<>();
                getCrossWords(words, crossWords);
                Set<String> megaSet = new HashSet<>();
                WordBucket bucket = buckets.getBucket(length);

                for (int i = 0; i < length; i++) {
                    Set<Character> letters = trie.getPossibleNextLetters(crossWords.get(i));
                    if (letters == null || letters.isEmpty()) {
                        System.out.println("Something went wrong, bailing out");
                        return;
                    }
                    Set<String> localSet = new HashSet<>();
                    for (Character c : letters) {
                        localSet.addAll(bucket.getWords(c, i));
                    }
                    if (i == 0) {
                        megaSet.addAll(localSet);
                    } else {
                        megaSet.retainAll(localSet);
                    }
                }
                availableWords = megaSet;
            }

            if (availableWords != null) {
                Set<String> usedWords = new HashSet<>(words);
                for (String word : availableWords) {
                    if (!usedWords.contains(word)) {
                        words.add(word);
                        List<String> crossWords = new ArrayList<>();
                        getCrossWords(words, crossWords);
                        boolean allGood = true;
                        for (String crossWord : crossWords) {
                            if (!trie.hasPrefix(crossWord)) {
                                allGood = false;
                                break;
                            }
                        }

                        if (allGood) {
                            searchRectangles(found, triesByLength,
                                    initialWords,
                                    output,
                                    ignoreSmaller,
                                    timestamp,
                                    length, height, words);
                        }
                        words.remove(words.size() - 1);
                    }
                }
            }
        }
    }

    public static void main(String[] argv) {
        final int WORD_LENGTH = 8;
        final int MIN_WORD_LEN = 7;
        final int MIN_WORD_HEIGHT = 7;
        List<List<String>> found = new ArrayList<>();
        Set<String> wordList = new HashSet<>();
        loadWordList(WORDS_FILE, wordList, WORD_LENGTH);
        BucketList buckets = BucketFiller.loadBucket(WORDS_FILE);
        ParallelBucketedWordRectangleFinder finder = new ParallelBucketedWordRectangleFinder(wordList, buckets);
        finder.findLargestRectangle(found, MIN_WORD_LEN, MIN_WORD_HEIGHT,
                String.format("rect_result.txt-%d-%d.txt", WORD_LENGTH, WORD_LENGTH), true);
        System.out.println(String.format("Total found: %d", found.size()));
    }

}
