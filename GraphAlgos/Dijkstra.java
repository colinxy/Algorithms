import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by yxy on 4/25/2015.
 */
public class Dijkstra {

    public static int dijkstra(Graph graph, int source, int target) {
        int[] greedyScores = new int[graph.numOfVertices+1];
        boolean[] explore = new boolean[graph.numOfVertices+1];
        PriorityQueue<DijkstraNode> frontier = new PriorityQueue<>();

        explore[source] = true;
        greedyScores[source] = 0;
        int justExplored = source;
        while (!explore[target]) {
            List<Edge> newFrontier = graph.getOutGoingEdges(justExplored);
            for (Edge e: newFrontier)
                if (!explore[e.to])
                    frontier.add(new DijkstraNode(justExplored, e.to, e.cost + greedyScores[justExplored]));

            DijkstraNode node = frontier.poll();
            greedyScores[node.to] = node.greedyScore;
            justExplored = node.to;
            explore[justExplored] = true;
        }

        return greedyScores[target];
    }

    public static int[] dijkstra(Graph graph, int source) {
        int[] greedyScores = new int[graph.numOfVertices+1];
        boolean[] explore = new boolean[graph.numOfVertices+1];
        int numOfExplored = 0;
        PriorityQueue<DijkstraNode> frontier = new PriorityQueue<>();

        explore[source] = true;
        numOfExplored++;
        greedyScores[source] = 0;
        int justExplored = source;
        while (true) {
            List<Edge> newFrontier = graph.getOutGoingEdges(justExplored);
            for (Edge e: newFrontier)
                if (!explore[e.to])
                    frontier.add(new DijkstraNode(justExplored, e.to, e.cost + greedyScores[justExplored]));

            DijkstraNode node = frontier.poll();
            if (node == null)
                break;
            greedyScores[node.to] = node.greedyScore;
            justExplored = node.to;
            explore[justExplored] = true;
            numOfExplored++;

            if (numOfExplored == graph.numOfVertices)
                break;
        }

        return greedyScores;
    }

    public static void runDijkstra(Graph graph) {
        long startTime = System.nanoTime();

        dijkstra(graph, 1, 100);
        dijkstra(graph, 2);

        System.out.println("Dijkstra running time: " + (System.nanoTime()-startTime)/1000000000.0 + " seconds\n");
    }
}

class DijkstraNode implements Comparable<DijkstraNode> {
    public int from;
    public int to;
    public int greedyScore;

    public DijkstraNode(int from, int to, int greedyScore) {
        this.from = from;
        this.to = to;
        this.greedyScore = greedyScore;
    }

    @Override
    public int compareTo(DijkstraNode other) {
        return this.greedyScore - other.greedyScore;
    }
}
