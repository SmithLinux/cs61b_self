import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

public class PercolationTest {

    @Test
    public void openTest() {
        Percolation p = new Percolation(5);
        p.open(1,4);
        boolean result = p.isOpen(4,1);
        p.open(3,0);
        boolean result2 = p.isOpen(3, 1);

        Truth.assertThat(p.isOpen(1,4)).isNotEqualTo(result);
        Truth.assertThat(p.isOpen(3,0)).isNotEqualTo(result2);
    }
}
