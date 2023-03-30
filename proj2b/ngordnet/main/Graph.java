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
    private final int[] ajList;

    /**
     * Create empty graph with 0 vertices.
     */
    public Graph() {
        ajList = new int[5];
    }

    public void addEdge(Integer vertices, String edge) {
        
    }

    public void createNode(Integer vertices) {
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
