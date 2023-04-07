package ngordnet.main;

import edu.princeton.cs.algs4.In;
import org.checkerframework.checker.units.qual.A;

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
    private Map<Integer, String[]> synsets;
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

    /**
     * a synset element is not permitted to contain a space, split by "," and " ".
     * create a graph node. and a synsets.
     */
    public void addSynsets() {
        In in = new In(synsetsFileName);


        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] synset = line.split(",");
            int num = Integer.valueOf(synset[0]);
            String words = synset[1];
            synsets.put(num, words.split(" "));
            graph.createNode(Integer.valueOf(synset[0]));
        }
    }

    public List<String> getSynset(int node) {
        if (!synsets.containsKey(node)) {
            return null;
        }

        return Arrays.asList(synsets.get(node));
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

    public List<String> getHyponyms(String syn) {
        List<String> syns = new ArrayList<>();

        List<Integer> synIndex = getSynsetsIndex(syn);
        for (int index : synIndex) {
            List<Integer> hyps = BFTraversal(index);
            if (hyps.isEmpty()) {
                return new ArrayList<>();
            }
            for (Integer i : hyps) {
                syns.addAll(getSynset(i));
            }
        }

        //Set<String> set = new HashSet<>(syns);
//        List<String> st = new ArrayList<>(set);

//        Collections.sort(st);
        return syns;
    }

    /**
     * handle list of words, return a hyponyms of each word.
     * get the hyponyms of both words.
     * car - beetle, sedan
     * bug - ant, beetle, gitbug
     * return beetle
     */
    public List<String> getHyponyms(List<String> syn) {
        List<String> preHyn = getHyponyms(syn.get(0));;

        for (String word : syn) {
            preHyn.retainAll(getHyponyms(word));
        }
//        Set<String> set = new HashSet<>(preHyn);
        return preHyn;
    }

    /**
     * return the list of the index which from the synsets.
     */
    private List<Integer> getSynsetsIndex(String syn) {
        List<Integer> index = new ArrayList<>();
        for (Map.Entry<Integer, String[]> entry : synsets.entrySet()) {
            for (String word : entry.getValue()) {
                if (Arrays.asList(word.split(" ")).contains(syn)) {
                    index.add(entry.getKey());
                }
            }
        }
        return index;
    }

    public List<Integer> BFTraversal(Integer vertices) {
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> visited = new ArrayList<>();
        queue.add(vertices);
        while (!queue.isEmpty()) {
            int frontOfQueue = queue.remove();
            visited.add(frontOfQueue);
            for (int i : graph.neighbors(frontOfQueue)) {
                if (!visited.contains(i)) {
                    queue.add(i);
                }
            }
        }
        return visited;
    }
}
