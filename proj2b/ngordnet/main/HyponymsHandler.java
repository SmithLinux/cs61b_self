package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HyponymsHandler extends NgordnetQueryHandler {

    private final WordNet wn;
    private final NGramMap ngm;

    public HyponymsHandler(WordNet wn, NGramMap ngm) {
        this.wn = wn;
        this.ngm = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {

        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        if (k < 0) {
            return "[]";
        }

        List<String> hypons = wn.getHyponyms(words);
        List<String> noduplicate = new ArrayList<>();
        for (String hypon : hypons) {
            if (!noduplicate.contains(hypon)) {
                noduplicate.add(hypon);
                popularWords(hypon, startYear, endYear);
            }
        }
        if (k > 0 && k <= hypons.size()) {
            noduplicate = noduplicate.subList(0, k);
        }
        Collections.sort(noduplicate);
        return noduplicate.toString();
    }

    /**
     * you would find the 5 most popular words in that time period that are hyponyms of both food and cake.
     * Here, the popularity is defined as the total number of times the word appears over the entire time period.
     */
    private List<String> popularWords(String word, int startYear, int endYear) {
        List<String> popular = new ArrayList<>();
        TimeSeries ts = ngm.countHistory(word, startYear, endYear);
        TimeSeries total = ngm.totalCountHistory();
        for(Map.Entry<Integer, Double> entry : ts.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
        return popular;
    }
}
