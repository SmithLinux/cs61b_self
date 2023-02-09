package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    private final static int EMPTY = 0;

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        if (wordLength < 1) throw new IllegalArgumentException();
        pattern = "-";

        List<String> words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        int numWords = words.size();
        if (numWords == 0) {
            throw new IllegalStateException();
        }
        int randomlyChosenNumber = StdRandom.uniform(numWords);
        chosenWord = words.get(randomlyChosenNumber);

        for (int i = 0; i < wordLength - 1; i++) {
            pattern += "-";
        }
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        int occurrences = 0;
        boolean hasGuessed = false;
        for(int i = 0; i < this.chosenWord.length(); i++) {
            if (this.pattern.charAt(i) != '-') {
                hasGuessed = true;
            }
            boolean sameLetter = letter == chosenWord.charAt(i);
            if (sameLetter && !hasGuessed) {
                pattern = pattern.substring(0, i) + letter + pattern.substring(i + 1);
                occurrences++;
            }

            hasGuessed = false;
        }

        return occurrences;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return this.pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return this.chosenWord;
    }

}
