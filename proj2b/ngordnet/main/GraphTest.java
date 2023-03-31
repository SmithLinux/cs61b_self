package ngordnet.main;


import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 *  lookups that you might need to perform
 *  1. Given a word (e.g. “change”), what nodes contain that word?
 *          Wordnet need to do
 *  2. Given an integer, what node goes with that index?
 *          Wordnet need to do
 *  3. Given a node, what words are in that node?
 * -----
 *  graph operations
 *  1. Creating a node
 *  2. Adding an edge to a node
 *  3. Finding reachable vertices
 */
public class GraphTest {

    @Test
    public void addNodeTest() {
        Graph<Integer, String> graph = new Graph<>();
        graph.createNode(1);
        graph.addEdge(1, "Hello");
        List<Integer> list = graph.getNodes();
        assertThat(list).contains(1);
    }

    @Test
    public void addNodeMoreTest() {
        Graph<Integer, Integer> graph = new Graph<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            graph.createNode(i);
            list.add(i);
        }

        assertThat(graph.getNodes()).containsExactlyElementsIn(list);
    }

    @Test
    public void addEdgeTest() {
        Graph<Integer, Integer> graph = new Graph<>();
        graph.createNode(1);
        graph.createNode(2);
        graph.addEdge(1, 2);
        List<Integer> list = new ArrayList<>();
        list.add(2);
        assertThat(graph.neighbors(1)).containsExactlyElementsIn(list);
    }

    @Test
    public void addEdgeMoreTest() {
        Graph<Integer, Integer> graph = new Graph<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            graph.createNode(i);
        }

        for (int i = 0; i < 100; i++) {
            graph.addEdge(i, 99 - i);
        }


        for (int i = 0; i < 100; i++) {
            assertThat(graph.neighbors(i)).containsExactly(99 - i);
        }
    }
}
