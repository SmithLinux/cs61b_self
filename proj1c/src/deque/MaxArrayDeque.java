package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.c = c;
    }

    /**
     * returns the maximum element in the deque as governed by the previously given Comparator. If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        if (this.size() == 0) {
            return null;
        }

        return compareHelper(this.c);
    }

    public T max(Comparator<T> comparator) {
        if (this.size() == 0) {
            return null;
        }

        return compareHelper(comparator);
    }

    private T compareHelper(Comparator<T> comparator) {
        T maximum;
        T nextItem;
        Iterator<T> it = this.iterator();
        maximum = it.next();

        while (it.hasNext()) {
            nextItem = it.next();
            if (comparator.compare(maximum, nextItem) < 0) {
                maximum = nextItem;
            }
        }

        return maximum;
    }
}
