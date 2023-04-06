package ngordnet.main;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test
    public void addHyponymsTwoCommon16Test() {
        WordNet wn=new WordNet("./data/wordnet/synsets16.txt","./data/wordnet/hyponyms16.txt");
        String[] words = "alteration,change,demotion,increase,jump,leap,modification,saltation,transition,variation".split(",");
        assertThat(wn.getHyponyms("change")).containsExactly(words);
    }

    @Test
    public void handleListsOfWords16Test() {
        WordNet wn=new WordNet("./data/wordnet/synsets16.txt","./data/wordnet/hyponyms16.txt");
        List<String> list = new ArrayList<>();
        list.add("change");
        list.add("occurrence");
        assertThat(wn.getHyponyms(list)).containsExactly("alteration", "change", "increase", "jump", "leap", "modification", "saltation", "transition");
    }

    @Test
    public void handleListsOfWords1Test() {
        WordNet wn=new WordNet("./data/wordnet/synsets16.txt","./data/wordnet/hyponyms16.txt");
        List<String> list = new ArrayList<>();
        list.add("event");
        list.add("action");
        assertThat(wn.getHyponyms(list)).containsExactly("action", "change", "demotion", "variation");
    }

    @Test
    public void handleListsOfWordsTest() {
        WordNet wn=new WordNet("./data/wordnet/synsets.txt","./data/wordnet/hyponyms.txt");
        List<String> list = new ArrayList<>();
        list.add("video");
        list.add("recording");
        assertThat(wn.getHyponyms(list)).containsExactly("video", "video_recording", "videocassette", "videotape");
    }

    @Test
    public void addHyponymsAllTest() {
        WordNet wn=new WordNet("./data/wordnet/synsets.txt","./data/wordnet/hyponyms.txt");
        String words = "HDTV,TV,cable,cable_television,high-definition_television,picture,telecasting,television,video,video_recording,videocassette,videotape";
        String[] newStr = words.split(",");
        assertThat(wn.getHyponyms("video")).containsExactly(newStr);
    }
}
