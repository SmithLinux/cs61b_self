package aoa.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in this constructor.
        if (wordLength < 1) throw new IllegalArgumentException();
        pattern = "-";

        wordPool  = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        int numWords = wordPool.size();
        if (numWords == 0) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < wordLength - 1; i++) {
            pattern += "-";
        }
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        Map<String, List<String>> possibleWords = new TreeMap<>();
        String wordPattern = String.valueOf(this.pattern);
        int occurrences = 0;
        boolean hasGuessed = false;
        for (String word: wordPool) {
            for (int i = 0; i < word.length(); i ++) {
                if (this.pattern.charAt(i) != '-') {
                    hasGuessed = true;
                }
                boolean sameLetter = letter == word.charAt(i);
                if (sameLetter && !hasGuessed) {
                    wordPattern = wordPattern.substring(0, i) + letter + wordPattern.substring(i + 1);
                    occurrences++;
                }
                hasGuessed = false;
            }
            if (!possibleWords.containsKey(wordPattern)) {
                possibleWords.put(wordPattern, new ArrayList<>());
                possibleWords.get(wordPattern).add(word);
            } else {
                possibleWords.get(wordPattern).add(word);
            }
            wordPattern = String.valueOf(this.pattern);
        }
        int mostWords = 0;
        String key = "";
        for(Map.Entry<String, List<String>> entry: possibleWords.entrySet()) {
            int oneOfSize = entry.getValue().size();
            if (oneOfSize > mostWords) {
                key = entry.getKey();
                mostWords = oneOfSize;
            }
        }
        wordPool = possibleWords.get(key);
        int count = 0;
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) != this.pattern.charAt(i)) {
                count++;
            }
        }
        this.pattern = key;
        //return the number of occurrences of the guessed letter in the new pattern.
        return count;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return this.pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return this.wordPool.get(0);
    }

    public static void main(String[] args) {
        EvilChooser ec = new EvilChooser(4, "data/example.txt");
        ec.makeGuess('e');
        System.out.println(ec.wordPool);
    }
}
