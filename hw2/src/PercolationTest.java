import com.google.common.truth.Truth;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void openTopBottomTest() {
        Percolation p = new Percolation(5);
        p.open(0,4);
        p.open(1,4);
        p.open(2,4);
        p.open(3,4);

        Truth.assertThat(p.isFull(3,4)).isEqualTo(true);
    }

    @Test
    public void openAroundTest() {
        Percolation p = new Percolation(5);
        p.open(0,4);
        p.open(1,4);
        p.open(2,4);
        p.open(3,4);

        Truth.assertThat(p.isConnected(p.xyTo1D(3,4), p.xyTo1D(2,4))).isEqualTo(true);
    }

//    @Test
//    public void cornerTest() {
//        Percolation p = new Percolation(6);
//        Truth.assertThat(p.isCorner(0,0)).isEqualTo(true);
//        Truth.assertThat(p.isCorner(0,5)).isEqualTo(true);
//        Truth.assertThat(p.isCorner(5,0)).isEqualTo(true);
//        Truth.assertThat(p.isCorner(5,5)).isEqualTo(true);
//    }


    @Test
    public void xyTo1D() {
        Percolation p = new Percolation(5);
        List<Integer> result = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result.add(p.xyTo1D(i, j));
            }
        }

        for (int i = 0; i < 25; i++) {
            expected.add(i);
        }

        Truth.assertThat(result).containsExactlyElementsIn(expected);
    }


    @Test
    public void isFullTest() {
        Percolation p = new Percolation(10);
        p.open(7,7);
        boolean result = p.isFull(7,7);
        Truth.assertThat(result).isEqualTo(false);
    }


    @Test
    public void unionAroundOpenedTest() {
        Percolation p = new Percolation(5);
        p.open(0, 1);
        p.open(0,2);
        p.open(0, 3);

        Truth.assertThat(p.isConnected(p.xyTo1D(0,1), p.xyTo1D(0,2))).isEqualTo(true);

        //p.unionAroundOpened(0,2);

        Truth.assertThat(p.isConnected(p.xyTo1D(0,1), p.xyTo1D(0,2))).isEqualTo(true);
    }

    @Test
    public void checkUnionTest() {
        Percolation p = new Percolation(5);
        p.open(0,1);
        p.open(0,2);


        Truth.assertThat(p.isConnected(p.xyTo1D(0,1), p.xyTo1D(0,2))).isEqualTo(true);
    }
}
