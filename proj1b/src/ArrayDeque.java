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

        if(this.nextFirst == -1) { // if the space of left side is full.
            this.nextFirst = this.item.length - 1;
        }
        this.item[this.nextFirst] = x;

        this.nextFirst--;
        this.size++;

    }

    public void resize(double factor) {
        //two strategy for resize
        //1.if the nextFirst > nextLast means the nextFirst is going to the back of the list
        //2.if the nextLast < nextFirst means the nextLast is going to the front of the list
        //3.no one goes further, peace!
        T[] newItem;
        int newFirst = 2;
        int newLast = newFirst + 1;

        if (factor == FACTOR) { //resizing up
            newItem = getResizeT(FACTOR);

            relocate(newFirst, newLast, newItem);
        }

        if (factor == RFACTOR) { //resizing down
            newItem = getResizeT(RFACTOR * 2);

            relocate(newFirst, newLast, newItem);
        }
    }

    private void relocate(int newFirst, int newLast, T[] newItem) {
        for (int i = 0; i < this.size; i++) {
            newItem[newLast] = this.get(i);
            newLast++;
        }

        this.item = newItem;
        this.nextLast = newLast;
        this.nextFirst = newFirst;
    }

    private T[] getResizeT(double factor) {
        T[] newItem;
        int capacity;
        capacity = (int) (this.item.length * factor);
        newItem = (T[]) new Object[capacity];
        return newItem;
    }

    @Override
    public void addLast(T x) {
        if (this.size == this.item.length) {
            this.resize((int) FACTOR);
        }

        if(this.nextLast == this.item.length) { // if the space of right side is full.
            this.nextLast = 0;
        }
        this.item[this.nextLast] = x;

        this.nextLast++;
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
        if (size == 0) {
            return null;
        }

        if (((float)size / (float)this.item.length) <= RFACTOR && this.item.length > 10) {
            resize(RFACTOR);
        }

        T removed = this.item[this.nextFirst + 1];
        this.item[this.nextFirst + 1] = null;
        this.nextFirst = this.nextFirst + 1;

        if (this.nextFirst + 1 == this.item.length) {
            this.nextFirst = -1;
        }


        this.size = this.size - 1;
        return removed;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (((float)size / (float)this.item.length) <= RFACTOR && this.item.length > 10) {
            resize(RFACTOR);
        }

        T removed = this.item[this.nextLast - 1];
        this.item[this.nextLast - 1] = null;
        this.nextLast = this.nextLast - 1;

        if (this.nextLast == 0) {
            this.nextLast = this.item.length;
        }

        this.size = this.size - 1;
        return removed;
    }

    @Override
    public T get(int index) {
        if (size == 0) {
            return null;
        }
        if (index >= size) {
            return null;
        }
        /**
         * three condition
         * FINISHED 3.nextFirst < nextLast.
         * the answer is no matter where is nextFirst or nextLast at, we always count from the left to the right
         */
        int reallyIndex;
        if (this.nextFirst == this.nextLast) {
            reallyIndex = this.nextFirst + index;
        }else {
            reallyIndex = this.nextFirst + index + 1;
        }

        if (reallyIndex >= this.item.length) {
            return this.item[reallyIndex - this.item.length];
        }
        return this.item[reallyIndex];


    }


    public static void main(String[] args) {
        Deque<Integer> af = new ArrayDeque<>();
        List<Integer> arr = new ArrayList<>();
//        Deque<Integer> al = new ArrayDeque<>();
//        List<Integer> lastArray = new ArrayList<>();
        /**
         * test for regular get method.
         */
//        for (int i = 0; i < 5; i++) {
//            af.addFirst(i);
//            af.addLast(i);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            arr.add(af.get(i));
//        }

        /**
         * test for nextFirst method which nextFirst have been to the left .
         */

//        for (int i = 10; i > 0; i--) {
//            af.addFirst(i);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            arr.add(af.get(i));
//        }

        /**
         * test for nextLast method which nextLast have been to the right .
         */
//
//        for (int i = 0; i < 10; i++) {
//            af.addLast(i);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            arr.add(af.get(i));
//        }

//        System.out.println("get first test");
//        for (int i = 0; i < 15; i++) {
//            arr.add(af.get(i));
//        }
        System.out.println(arr);
        System.out.println("over!");
//        for (int i = 0; i < 10; i++) {
//            al.addLast(i);
//        }
//        System.out.println("get last test");
//        for (int i = 0; i < 15; i++) {
//            lastArray.add(al.get(i));
//        }
//        System.out.println(al);
//        System.out.println("over!");
    }
}

/**
 * functions which need to refactor are:
 * removeFirst() removeLast() resize();
 */