package aoa.guessers;

import aoa.utils.FileUtils;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        List<String> lmw = getLengthMatchesWords(pattern);
        List<String> pmw = getPatternMatchesWords(pattern, lmw);
        List<String> possibleMatchesWords = getPossibleMatchesWords(pattern, pmw, guesses);
        Map<Character, Integer> freqMap = getFrequencyMap(possibleMatchesWords);
        int biggestNum = -1;
        char biggestChar = '?';
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > biggestNum) {
                biggestNum = entry.getValue();
                biggestChar = entry.getKey();
            }
        }
        return biggestChar;
    }

    public Map<Character, Integer> getFrequencyMap(List<String> pmw, List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> frequency = new TreeMap<>();
        boolean isContain = false;

        for (String word : pmw) {
            for (int i = 0; i < word.length(); i++) {
                if(!frequency.containsKey(word.charAt(i))) {
                    for (char c : guesses) {
                        if (word.charAt(i) == c) {
                            isContain = true;
                        }
                    }
                    if (!isContain) {
                        frequency.put(word.charAt(i), 1);
                    }
                    isContain = false;
=======
    public Map<Character, Integer> getFrequencyMap(List<String> pmw) {
        // TODO: Fill in this method.
        Map<Character, Integer> frequency = new TreeMap<>();
        for (String word : pmw) {
            for (int i = 0; i < word.length(); i++) {
                if(!frequency.containsKey(word.charAt(i))) {
                    frequency.put(word.charAt(i), 1);
>>>>>>> 0037528 (proj0: fixing the bug of PAGALetterFreqGuesser)
                }else {
                    frequency.put(word.charAt(i), frequency.get(word.charAt(i)) + 1);
                }
            }
        }
        return frequency;
    }

    public List<String> getLengthMatchesWords(String pattern) {
        List<String> lengthMatchesWords = new ArrayList<>();
        for (String word: words) {
            if (word.length() == pattern.length()) {
                lengthMatchesWords.add(word);
            }
        }
        return lengthMatchesWords;
    }

    /**
     * Return the words which is possible matches.
     */
    public List<String> getPossibleMatchesWords(String pattern, List<String> patternMatchesWords, List<Character> guesses) {
<<<<<<< HEAD
        List<String> pmw = new ArrayList<>(patternMatchesWords);
        for (String word : patternMatchesWords) {
            for (int i = 0; i < word.length(); i++) {
                if(pattern.charAt(i) == '-') {
                    for (char c : guesses) {
                        if (word.charAt(i) == c) {
                            pmw.remove(word);
                        }
                    }
                }
            }
        }

        return pmw;
=======
        boolean isContain = false;
        List<String> possibleMatchesWords = new ArrayList<>();
        for (String word : patternMatchesWords) {
            for (char c : guesses) {
                if (word.contains(String.valueOf(c))) {
                    isContain = true;
                }
            }
            if (!isContain) {
                possibleMatchesWords.add(word);
            }
            isContain = false;
        }

        return possibleMatchesWords;
>>>>>>> 0037528 (proj0: fixing the bug of PAGALetterFreqGuesser)
    }

    /**
     * Return the words which matches the pattern.
     */
    public List<String> getPatternMatchesWords(String pattern, List<String> lengthMatchesWords) {
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

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        List<String> lmw = pagalfg.getLengthMatchesWords("----");
        List<String> lmw2 = pagalfg.getPatternMatchesWords("-e--", lmw);
        System.out.println(lmw);
        System.out.println(lmw2);
<<<<<<< HEAD
        List<String> pmw = pagalfg.getPossibleMatchesWords("-e--",lmw, List.of('e'));
        System.out.println(pmw);
        System.out.println(pagalfg.getGuess("----", List.of('e')));


        System.out.println("-------------------");
=======
        List<String> pmw = pagalfg.getPossibleMatchesWords(lmw, List.of('e'));
        System.out.println(pmw);
        System.out.println(pagalfg.getGuess("----", List.of('e')));

>>>>>>> 0037528 (proj0: fixing the bug of PAGALetterFreqGuesser)
        PAGALetterFreqGuesser palfg = new PAGALetterFreqGuesser("data/sorted_scrabble.txt");
        System.out.println(palfg.getGuess("be-", List.of('e')));
    }
}
