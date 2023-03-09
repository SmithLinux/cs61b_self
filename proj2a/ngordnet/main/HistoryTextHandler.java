package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {

    private NGramMap ngm;

    public HistoryTextHandler(NGramMap map) {
        this.ngm = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        StringBuilder response = new StringBuilder();

        for (String word : words) {
            response.append(word).append(":").append(" ").append(this.ngm.weightHistory(word, startYear, endYear).toString()).append("\n");

        }
        return response.toString();
    }
}
