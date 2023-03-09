package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private NGramMap ngm;

    public HistoryHandler(NGramMap map) {
        ngm = map;
    }

    public String handle(NgordnetQuery q) {
        List<String> labels = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        List<TimeSeries> lts = new ArrayList<>();

        for (String label: labels) {
            lts.add(this.ngm.weightHistory(label, startYear, endYear));
        }

        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);

        return Plotter.encodeChartAsString(chart);
    }

}
