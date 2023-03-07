package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;

    private final Map<String, TimeSeries> wordsMap;
    private final TimeSeries counts;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     * words file
     * 1. word 2. year 3. number of times that the word appeared 4.ignore
     * counts file
     * 1. year
     * 2. the total number of words recorded from all texts that year
     * 3.ignore the total number of pages of text from that year
     * 4.ignore the total number of distinct sources from that year
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordsMap = getSeparateWordsFile(new In(wordsFilename));
        counts = getSeparateCountsFile(new In(countsFilename));
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(wordsMap.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy,
     * not a link to this NGramMap's TimeSeries. In other words, changes made
     * to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word) {
        return new TimeSeries(wordsMap.get(word), MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return counts;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries wordsHistory = countHistory(word, startYear, endYear);
        TimeSeries countsHistory = new TimeSeries(counts, startYear, endYear);

        return wordsHistory.dividedBy(countsHistory);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to
     * all words recorded in that year. If the word is not in the data files, return an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries wordsHistory = countHistory(word);
        int startYear = 0;
        int endYear = 0;
        for (Map.Entry<Integer, Double> entry : wordsHistory.entrySet()) {
            if (startYear == 0) {
                startYear = entry.getKey();
                endYear = entry.getKey();
            }
            if (entry.getKey() > startYear) {
                endYear = entry.getKey();
            }
        }
        TimeSeries countsHistory = new TimeSeries(counts, startYear, endYear);
        return wordsHistory.dividedBy(countsHistory);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS
     * between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     * this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {

        TimeSeries sumTS = new TimeSeries();
        for (String word : words) {
            boolean isExist = true;

            for (int i = startYear; i <= endYear; i++) {
                if (this.wordsMap.get(i) == null) {
                    isExist = false;
                }
            }
            if (isExist) {
                sumTS = sumTS.plus(weightHistory(word, startYear, endYear));
            }
        }
        return sumTS;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries sumTS = new TimeSeries();
        for (String word : words) {
            sumTS = sumTS.plus(weightHistory(word));
        }
        return sumTS;
    }

    private TimeSeries getSeparateCountsFile(In countsFile) {
        String[] strs;
        TimeSeries tm = new TimeSeries();

        while (!countsFile.isEmpty()) {
            strs = countsFile.readString().split(",", 3);
            tm.put(Integer.valueOf(strs[0]), Double.valueOf(strs[1]));
        }

        return tm;
    }

    private Map<String, TimeSeries> getSeparateWordsFile(In wordsFile) {
        Map<String, TimeSeries> map = new TreeMap<>();

        String word;
        int year;
        double appeared;
        TimeSeries tm = null;

        while (!wordsFile.isEmpty()) {
            word = wordsFile.readString();
            year = wordsFile.readInt();
            appeared = wordsFile.readDouble();

            if (!map.containsKey(word)) {
                tm = new TimeSeries();
            }
            
            tm.put(year, appeared);
            map.put(word, tm);
            wordsFile.readInt();
            
        }

        return map;
    }
}
