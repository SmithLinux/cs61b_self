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

        public T getItem(int index) {
            if (index == 0) {
                return this.item;
            }
            return (T) this.next.getItem(index - 1);
        }
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
        this.size++;
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
        this.size++;
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
        if (this.size == 0) {
            return null;
        }
        Node<T> first = this.sentinel.next;
        this.sentinel.next = first.next;
        first.next.prev = this.sentinel;
        first.next = null;
        first.prev = null;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        Node<T> last = this.sentinel.prev;
        this.sentinel.prev = last.prev;
        last.prev.next = this.sentinel;
        last.next = null;
        last.prev = null;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (this.size == 0) {
            return null;
        }
        Node<T> currentNote = this.sentinel.next;
        while (index != 0) {
            currentNote = currentNote.next;
            index--;
        }
        return currentNote.item;
    }

    @Override
    public T getRecursive(int index) {
        if (this.size == 0) {
            return null;
        }
        return (T) this.sentinel.next.getItem(index);
    }



}
