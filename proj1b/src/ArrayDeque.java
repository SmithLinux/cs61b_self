import org.checkerframework.checker.units.qual.A;

import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private T[] item;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static final double RFACTOR = 0.25;
    private static final double FACTOR = 1.25;

    public ArrayDeque() {
        this.item = (T[]) new Object[10];
        this.size = 0;
        this.nextFirst = this.item.length / 2 - 1;
        this.nextLast = this.item.length / 2;
    }
    @Override
    public void addFirst(T x) {
        this.resize();

        this.item[this.nextFirst] = x;

        this.nextFirst--;

        this.size++;
        if(this.nextFirst == -1) {
            this.nextFirst = this.item.length - 1;
        }
    }

    public void resize() {
        double refactor = (double) this.size / (double) this.item.length;
        if (this.size != this.item.length - 1 && refactor > RFACTOR && this.item.length <= 10) {
            return;
        }
        T[] newItem;
        int factor;
        int startPosition;
        if (this.size == this.item.length - 1) {
            // make array bigger size
            factor = (int) Math.round(this.item.length * FACTOR);
            newItem = (T[]) new Object[factor];
            startPosition = newItem.length / 2 - this.item.length / 2;
            System.arraycopy(this.item, 0, newItem, startPosition, this.size);
            return;
        }


        //shrink array
        factor = Math.round(this.item.length / 2);
        newItem = (T[]) new Object[factor];
        startPosition = newItem.length / 2 - (this.nextLast - this.nextFirst) / 2;
        System.arraycopy(this.item, this.nextFirst, newItem, startPosition, size);

    }

    @Override
    public void addLast(T x) {
        this.resize();

        this.item[this.nextLast] = x;

        this.nextLast++;

        this.size++;

        if(this.nextLast == this.item.length) {
            this.nextLast = 0;
        }
    }

    @Override
    public List<T> toList() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
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
        return null;
    }

    public static void main(String[] args) {
        Deque<Integer> al = new ArrayDeque<>();
        al.addLast(5);
        al.addLast(6);
        al.addLast(7);
        al.addLast(8);
        al.addLast(9);
        al.addLast(0);
        al.addLast(1);
        al.addLast(2);
        al.addLast(3);
        al.addLast(4);
//        al.addFirst(4);
//        al.addFirst(3);
//        al.addFirst(2);
//        al.addFirst(1);
//        al.addFirst(0);
//        al.addFirst(9);
//        al.addFirst(8);
//        al.addFirst(7);
//        al.addFirst(6);
//        al.addFirst(5);
        System.out.println("over!");
    }
}
