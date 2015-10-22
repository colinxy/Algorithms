import java.util.List;

/**
 * Created by yxy on 5/11/2015.
 */
public class Graph {
    public int numOfNodes;
    public Node[] positive;
    public Node[] negative;

    public Graph(int numOfNodes) {
        this.numOfNodes = numOfNodes;
        positive = new Node[numOfNodes+1];
        negative = new Node[numOfNodes+1];
    }

    public void addNode(int from, int to) {
        if (from > 0) {
            if (positive[from] == null) {
                positive[from] = new Node(from);
                positive[from].out.add(to);
            } else {
                positive[from].out.add(to);
            }
        } else {
            if (negative[-from] == null) {
                negative[-from] = new Node(-from);
                negative[-from].out.add(to);
            } else {
                negative[-from].out.add(to);
            }
        }
    }

    public Node getNode(int from) {
        if (from > 0) return positive[from];
        else return negative[-from];
    }

    public List<Integer> getEdges(int index) {
        if (index < 0) return negative[-index].out;
        else return positive[index].out;
    }
}
