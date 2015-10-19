package info.lynxnet.cracking.chapter17;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    boolean stopper = false;
    Map<Character, TrieNode> letters = new HashMap<>();

    public TrieNode() {
    }

    public void addWord(String word) {
        if (word == null || word.isEmpty()) {
            this.stopper = true;

            return;
        }
        char head = word.charAt(0);
        String tail = word.substring(1);
        TrieNode nextNode = null;
        if (this.letters.containsKey(head)) {
            nextNode = this.letters.get(head);
        } else {
            nextNode = new TrieNode();
            this.letters.put(head, nextNode);
        }
        if (nextNode != null) {
            nextNode.addWord(tail);
        }
    }

    public boolean hasWord(String word) {
        if (word == null || word.isEmpty()) {
            return this.stopper;
        }
        char head = word.charAt(0);
        String tail = word.substring(1);
        return this.letters.containsKey(head) && this.letters.get(head).hasWord(tail);
    }

    public boolean hasPrefix(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return true;
        }
        char head = prefix.charAt(0);
        String tail = prefix.substring(1);
        return this.letters.containsKey(head) && this.letters.get(head).hasPrefix(tail);
    }


}
