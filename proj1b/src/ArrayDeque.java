import org.checkerframework.checker.units.qual.A;

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
        if (this.item.length > 10 || this.size == this.item.length) {
            this.resize();
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

    public void resize() {
        //two strategy for resize
        //1.if the nextFirst > nextLast means the nextFirst is going to the back of the list
        //2.if the nextLast < nextFirst means the nextLast is going to the front of the list
        //3.no one goes further, peace!
        double usage = (double) this.size / (double) this.item.length;
        if (this.size != this.item.length && usage > RFACTOR) {
            return;
        }

        T[] newItem;
        int factor;
        int startPosition;
        int rightIndex;
        int middle = this.item.length / 2;
        if (this.size == this.item.length) {
            // make array bigger size
            factor = (int) Math.round(this.item.length * FACTOR);
            newItem = (T[]) new Object[factor];
            startPosition = newItem.length / 2 - middle;
            if (nextFirst >= middle) { //left side is full
                System.arraycopy(this.item, 0, newItem, startPosition, middle); //copy left side
                System.arraycopy(this.item, this.nextFirst, newItem, startPosition - middle, this.size - this.nextFirst); //copy remain left side but on the right side of the old array

                int totalItemInFirst = (this.item.length - this.nextFirst + this.item.length / 2);
                rightIndex = (newItem.length / 2 - 1) - totalItemInFirst;
                this.nextFirst = rightIndex;
                this.item = newItem;

            } else if (nextLast < middle) { //right side is full
                System.arraycopy(this.item, 0, newItem, startPosition, this.size);

                int totalItemInRight = (this.item.length - this.nextFirst + this.item.length / 2);
                rightIndex = (newItem.length / 2 - 1) - totalItemInRight;
                this.nextFirst = rightIndex;

            } else {
                System.arraycopy(this.item, 0, newItem, startPosition, this.size);
            }
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
//        al.addLast(5);
//        al.addLast(6);
//        al.addLast(7);
//        al.addLast(8);
//        al.addLast(9);
//        al.addLast(0);
//        al.addLast(1);
//        al.addLast(2);
//        al.addLast(3);
//        al.addLast(4);
        al.addFirst(1);
        al.addFirst(2);
        al.addFirst(3);
        al.addFirst(4);
        al.addFirst(5);
        al.addFirst(6);
        al.addFirst(7);
        al.addFirst(8);
        al.addFirst(9);
        al.addFirst(10);
        al.addFirst(11);
        al.addFirst(12);
        al.addFirst(13);
        al.addFirst(14);
        al.addFirst(15);
        al.addFirst(16);
        al.addFirst(17);
        al.addFirst(18);
        al.addFirst(19);
        al.addFirst(20);
        System.out.println("over!");
    }
}
