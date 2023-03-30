package ngordnet.main;


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
public class Graph {
    private Map<Integer, List<String>> adjList;

    /**
     * Create empty graph with 0 vertices.
     */
    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(Integer vertices, String edge) {
        adjList.get(vertices).add(edge);
    }

    public void createNode(Integer vertices) {
        adjList.put(vertices, new ArrayList<>());
    }


    /**
     * return the number of edges.
     */
    public static int degree(Graph g, Integer vertices) {
        return 0;
    }

    public Integer getNode() {
        return null;
    }
}
