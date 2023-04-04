package ngordnet.main;


import org.checkerframework.checker.units.qual.A;

import java.util.*;

/**
 * a - c - d
 * |
 * b
 * AjList
 * 0 b c
 * 1
 * 2 d
 * 3
 * The key represent the number of the vertices, and the values represent the node which connected
 */
public class Graph<V, W> {
    private final Map<V, List<W>> adjList;
    private int nodeSize;

    /**
     * Create empty graph with 0 vertices.
     */
    public Graph() {
        adjList = new HashMap<>();
    }

    public boolean addEdge(V vertices, W edge) {
        if (!hasNode(vertices)) {
            return false;
        }
        adjList.get(vertices).add(edge);
        return true;
    }

    public boolean hasNode(V vertices) {
        for (Map.Entry<V, List<W>> entry : adjList.entrySet()) {
            if (vertices.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    public void createNode(V vertices) {
        if (!hasNode(vertices)) {
            adjList.put(vertices, new ArrayList<>());
        }
    }

    public List<V> getNodes() {
        List<V> list = new ArrayList<>();
        for (Map.Entry<V, List<W>> entry : adjList.entrySet()) {
            list.add(entry.getKey());
        }

        return list;
    }

    public List<W> neighbors(V node) {
        return adjList.get(node);
    }


}
