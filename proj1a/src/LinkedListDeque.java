import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    private Node<T> sentinel;
    private int size;

    public static class Node<T> {
        private T item;
        private Node prev;
        private Node next;

        public Node() {
        }

        public Node(T item) {
            this.item = item;
            this.prev = (Node) this.item;
            this.next = this.prev;
        }

        /**
         * this constructor is for the list which has been already created.
         */
    }
    public LinkedListDeque() {
        Node<T> middleMan = new Node<>();
        this.sentinel = new Node<>((T) middleMan);
    }


    /**
     * The empty list is represented by a single sentinel node that points at itself.
     * There is a single instance variable called sentinel that points at this sentinel.
     */

    @Override
    public void addFirst(T x) {
    }

    @Override
    public void addLast(T x) {
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

    @Override
    public T getRecursive(int index) {
        return null;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> q = new LinkedListDeque<>();
        System.out.println(q.sentinel.prev.item == q.sentinel.next.item);
    }
}
