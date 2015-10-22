import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yxy on 3/30/2015.
 */
public class Graph {
    public int numOfVertex = 0;
    private ArrayList<Vertex> Vertices = new ArrayList<>();

    public Graph() {
        this.Vertices.add(new Vertex());  // empty vertex to make sure vertex index starts at 1
    }

    public void addVertex(Vertex v) { // add vertices sequentially
        Vertices.add(v);
        numOfVertex++;
    }

    public void setVertexAddEdge(int thisIndex, int thatIndex, int cost) {
        getVertex(thisIndex).addEdge(thatIndex, cost);
    }

    public Vertex getVertex(int index) { // vertex index start at 1
        return this.Vertices.get(index);
    }

    public HashMap<Integer, Integer> eachEdgeInVertex(int index) {
        return getVertex(index).getOutGoing();
    }
}
