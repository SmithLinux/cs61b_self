import deque.MaxArrayDeque;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {

    private static class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    private static class CharComparator implements Comparator<Character> {

        @Override
        public int compare(Character o1, Character o2) {
            return o1.compareTo(o2);
        }
    }

    @Test
    public void maxTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntegerComparator());
        Integer expected = 9;
        Integer maximum;

        for (int i = 0; i < 10; i++) {
            mad.addLast(i);
        }

        maximum = mad.max();
        assertThat(maximum).isEqualTo(expected);
    }

    @Test
    public void maxComparatorTest() {
        MaxArrayDeque<Character> mad = new MaxArrayDeque<>(new CharComparator());
        Character expected = 'Z';
        Character maximum;

        for (int i = 0; i < 26; i++) {
            mad.addLast((char)('A' + i));
        }

        maximum = mad.max(new CharComparator());
        assertThat(maximum).isEqualTo(expected);
    }


}
