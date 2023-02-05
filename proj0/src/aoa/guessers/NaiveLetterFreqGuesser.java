package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.
        Map<Character, Integer> frequency = new TreeMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if(!frequency.containsKey(word.charAt(i))) {
                    frequency.put(word.charAt(i), 1);
                }else {
                    frequency.put(word.charAt(i), frequency.get(word.charAt(i)) + 1);
                }
            }
        }
        return frequency;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        Map<Character, Integer> noGuessedMap = notGuessedMap(guesses, getFrequencyMap());
        int biggestNum = -1;
        char biggestChar = '?';
        for (Map.Entry<Character, Integer> entry : noGuessedMap.entrySet()) {
            if (entry.getValue() > biggestNum) {
                biggestNum = entry.getValue();
                biggestChar = entry.getKey();
            }
        }
        return biggestChar;
    }

    public static Map<Character, Integer> notGuessedMap(List<Character> guesses, Map<Character, Integer> freqMap) {
        if (guesses.size() == 0) {
            return freqMap;
        }
        boolean isContain = false;
        Map<Character, Integer> noContainedMap = new TreeMap<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            for (char c : guesses) {
                if (c == entry.getKey()) {
                    isContain = true;
                }
            }
            if (!isContain) {
                noContainedMap.put(entry.getKey(), entry.getValue());
            }
            isContain = false;
        }
        return noContainedMap;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
