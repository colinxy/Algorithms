/**
 * Created by yxy on 3/30/2015.
 */
public class SortByDiff implements Comparable<SortByDiff>{
    public int weight;
    public int length;

    public SortByDiff(int weight, int length) {
        this.weight = weight;
        this.length = length;
    }

    public int diff() {
        return this.weight - this.length;
    }

    @Override
    public int compareTo(SortByDiff o) {
        int comp = (this.weight-this.length) - (o.weight-o.length);
        if (comp != 0)
            return comp;
        else
            return this.weight - o.weight;
    }
}
