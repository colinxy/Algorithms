import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxy on 4/23/2015.
 */
public class Vertex {
    public int id;
    public List<Edge> outGoing;
    public List<Edge> inGoing;

    public Vertex(int id) {
        this.id = id;
        this.outGoing = new ArrayList<>();
        this.inGoing = new ArrayList<>();
    }

    public void addOutGoing(int to, int cost) {
        this.outGoing.add(new Edge(id, to, cost));
    }

    public void addIngoing(int from, int cost) {
        this.inGoing.add(new Edge(from, id, cost));
    }

    public Vertex copy() {
        Vertex v = new Vertex(this.id);
        for (Edge e: this.outGoing) {
            v.addOutGoing(e.to, e.cost);
        }
        for (Edge e: this.inGoing) {
            v.addIngoing(e.from, e.cost);
        }

        return v;
    }
}
