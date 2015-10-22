import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Created by yxy on 3/30/2015.
 */
public class PrimMST {
    public static void primMST(String fileName) {
        int numOfVertex = 0;
        int numOfEdge = 0;
        Graph graph = new Graph();
        int minCost = 100000;
        int minVertex1 = 0;
        int minVertex2 = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = br.readLine();
            String[] firstLine= line.split(" ");
            numOfVertex = Integer.parseInt(firstLine[0]);
            numOfEdge = Integer.parseInt(firstLine[1]);

            for (int i = 1; i < numOfVertex+1; i++) {
                graph.addVertex(new Vertex(i));
            }

            while ((line = br.readLine()) != null) {
                String[] edgeAndCost = line.split(" ");
                int vertex1 = Integer.parseInt(edgeAndCost[0]);
                int vertex2 = Integer.parseInt(edgeAndCost[1]);
                int cost = Integer.parseInt(edgeAndCost[2]);

                graph.setVertexAddEdge(vertex1, vertex2, cost);
                graph.setVertexAddEdge(vertex2, vertex1, cost);

                if (cost < minCost) {
                    minCost = cost;
                    minVertex1 = vertex1;
                    minVertex2 = vertex2;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("overall cost of minimal spanning tree: " + costOfMST(graph, minVertex1, minVertex2, minCost));
    }

    public static long costOfMST(Graph graph, int startVertex1, int startVertex2, int minCost) {
        long totalCost = minCost;
        graph.getVertex(startVertex1).inTheTree = true;
        graph.getVertex(startVertex2).inTheTree = true;

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        pushHeap(graph, minHeap, startVertex1);
        pushHeap(graph, minHeap, startVertex2);

        for (int i = 0; i < graph.numOfVertex-2; i++)
            totalCost += nextMinCost(graph, minHeap);

        return totalCost;
    }

    public static int nextMinCost(Graph graph, PriorityQueue<Edge> minHeap) {
        Edge e = minHeap.poll();
        while (graph.getVertex(e.to).inTheTree) {
            e = minHeap.poll();
        }
        graph.getVertex(e.to).inTheTree = true;

        pushHeap(graph, minHeap, e.to);

        return e.cost;
    }

    public static void pushHeap(Graph graph, PriorityQueue<Edge> minHeap, int index) {
        for (int iter : graph.eachEdgeInVertex(index).keySet()) {
            if (!graph.getVertex(iter).inTheTree) {
                int cost = graph.eachEdgeInVertex(index).get(iter);
                minHeap.add(new Edge(index, iter, cost));
            }
        }
    }
}
