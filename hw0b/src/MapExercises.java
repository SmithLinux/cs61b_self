import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> alphabet = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            alphabet.put(c, i + 1);
        }
        return alphabet;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> square = new HashMap<>();
        for (int i: nums) {
            square.put(i, i * i);
        }
        return square;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> count = new HashMap<>();
        for (String str : words) {
            if (!count.containsKey(str)) {
                count.put(str, 1);
            }else {
                count.put(str, count.get(str) + 1);
            }
        }
        return count;
    }
}
