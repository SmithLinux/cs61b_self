import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

/**
 * Invariants
 * 1. The size variable is always the total number of items that have been added.
 * 2. The item variable is always the array which store the item.
 * 3. The front item (if it exists), is always at this.nextFirst + 1.
 * 4. The last item (if it exists), is always at this.nextLast - 1.
 * 5. The nextFirst is always point to the first empty array space, if that point to the end of the array, it will go to the back of the array if there has a space.
 * 6. The nextLast is always point to the last empty array space, if that point to the end of the array, it will go to the front of the array if there has a space.
 * 7. Every initial arrayDeque the first is always at the last - 1;
 */
public class ArrayDeque<T> implements Deque<T> {
    private T[] item;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static final double RFACTOR = 0.25;
    private static final double FACTOR = 2;

    public ArrayDeque() {
        this.item = (T[]) new Object[10];
        this.size = 0;
        this.nextFirst = this.item.length / 2 - 1;
        this.nextLast = this.item.length / 2;
    }
    @Override
    public void addFirst(T x) {
        if (this.size == this.item.length) {
            this.resize((int) FACTOR);
        }

        if(this.nextFirst == -1) {
            this.nextFirst = this.item.length - 1;
        }
        this.item[this.nextFirst] = x;

        if (this.nextFirst != this.item.length / 2) {
            this.nextFirst--;
        }

        this.size++;

    }

    public void resize(int factor) {
        //two strategy for resize
        //1.if the nextFirst > nextLast means the nextFirst is going to the back of the list
        //2.if the nextLast < nextFirst means the nextLast is going to the front of the list
        //3.no one goes further, peace!
        T[] newItem;
        int newFirst;
        int newLast;
        int capacity;
        if (factor == FACTOR) { //resizing up
            capacity = (int) (this.item.length * FACTOR);
            newItem = (T[]) new Object[capacity];
            newFirst = newItem.length / 2 - 1;
            newLast = newItem.length / 2;
            for (int i = 0; i < this.item.length; i++) {
                newItem[newLast] = this.get(i);
                newLast++;
            }
            this.item = newItem;
            this.nextLast = newLast;
            this.nextFirst = newFirst;
        }
    }

    @Override
    public void addLast(T x) {
        if (this.size == this.item.length) {
            this.resize((int) FACTOR);
        }

        if(this.nextLast == this.item.length) {
            this.nextLast = 0;
        }
        this.item[this.nextLast] = x;

        if (this.nextLast != this.item.length / 2 - 1) {
            this.nextLast++;
        }

        this.size++;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            returnList.add(this.get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        T removed = this.item[this.nextFirst + 1];
        this.item[this.nextFirst + 1] = null;
        this.nextFirst = this.nextFirst + 1;
        return removed;
    }

    @Override
    public T removeLast() {
        T removed = this.item[this.nextLast - 1];
        this.item[this.nextFirst - 1] = null;
        this.nextLast = this.nextLast - 1;
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || size == 0) {
            return null;
        }
        int upper = this.nextLast - 1;
        int lower = this.nextFirst + 1;
        if (this.nextFirst == this.item.length / 2) {
            lower = this.nextFirst;
        } else if (this.nextLast == this.item.length / 2 - 1) {
            upper = this.nextLast;
        }



        if (index >= size) {
            return null;
        }

        if (upper < this.nextFirst) { //the nextLast has been to the left of the array [6,7,n,n,2,3,4,5]
            int rightSize = this.item.length - lower;
            if (index < rightSize) {
                return this.item[index + lower];
            }else {
                return this.item[index - rightSize];
            }
        }else if (lower > this.nextLast) { //the nextFirst has been to the right of the array [3,2,1,0,n,n,5,4]
            int leftSize = this.item.length - upper - 1;
            if (index < leftSize) {
                return this.item[index + upper + 1];
            }else {
                return this.item[index - leftSize];
            }
        }else {
            if (index + lower > upper) {
                return null;
            }
            return item[index + lower];
        }

    }


    public static void main(String[] args) {
//        Deque<Integer> af = new ArrayDeque<>();
//        List<Integer> arr = new ArrayList<>();
        Deque<Integer> al = new ArrayDeque<>();
        List<Integer> lastArray = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            af.addFirst(i);
//        }
//        System.out.println("get first test");
//        for (int i = 0; i < 15; i++) {
//            arr.add(af.get(i));
//        }
//        System.out.println(arr);
        System.out.println("over!");
        for (int i = 0; i < 15; i++) {
            al.addLast(i);
        }
        System.out.println("get last test");
        for (int i = 0; i < 15; i++) {
            lastArray.add(al.get(i));
        }
        System.out.println(lastArray);
        System.out.println("over!");
    }
}
