package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int n;
    private double loadFactor;
    private static final int RESIZE_FACTOR = 2;


    /** Constructors */
    public MyHashMap() {
        buckets = createTable(16);
        this.loadFactor = 0.75;
    }

    public MyHashMap(int initialCapacity) {
        buckets = createTable(initialCapacity);
        this.loadFactor = 0.75;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        buckets = new Collection[initialCapacity];
        this.loadFactor = loadFactor;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!


    @Override
    public void put(K key, V value) {
        if (((double) n /(double) this.buckets.length) >= loadFactor) {
            this.resize();
        }
        Collection<Node> bucket = this.buckets[this.hashing(key, this.buckets.length)];

        if (bucket == null) {
            this.buckets[this.hashing(key, this.buckets.length)] = this.createBucket();
            this.buckets[this.hashing(key, this.buckets.length)].add(new Node(key, value));
            this.n++;
        } else {
            if (this.containsKey(key)) {
                for (Node item : bucket) {
                    if (item.key.equals(key)) {
                        item.value = value;
                    }
                }
            } else {
                bucket.add(new Node(key, value));
                this.n++;
            }
        }

    }

    @Override
    public V get(K key) {
        Collection<Node> bucket = this.buckets[this.hashing(key, this.buckets.length)];
        if (bucket == null) {
            return null;
        }
        for (Node item : bucket) {
            if (item.key.equals(key)) {
                return item.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        Collection<Node> bucket = this.buckets[this.hashing(key, this.buckets.length)];
        if (bucket == null) {
            return false;
        }
        for (Node item : bucket) {
            if (item.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.n;
    }

    @Override
    public void clear() {
        this.buckets = this.createTable(16);
        this.n = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private int hashing(K key, int bucketsLength) {
        return Math.abs(key.hashCode() % bucketsLength);
    }

    private void resize() {
        Collection<Node>[] newBuckets = new Collection[this.buckets.length * RESIZE_FACTOR];
        for (Collection<Node> bucket : this.buckets) {
            if (bucket != null) {
                for (Node item : bucket) {
                    if (newBuckets[this.hashing(item.key, newBuckets.length)] == null) {
                        newBuckets[this.hashing(item.key, newBuckets.length)] = new LinkedList<>();
                    }
                    newBuckets[this.hashing(item.key, newBuckets.length)].add(item);
                }
            }

        }
        this.buckets = newBuckets;
    }
}
