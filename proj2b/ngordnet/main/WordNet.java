package ngordnet.main;

import edu.princeton.cs.algs4.In;

import java.util.Map;
import java.util.TreeMap;

/**
 * - reads in the WordNet dataset and constructs an instance of the directed graph class
 * - take a word and return its hyponyms.
 *
 * number - words
 * node - edges
 */
public class WordNet {
    // have instance of graph and add some helpful functions
    private Graph<Integer, Integer> graph;
    private Map<Integer, String> synsets;
    private String synsetsFileName;
    private String hyponymsFileName;

    public WordNet() {
        graph = new Graph<>(); // build the graph -> add all the edges
        synsets = new TreeMap<>();
    }

    public WordNet(String synsetsFileName, String hyponymsFileName) {
        graph = new Graph<>(); // build the graph -> add all the edges
        synsets = new TreeMap<>();
        this.synsetsFileName = synsetsFileName;
        this.hyponymsFileName = hyponymsFileName;
    }

    public void addSynsets() {
        In in = new In(synsetsFileName);


        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] synset = line.split(",");
            synsets.put(Integer.valueOf(synset[0]), synset[1]);
        }
    }

    public String getSynset(int node) {
        if (!synsets.containsKey(node)) {
            return "";
        }
        return synsets.get(node);
    }
}
