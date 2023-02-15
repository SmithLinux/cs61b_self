import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

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
        if (factor == FACTOR) {
            int middle = this.item.length / 2;
            if (this.nextFirst >= middle) { //nextFirst has came to the right
                capacity = (int) (this.item.length * FACTOR);
                newItem = (T[]) new Object[capacity];
                newFirst = newItem.length / 2 - 1;
                newLast = newItem.length / 2;
                // [1,2,3,4,5,6,7,8,9,10]
                for (int i = middle - 1; i >= 0; i--) { //the left side
                    newItem[newFirst] = this.get(i);
                    newFirst--;
                }

                for (int i = middle; i < this.nextLast; i++) { //the right side
                    newItem[newLast] = this.get(i);
                    newLast++;
                }

                for (int i = this.size - 1; i >= this.nextFirst; i--) { //the rest
                    newItem[newFirst] = this.get(i);
                    newFirst--;
                }

                this.nextFirst = newFirst;
                this.nextLast = newLast;
                this.item = newItem;

            } else if (this.nextLast < middle) { //nextLast has came to the left
                capacity = (int) (this.item.length * FACTOR);
                newItem = (T[]) new Object[capacity];
                newFirst = newItem.length / 2 - 1;
                newLast = newItem.length / 2;
                // [1,2,3,4,5,6,7,8,9,10]
                for (int i = middle - 1; i > this.nextFirst; i--) { //the left side
                    newItem[newFirst] = this.get(i);
                    newFirst--;
                }

                for (int i = middle; i < this.size; i++) { //the right side
                    newItem[newLast] = this.get(i);
                    newLast++;
                }

                for (int i = 0; i <= this.nextLast; i++) { //the rest
                    newItem[newLast] = this.get(i);
                    newLast++;
                }
                this.nextFirst = newFirst;
                this.nextLast = newLast;
                this.item = newItem;
            }
        }
        else if (factor == RFACTOR) {
            capacity = this.item.length / 2;
            newItem = (T[]) new Object[capacity];
            newFirst = capacity / 2 - (this.item.length - this.nextFirst);
            newLast = newFirst + size;
            System.arraycopy(this.item, this.nextFirst, newItem, newFirst, size);
            this.nextFirst = newFirst;
            this.nextLast = newLast;
        }

//        if (this.size > 10) { //when size is bigger than 10 then to consider to shrink.
//
//        }

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
        int middle = this.item.length / 2;
        if (this.nextFirst >= middle) { //been to the right side

            for (int i = this.nextFirst + 1; i < this.item.length; i++) {
                returnList.add(this.get(i));
            }
            for (int i = 0; i < middle; i++) {
                returnList.add(this.get(i));
            }
            for (int i = middle; i < this.nextLast; i++) {
                returnList.add(this.get(i));
            }
        } else if (this.nextLast < middle) { //been to the left side
            for (int i = this.nextFirst + 1; i < middle; i++) { //copy from the first to the last
                returnList.add(this.get(i));
            }
            for (int i = middle; i < this.item.length; i++) {
                returnList.add(this.get(i));
            }
            for (int i = 0; i <= this.nextLast - 1; i++) {
                returnList.add(this.get(i));
            }
        } else {
            //not go further
            for (int i = this.nextFirst + 1; i < middle; i++) {
                returnList.add(this.get(i));
            }
            for (int i = middle; i <= this.nextLast - 1; i++) {
                returnList.add(this.get(i));
            }
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
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return this.item[index];
    }

    public static void main(String[] args) {
        Deque<Integer> al = new ArrayDeque<>();
//        for(int i = 0; i < 20; i++) {
//            al.addFirst(i);
//        }
        for(int i = 0; i < 21; i++) {
            al.addLast(i);
        }

//        for(int i = 0; i < 11; i++) {
//            al.addLast(i);
//            al.addFirst(i);
//        }
        System.out.println("over!");
    }
}
