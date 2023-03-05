import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;


/**
 * Invariants
 * 1. All leaves are the same distance from the root.
 * 2, A non-leaf node with k items must have exactly k + 1 children.
 */
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{


    private Node root;
    private int size;

    @Override
    public void put(K key, V value) {
        boolean isReplaced = false;
        if (root == null) {
            this.size++;
            this.root = new Node(key, value);
        } else {
            isReplaced = root.insert(root, key, value);
        }
        if (isReplaced) {
            this.size++;
        }
    }

    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        return root.getValue(key);
    }

    @Override
    public boolean containsKey(K key) {
        if (this.root == null) {
            return false;
        }
        return this.root.contain(key);
    }

    @Override
    public int size() {
        return this.size;
    }

    /** Removes every mapping from this map. */
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
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

    private class Node {
        K key;
        V value;
        boolean leaf;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.leaf = true;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public boolean insert(Node node, K key, V value) {
            if (node.key.compareTo(key) == 0) {
                node.value = value;
                return false;
            }
            if(node.key.compareTo(key) > 0) {
                if (node.left == null) {
                    node.left = new Node(key, value);
                    node.leaf = false;
                    return true;
                }
                return insert(node.left, key, value); // insert to the left leaf
            } else if (node.key.compareTo(key) < 0) {   // insert to the right leaf
                if (node.right == null) {
                    node.right = new Node(key, value);
                    node.leaf = false;
                    return true;
                }
                return insert(node.right, key, value);
            }
            return true;
        }

        public boolean contain(K key) {
            if (this.isLeaf()) {
                return this.key.compareTo(key) == 0;
            }
            if (this.key.compareTo(key) == 0) {
                return true;
            }
            if (this.key.compareTo(key) > 0) {
                return this.left.contain(key);
            } else {
                return this.right.contain(key);
            }
        }

        public V getValue(K key) {
            if (this.key.compareTo(key) == 0) {
                return this.value;
            }
            if (this.isLeaf()) {
                return null;
            }
            if (this.key.compareTo(key) > 0) {
                return this.left.getValue(key);
            } else if (this.key.compareTo(key) < 0) {
                return this.right.getValue(key);
            }
            return null;
        }

    }

    public static void main(String[] args) {
        System.out.println("c".compareTo("d"));
    }



}
