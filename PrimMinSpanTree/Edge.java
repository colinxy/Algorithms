/**
 * Created by yxy on 3/30/2015.
 */
public class Edge implements Comparable<Edge>{
    public int from;
    public int to;
    public int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

