import java.util.Arrays;
import java.util.List;

/**
 * Created by yxy on 4/24/2015.
 */
public class Graph {
    public int numOfVertices;
    public int numOfEdges;
    public Vertex[] vertices;  // vertex_id => vertex
    public int[] vertexWeight;

    public Graph(int numOfEdges, int numOfVertices) {
        this.numOfEdges = numOfEdges;
        this.numOfVertices = numOfVertices;
        this.vertices = new Vertex[numOfVertices+1];
    }

    public void addEdges(Edge e) {
        Vertex v;
        if (vertices[e.from] != null) {
            v = vertices[e.from];
            v.addOutGoing(e.to, e.cost);
        }
        else {
            v = new Vertex(e.from);
            v.addOutGoing(e.to, e.cost);
            vertices[e.from] = v;
        }

        if (vertices[e.to] != null) {
            v = vertices[e.to];
            v.addIngoing(e.from, e.cost);
        }
        else {
            v = new Vertex(e.to);
            v.addIngoing(e.from, e.cost);
            vertices[e.to] = v;
        }
    }

    public List<Edge> getInGoingEdges(int id) {
        return vertices[id].inGoing;
    }

    public List<Edge> getOutGoingEdges(int id) {
        return vertices[id].outGoing;
    }

    public boolean hasEdge(int from, int to) {
        try {
            getEdgeLength(from, to);
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public int getEdgeLength(int from, int to) throws RuntimeException{
        for (Edge e: getOutGoingEdges(from)) {
            if (e.to == to) {
                return e.cost;
            }
        }
        throw new RuntimeException("Edge not found");
    }

    /*public void enableVertexWeight() {
        vertexWeight = new int[numOfVertices+1];
    }

    public void addVertexWeight(int id, int weight) {
        vertexWeight[id] = weight;
    }*/

    public int getVertexWeight(int id) {
        return vertexWeight[id];
    }

    public Graph copy() {
        Graph graph = new Graph(numOfEdges, numOfVertices);
        for (int i = 1, j = this.vertices.length; i < j; i++) {
            graph.vertices[i] = this.vertices[i].copy();
        }
        if (this.vertexWeight != null)
            graph.vertexWeight = this.vertexWeight.clone();

        return graph;
    }
}
