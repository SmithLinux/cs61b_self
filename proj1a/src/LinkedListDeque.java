import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    private Node<T> sentinel;
    private int size;

    public static class Node<T> {
        private T item;
        private Node prev;
        private Node next;

        public Node() {
            Object middleMan = new Object();
            this.item = (T) middleMan;
            this.prev = this;
            this.next = this.prev;
        }

        public Node(T item) {
            this.item = item;
            this.prev = this;
            this.next = this.prev;
        }



        /**
         * this constructor is for the list which has been already created.
         */
    }
    public LinkedListDeque() {
        this.sentinel = new Node<>();
    }


    /**
     * The empty list is represented by a single sentinel node that points at itself.
     * There is a single instance variable called sentinel that points at this sentinel.
     */

    @Override
    public void addFirst(T x) {
        Node<T> newNode = new Node(x);
        Node<T> servant = this.sentinel;
        Node<T> firstNode = servant.next;
        servant.next = newNode;
        newNode.prev = servant;
        firstNode.prev = newNode;
        newNode.next = firstNode;

    }

    @Override
    public void addLast(T x) {
        Node<T> newNode = new Node(x);
        Node<T> servant = this.sentinel; // last node
        Node<T> lastNode = servant.prev;
        lastNode.next = newNode;
        newNode.prev = lastNode;
        servant.prev = newNode;
        newNode.next = servant;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> nextNode = this.sentinel.next;
        while (nextNode != this.sentinel) {
            returnList.add(nextNode.item);
            nextNode = nextNode.next;
        }
        return returnList;
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

    @Override
    public T getRecursive(int index) {
        return null;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> q = new LinkedListDeque<>();
        System.out.println(q.sentinel.prev.item == q.sentinel.next.item);
        q.addLast(6);
        q.addLast(5);
        q.addLast(4);
        q.addLast(3);
        q.addLast(2);
        q.addLast(1);
        System.out.println("Test add first method");
        LinkedListDeque<Integer> q2 = new LinkedListDeque<>();
        q2.addFirst(6);
        q2.addFirst(5);
        q2.addFirst(4);
        q2.addFirst(3);
        q2.addFirst(2);
        q2.addFirst(1);
        System.out.println(q2.toList());
    }
}
