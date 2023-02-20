import deque.ArrayDeque;
import deque.Deque;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {

    @Test
    public void equalsTest() {
        Deque<String> ad1 = new ArrayDeque<>();
        Deque<String> ad2 = new ArrayDeque<>();

        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        ad2.addLast("front");
        ad2.addLast("middle");
        ad2.addLast("back");

        assertThat(ad1).isEqualTo(ad2);
    }

    @Test
    public void toStringTest() {
        Deque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");
        String expected = "[front, middle, back]";

        assertThat(ad1.toString()).isEqualTo(expected);
    }
}
