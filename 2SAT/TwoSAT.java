import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by yxy on 5/6/2015.
 */
public class TwoSAT {
    public static List<Pair> reduce(List<Pair> pairs, int numOfVar) {
        boolean[] positiveCache = new boolean[numOfVar+1];
        boolean[] negativeCache = new boolean[numOfVar+1];
        List<Pair> reduced = new ArrayList<>();

        /*for (Pair p : pairs) {
            if (graph.getNode(p.first) != null && graph.getNode((p.second)) != null) {
                reduced.add(p);
            }
        }*/

        for (Pair p : pairs) {
            if (p.first > 0) positiveCache[p.first] = true;
            else negativeCache[-p.first] = true;

            if (p.second > 0) positiveCache[p.second] = true;
            else negativeCache[-p.second] = true;
        }

        for (Pair p : pairs) {
            if (p.first > 0 && p.second > 0) {
                if (negativeCache[p.first] && negativeCache[p.second])
                    reduced.add(p);
            }
            else if (p.first > 0 && p.second < 0) {
                if (negativeCache[p.first] && positiveCache[-p.second])
                    reduced.add(p);
            }
            else if (p.first < 0 && p.second > 0) {
                if (positiveCache[-p.first] && negativeCache[p.second])
                    reduced.add(p);;
            }
            else if (p.first < 0 && p.second < 0) {
                if (positiveCache[-p.first] && positiveCache[-p.second])
                    reduced.add(p);;
            }
        }

        return reduced;
    }

    public static boolean twoSat(List<Pair> reducedPairs, int numOfVar) {
        boolean[] guess = new boolean[numOfVar+1];  // number of variables
        Random rand = new Random();
        for (int i = 1; i <= numOfVar; i++) {  // number of variables
            guess[i] = rand.nextBoolean();
        }

        for (long i = 0, j = 2*(long)numOfVar/**numOfVar*/; i < j; i++) {
            boolean satisfy = true;

            Pair temp = new Pair(0, 0);
            for (Pair p : reducedPairs) {
                if ((p.first < 0 == guess[Math.abs(p.first)]) && (p.second < 0 == guess[Math.abs(p.second)])) {
                    satisfy = false;
                    temp = p;
                    break;
                }
            }

            if (satisfy) return true;

            Pair p = temp;
            if (rand.nextBoolean()) guess[Math.abs(p.first)] = !guess[Math.abs(p.first)];
            else guess[Math.abs(p.second)] = !guess[Math.abs(p.second)];
        }

        return false;
    }

    public static void runTwoSAT(Pair[] pairs) {
        long start = System.nanoTime();

        int numOfVar = pairs.length;
        List<Pair> reducedPairs = Arrays.asList(pairs);
        int lastSize = reducedPairs.size();
        reducedPairs = reduce(reducedPairs, numOfVar);
        int thisSize = reducedPairs.size();
        while (thisSize < lastSize) {
            lastSize = thisSize;
            reducedPairs = reduce(reducedPairs, numOfVar);
            thisSize = reducedPairs.size();
        }
        System.out.println(reducedPairs.size());

        boolean satisfy = false;
        for (int i = 0, j = (int)Math.log((double) pairs.length); i < j; i++) {
            if (twoSat(reducedPairs, pairs.length)) {
                satisfy = true;
                break;
            }
            System.out.println(i);
        }

        System.out.println(satisfy);
        System.out.println("2sat randomized local search running time: " + (System.nanoTime() - start)/1000000000.0 + "seconds");
    }
}
