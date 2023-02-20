import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {

    @Test
    public void toListTest() {
        Deque<Integer> all2 = new ArrayDeque<>();
        List<Integer> af = new ArrayList<>();
        for(int i = 9; i >= 0; i--) {
            all2.addFirst(i);
        }

        for (int i = 0; i < 10; i ++) {
            af.add(i);
        }
        List<Integer> list2 = all2.toList();
        assertThat(list2).containsExactlyElementsIn(af).inOrder();
    }

    @Test
    public void getFirstTest() {
        Deque<Integer> all2 = new ArrayDeque<>();
        List<Integer> af = new ArrayList<>();
        for(int i = 99; i >= 0; i--) {
            all2.addFirst(i);
        }

        for (int i = 0; i < 100; i ++) {
            af.add(i);
        }
        List<Integer> list2 = all2.toList();
        assertThat(list2).containsExactlyElementsIn(af).inOrder();
    }

    @Test
    public void getLastTest() {
        Deque<Integer> all2 = new ArrayDeque<>();
        List<Integer> al = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            all2.addLast(i);
        }

        for (int i = 0; i < 100; i ++) {
            al.add(i);
        }
        List<Integer> list2 = all2.toList();
        assertThat(list2).containsExactlyElementsIn(al).inOrder();
    }

    @Test
    public void getFirstTestBasic() {
        Deque<String> lld1 = new ArrayDeque<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

    }

    @Test
    public void addLastTestBasic() {
        Deque<String> lld1 = new ArrayDeque<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque<Integer> lld1 = new ArrayDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    @Test
    public void isEmptyTest() {
        Deque<String> lld1 = new ArrayDeque<>();

        assertThat(lld1.isEmpty()).isEqualTo(lld1.toList().isEmpty());
        lld1.addLast("Scream");
        assertThat(lld1.isEmpty()).isEqualTo(lld1.toList().isEmpty());
    }

    @Test
    public void sizeTest() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addFirst(0);

        int expected = lld1.toList().size();

        assertThat(lld1.size()).isEqualTo(expected);
        lld1.addLast(3);

        expected = lld1.toList().size();
        assertThat(lld1.size()).isEqualTo(expected);

        Deque<Integer> lld2 = new ArrayDeque<>();

        expected = lld2.toList().size();
        assertThat(lld2.size()).isEqualTo(expected);
    }

    @Test
    public void getTest() {
        Deque<String> lld1 = new ArrayDeque<>();

        assertThat(lld1.get(0)).isEqualTo(null);

        lld1.addLast("1");
        lld1.addLast("2");
        lld1.addLast("3");

        assertThat(lld1.get(0)).isEqualTo("1");
    }

    @Test
    public void removeLast() {
        Deque<Integer> all2 = new ArrayDeque<>();
        List<Integer> af = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        int sizeAfterRemoved = 1;

        for(int i = 0; i < 40; i++) {
            all2.addLast(i);
        }

        for(int i = 0; i < 39; i++) {
            af.add(all2.removeLast());
        }

        for (int i = 39; i > 0; i --) {
            expected.add(i);
        }

        assertThat(af).containsExactlyElementsIn(expected).inOrder();
        assertThat(all2.size()).isEqualTo(sizeAfterRemoved);
    }

    @Test
    public void removeFirst() {
        Deque<Integer> all2 = new ArrayDeque<>();
        List<Integer> af = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        int sizeAfterRemoved = 0;

        for(int i = 29; i >= 0; i--) {
            all2.addFirst(i);
        }

        for(int i = 0; i < 30; i++) {
            af.add(all2.removeFirst());
        }

        for (int i = 0; i < 30; i ++) {
            expected.add(i);
        }
        assertThat(af).containsExactlyElementsIn(expected).inOrder();
        assertThat(all2.size()).isEqualTo(sizeAfterRemoved);
    }


    @Test
    public void removeAddResizeTest() {
        Deque<Integer> all = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        Deque<Integer> all2 = new ArrayDeque<>();
        List<Integer> list2 = new ArrayList<>();

        Deque<Integer> all3 = new ArrayDeque<>();
        List<Integer> list3 = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            all.addLast(i);
        }

        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        for (int i = 0; i < 8000; i++) { // 160
            all.removeLast();
        }

        for (int i = 2000; i < 10000; i++) {
            all.addLast(i);
        }

        /**
         */

        for (int i = 0; i < 100; i++) {
            all2.addFirst(i);
        }

        for (int i = 99; i >= 0; i--) {
            list2.add(i);
        }

        for (int i = 0; i < 80; i++) { // 160
            all2.removeLast();
        }

        for (int i = 79; i >= 0; i--) {
            all2.addLast(i);
        }

        /**
         */

        for (int i = 0; i < 100; i++) {
            all3.addFirst(i);
        }

        for (int i = 99; i >= 0; i--) {
            list3.add(i);
        }

        for (int i = 0; i < 80; i++) { // 160
            all3.removeFirst();
        }

        for (int i = 20; i < 100; i++) {
            all3.addFirst(i);
        }

        assertThat(all.toList()).containsExactlyElementsIn(list).inOrder();
        assertThat(all2.toList()).containsExactlyElementsIn(list2).inOrder();
        assertThat(all3.toList()).containsExactlyElementsIn(list3).inOrder();



    }

}
