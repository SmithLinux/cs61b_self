package ngordnet.main;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class WordNetTest {

    @Test
    public void addSynsetsTest() {
        WordNet wn = new WordNet("data/wordnet/synsets11.txt", "data/wordnet/hyponyms11.txt");
        wn.addSynsets();
        String action = wn.getSynset(0);
        String change = wn.getSynset(1);
        String actifed = wn.getSynset(10);
        assertThat(action).isEqualTo("action");
        assertThat(change).isEqualTo("change");
        assertThat(actifed).isEqualTo("actifed");
    }
}
