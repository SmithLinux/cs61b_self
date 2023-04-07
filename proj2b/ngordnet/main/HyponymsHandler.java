package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;

import java.util.Collections;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {

    private WordNet wn;

    public HyponymsHandler() {}

    public HyponymsHandler(WordNet wn) {
        this.wn = wn;
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
        if (k > 0 && k <= hypons.size()) {
            hypons = hypons.subList(0, k);
        }
        Collections.sort(hypons);
        return hypons.toString();
    }


}
