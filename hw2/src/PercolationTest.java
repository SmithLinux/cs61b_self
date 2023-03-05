import com.google.common.truth.Truth;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PercolationTest {

    @Test
    public void openTest() {
        Percolation p = new Percolation(5);
        for (int r = 4; r > 0; r--) {
            for (int c = 4; c > 0; c--) {
                p.open(r,c);
                Truth.assertThat(p.isOpen(r,c)).isEqualTo(true);
            }
        }
    }

    @Test
    public void percolateTest() {
        Percolation p = new Percolation(5);
        for (int r = 1; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                p.open(r,c);
                Truth.assertThat(p.percolates()).isEqualTo(false);
            }
        }
        p.open(0,0);
        Truth.assertThat(p.percolates()).isEqualTo(true);
        Truth.assertThat(p.isFull(4,0)).isEqualTo(true);
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
//    public void openAroundTest() {
//        Percolation p = new Percolation(5);
//        p.open(0,4);
//        p.open(1,4);
//        p.open(2,4);
//        p.open(3,4);
//
//        Truth.assertThat(p.isConnected(p.xyTo1D(3,4), p.xyTo1D(2,4))).isEqualTo(true);
//    }

//    @Test
//    public void cornerTest() {
//        Percolation p = new Percolation(6);
//        Truth.assertThat(p.isCorner(0,0)).isEqualTo(true);
//        Truth.assertThat(p.isCorner(0,5)).isEqualTo(true);
//        Truth.assertThat(p.isCorner(5,0)).isEqualTo(true);
//        Truth.assertThat(p.isCorner(5,5)).isEqualTo(true);
//    }


    public void xyTo1DTest() {
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

        Percolation p2 = new Percolation(5);
        List<Integer> result2 = new ArrayList<>();
        List<Integer> expected2 = new ArrayList<>();

        result2.add(p2.xyTo1D(0,0));
        result2.add(p2.xyTo1D(1,1));
        result2.add(p2.xyTo1D(2,2));
        result2.add(p2.xyTo1D(3,3));
        result2.add(p2.xyTo1D(4,4));
        expected2.add(0);
        expected2.add(6);
        expected2.add(12);
        expected2.add(18);
        expected2.add(24);

        Truth.assertThat(result2).containsExactlyElementsIn(expected2);

    }


    @Test
    public void isFullTest() {
        Percolation p = new Percolation(5);
        for (int r = 0; r < 5; r++) {
            p.open(r, 0);
        }
        for (int c = 2; c < 5; c++) {
            for (int r = 1; r < 5; r++) {
                p.open(r, c);
                Truth.assertThat(p.isFull(r, c)).isEqualTo(false);
            }
        }
    }


//    @Test
//    public void unionAroundOpenedTest() {
//        Percolation p = new Percolation(5);
//        p.open(0, 1);
//        p.open(0,2);
//        p.open(0, 3);
//
//        Truth.assertThat(p.isConnected(p.xyTo1D(0,1), p.xyTo1D(0,2))).isEqualTo(true);
//
//        //p.unionAroundOpened(0,2);
//
//        Truth.assertThat(p.isConnected(p.xyTo1D(0,1), p.xyTo1D(0,2))).isEqualTo(true);
//    }

//    @Test
//    public void checkUnionTest() {
//        Percolation p = new Percolation(5);
//        p.open(0,1);
//        p.open(0,2);
//
//
//        Truth.assertThat(p.isConnected(p.xyTo1D(0,1), p.xyTo1D(0,2))).isEqualTo(true);
//    }


}
