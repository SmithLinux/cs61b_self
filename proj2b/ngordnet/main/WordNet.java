package ngordnet.main;

import edu.princeton.cs.algs4.In;

import java.util.*;

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
        addSynsets();
        addHyponyms();
    }

    public void addSynsets() {
        In in = new In(synsetsFileName);


        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] synset = line.split(",");
            synsets.put(Integer.valueOf(synset[0]), synset[1]);
            graph.createNode(Integer.valueOf(synset[0]));
        }
    }

    public String getSynset(int node) {
        if (!synsets.containsKey(node)) {
            return "";
        }
        return synsets.get(node);
    }

    private void addHyponyms() {
        In in = new In(hyponymsFileName);


        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] hyponyms = line.split(",");
            int i = 0;
            int vertices = Integer.parseInt(hyponyms[0]);
            for (String hyponym : hyponyms) {
                if (i != 0) {
                    graph.addEdge(vertices, Integer.parseInt(hyponym));
                }
                i++;
            }
        }
    }

    public Set<String> getHyponyms(String syn) {
        Set<String> syns = new HashSet<>();
        syns.add(syn);
        int synIndex = getSynsetsIndex(syn);
        for (int i : graph.neighbors(synIndex)) {
            syns.add(synsets.get(i));
        }

        return syns;
    }

    private int getSynsetsIndex(String syn) {
        for (Map.Entry<Integer, String> entry : synsets.entrySet()) {
            if (syn.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
