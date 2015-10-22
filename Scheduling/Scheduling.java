import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by yxy on 3/30/2015.
 */
public class Scheduling {
    public static void schedule(String fileName) {
        int numOfTask = 0;
        ArrayList<SortByDiff> wlDiff = new ArrayList<>();
        ArrayList<SortByRatio> wlRatio = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = br.readLine();
            numOfTask = Integer.parseInt(line);
            while ((line = br.readLine()) != null) {
                String[] weightAndLength = line.split(" ");
                SortByDiff diff = new SortByDiff(Integer.parseInt(weightAndLength[0]), Integer.parseInt(weightAndLength[1]));
                wlDiff.add(diff);

                SortByRatio ratio = new SortByRatio(Integer.parseInt(weightAndLength[0]), Integer.parseInt(weightAndLength[1]));
                wlRatio.add(ratio);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("scheduleByDiff: " + scheduleByDiff(wlDiff, numOfTask));
        System.out.println("scheduleByRatio: " + scheduleByRatio(wlRatio, numOfTask));

    }

    public static long scheduleByDiff(ArrayList<SortByDiff> wlDiff, int numOfTask) {
        Collections.sort(wlDiff);
        long total = 0;
        long lengthByNow = 0;

        for (int i = numOfTask-1; i >= 0; i--) {
            lengthByNow += wlDiff.get(i).length;
            total += wlDiff.get(i).weight * lengthByNow;
        }

        return total;
    }

    public static long scheduleByRatio(ArrayList<SortByRatio> wlRatio, int numOFTask) {
        Collections.sort(wlRatio);
        long total = 0;
        long lengthByNow = 0;

        for (int i = numOFTask-1; i >= 0; i--) {
            lengthByNow += wlRatio.get(i).length;
            total += wlRatio.get(i).weight * lengthByNow;
        }

        return total;
    }
}
