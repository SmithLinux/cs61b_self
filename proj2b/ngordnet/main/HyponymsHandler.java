package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

public class HyponymsHandler extends NgordnetQueryHandler {

    private WordNet wn;

    public HyponymsHandler() {}

    public HyponymsHandler(WordNet wn) {
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) {
        return "Hello!";
    }


}
