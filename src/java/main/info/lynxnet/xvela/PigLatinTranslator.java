package info.lynxnet.xvela;

import java.util.HashSet;
import java.util.Set;

public class PigLatinTranslator {
    public static final Set<Character> VOWELS = new HashSet<Character>() {{
        add('a');
        add('e');
        add('i');
        add('o');
        add('u');
    }};

    public static final String ENDING = "ay";


    public static String translateWord(String word) {
        if (word == null) {
            return null;
        }
        if (word.length() == 0 || !Character.isLetter(word.charAt(0))) {
            return word;
        }
        if (word.matches("\\s+")) {
            return word;
        }
        if (VOWELS.contains(Character.toLowerCase(word.charAt(0)))) {
            return word + ENDING;
        }
        String remainder = word.substring(1);
        if (Character.isUpperCase(word.charAt(0))) {
            remainder = remainder.length() > 1 ? remainder.substring(0, 1).toUpperCase() + remainder.substring(1)
                    : remainder.toUpperCase();
        }
        return remainder + Character.toLowerCase(word.charAt(0)) + "ay";
    }

    public static String translateSentence(String sentence) {
        if (sentence == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();

        String[] parts = sentence.split("\\b");
        for (String part : parts) {
            builder.append(translateWord(part));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] words = {"test", "", "another", "x"};
        for (String word : words) {
            System.out.println(word + " -> " + translateWord(word));
        }
        String sentence = "A man, a plan, a canal: Panama!";
        System.out.println(sentence + " -> " + translateSentence(sentence));
    }
}
