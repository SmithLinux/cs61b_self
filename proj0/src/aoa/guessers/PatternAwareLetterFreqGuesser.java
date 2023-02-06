package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = getFreqMapThatMatchesPattern(pattern);
        Map<Character, Integer> notGuessedMap = getNotGuessedMap(guesses, freqMap);
        int biggestNum = 0;
        char mostCommon = '?';
        for (Map.Entry<Character, Integer> entry : notGuessedMap.entrySet()) {
            if (entry.getValue() > biggestNum) {
                biggestNum = entry.getValue();
                mostCommon = entry.getKey();
            }
        }

        return mostCommon;
    }

    public static Map<Character, Integer> getNotGuessedMap(List<Character> guesses, Map<Character, Integer> freqMap) {
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

    /**
     * Return the frequency map which the length of the words match the pattern.
     */
    public Map<Character, Integer> getFreqMapThatMatchesPattern(String pattern) {
        Map<Character, Integer> letters = new TreeMap<>();
        List<String> matchedWords = getTheLengthMatchedWords(pattern);
        List<String> patternMatchesWords = getThePatternMatchesWords(matchedWords, pattern);
        for(String word : patternMatchesWords) {
            for (int i = 0; i < word.length(); i++) {
                if (!letters.containsKey(word.charAt(i))) {
                    letters.put(word.charAt(i), 1);
                }else {
                    letters.put(word.charAt(i), letters.get(word.charAt(i)) + 1);
                }
            }
        }
        return letters;
    }

    /**
     * Return the list of words which matched the pattern.
     */
    public List<String> getThePatternMatchesWords(List<String> lengthMatchesWords, String pattern) {
        List<String> patternMatchesWords = new ArrayList<>(lengthMatchesWords);

        for (String word : lengthMatchesWords) {
            for (int i = 0; i < word.length(); i++) {
                if (pattern.charAt(i) != '-' && word.charAt(i) != pattern.charAt(i)) {
                    patternMatchesWords.remove(word);
                }
            }
        }
        return patternMatchesWords;
    }

    /**
     * Return the words which match the length of the pattern.
     */
    public List<String> getTheLengthMatchedWords(String pattern) {
        List<String> sameLengthWords = new ArrayList<>();

        for(String word : words) {
            if (word.length() == pattern.length()) {
                sameLengthWords.add(word);
            }
        }
        return sameLengthWords;
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        PatternAwareLetterFreqGuesser palfg2 = new PatternAwareLetterFreqGuesser("data/sorted_scrabble.txt");

        System.out.println(palfg.getGuess("-e--", List.of('e')));
        Map<Character, Integer> lt = palfg.getFreqMapThatMatchesPattern("-e--");
        Map<Character, Integer> lt2 = palfg.getFreqMapThatMatchesPattern("----");
        List<String> mw = palfg.getTheLengthMatchedWords("-e--");
        List<String> pmw = palfg.getThePatternMatchesWords(mw,"-e--");

        List<String> mw2 = palfg2.getTheLengthMatchedWords("-e-a");
        List<String> pmw2 = palfg2.getThePatternMatchesWords(mw2,"-e-a");
        System.out.println(lt);
        System.out.println(lt2);
        System.out.println(mw);
        System.out.println(pmw);
        System.out.println("--------------------------------");
        System.out.println(mw2);
        System.out.println(pmw2);

    }
}