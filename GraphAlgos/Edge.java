/**
 * Created by yxy on 4/23/2015.
 */
public class Edge implements Comparable<Edge> {
    public int from;
    public int to;
    public int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge other) {
        return this.cost - other.cost;
    }
}
