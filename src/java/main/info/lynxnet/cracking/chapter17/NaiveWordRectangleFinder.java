package info.lynxnet.cracking.chapter17;

import java.io.*;
import java.util.*;

/**
 * Word Rectangle:
 * Given a list of millions of words, design an algorithm to create the largest possible rectangle of letters
 * such that every row forms a word (reading from left to right) and every column forms a word (reading top to bottom).
 * The words need not be chosen consecutively from the list but all rows must be the same length and all columns
 * must be the same height.
 */
public class NaiveWordRectangleFinder {
    private long counter;
    private int largestFound;

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

    void findLargestRectangle(Set<String> wordList, List<List<String>> found,
                              int minWidth, int minHeight,
                              String fileName, boolean ignoreSmaller) {
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
                for (int j = i; j > minHeight; j--) {
                    long start = System.currentTimeMillis();
                    System.out.println("Searching for rectangles " + i + "x" + j);
                    List<String> words = new ArrayList<>();
                    searchRectangles(found, triesByLength, wordsByLength, output, ignoreSmaller,
                            timestamp, i, j, words);
                    long end = System.currentTimeMillis();
                    System.out.println("Time spent: " + ((end - start) / 1000));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void searchRectangles(List<List<String>> found, Map<Integer, TrieNode> triesByLength,
                          Map<Integer, List<String>> wordsByLength,
                          PrintWriter output,
                          boolean ignoreSmaller,
                          long timestamp,
                          int length, int height, List<String> words) {
        counter++;
        if (counter % 1000 == 1) {
            long now = System.currentTimeMillis();
            long difference = now - timestamp;
            System.out.println(String.format("Time spent so far: %.3f", (double)difference / 1000));
            System.out.println(String.format("Variants checked: %d (%.3f per second)", counter, counter * 1000 / (double)(difference > 0 ? difference : 1)));
        }

        if (ignoreSmaller && (height * length < largestFound)) {
            return;
        }

        if (words.size() > height) {
            return;
        }

        if (height - words.size() < 3) {
            System.out.println("==== Current, tries = " + counter + " ====");
            for (String word : words) {
                System.out.println(word);
            }
        }

        if (height == words.size()) {
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
            return;
        }

        TrieNode trie = triesByLength.get(height);

        if (trie != null) {
            List<String> availableWords = wordsByLength.get(length);
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
                                    wordsByLength,
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
//    test_loadTrie();
        List<List<String>> found = new ArrayList<>();
        Set<String> wordList = new HashSet<>();
        loadWordList("data/words.txt", wordList, 7);
        NaiveWordRectangleFinder finder = new NaiveWordRectangleFinder();
        finder.findLargestRectangle(wordList, found, 6, 6, "rect_7x7.txt", true);
    }

}
