package ngordnet.main;

import java.util.Map;

/**
 * - reads in the WordNet dataset and constructs an instance of the directed graph class
 * - take a word and return its hyponyms.
 */
public class WordNet {
    // have instance of graph and add some helpful functions
    private Graph graph;
    private Map<Integer, String> vertices;

    public WordNet() {
        graph = new Graph(); // build the graph -> add all the edges
    }
}
