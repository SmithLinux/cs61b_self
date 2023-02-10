package bomb;

import common.IntList;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {
            b.phase1(new IntList(0, new IntList(9, new IntList(3, new IntList(0, new IntList(8, null)))))); // Figure this out too
        }
        int pass = -81201430;
        String password = "";
        int i = 0;
        while (i < 1400) {
            if (i == 1337) {
                password += pass + " ";
            }else {
                password+= i + " ";
            }
            i++;
        }

        if (phase >= 2) {
            b.phase2(password);
        }
    }
}
