package ngordnet.main;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class WordNetTest {

    @Test
    public void addSynsetsTest() {
        WordNet wn = new WordNet("data/wordnet/synsets11.txt", "data/wordnet/hyponyms11.txt");
        wn.addSynsets();
        List<String> action = wn.getSynset(0);
        List<String> change = wn.getSynset(1);
        List<String> actifed = wn.getSynset(10);
        assertThat(action).containsExactly("action");
        assertThat(change).containsExactly("change");
        assertThat(actifed).containsExactly("actifed");
    }

    @Test
    public void addHyponymsTest() {
        WordNet wn=new WordNet("./data/wordnet/synsets11.txt","./data/wordnet/hyponyms11.txt");
        assertThat(wn.getHyponyms("antihistamine")).containsExactly("actifed", "antihistamine");
    }

    @Test
    public void addHyponyms16Test() {
        WordNet wn=new WordNet("./data/wordnet/synsets16.txt","./data/wordnet/hyponyms16.txt");
        assertThat(wn.getHyponyms("action")).containsExactly("action", "change", "demotion", "variation");
    }
}
