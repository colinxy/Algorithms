/**
 * Created by yxy on 3/30/2015.
 */
public class SortByRatio implements Comparable<SortByRatio> {
    public int weight;
    public int length;

    public SortByRatio(int weight, int length) {
        this.weight = weight;
        this.length = length;
    }

    public double ratio() {
        return this.weight/(double)this.length;
    }

    @Override
    public int compareTo(SortByRatio o) {
        double comp = this.weight / (double)this.length - o.weight / (double)o.length;
        if (comp > 0)
            return 1;
        if (comp < 0)
            return -1;
        else return 0;
    }
}
