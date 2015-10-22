import java.util.List;

/**
 * Created by yxy on 4/25/2015.
 */
public class Johnson {
    public static void computeVertexWeight(Graph graph) {
        for (int i = 1; i <= graph.numOfVertices; i++) {
            graph.addEdges(new Edge(0, i ,0));
        }

        /*int[] cache = new int[graph.numOfVertices+1];  // vertex id
        for (int i = 0; i < cache.length; i++)
            cache[i] = 1 << 28;  // infinity
        cache[0] = 0;

        /*for (int i = 1; i < graph.numOfVertices; i++) {  // path with <= i edges
            int[] cacheTemp = new int[graph.numOfVertices+1];

            for (int j = 0; j <= graph.numOfVertices; j++) {  // target vertex id
                int minL = cache[j];
                List<Edge> inGoingEdgesOfJ = graph.getInGoingEdges(j);
                for (Edge e: inGoingEdgesOfJ) {
                    int currentL = cache[e.from] + e.cost;

                    if (currentL < minL)
                        minL = currentL;
                }
                cacheTemp[j] = minL;
            }
            cache = cacheTemp;
        }

        // 1 extra iteration to check for negative cycles
        for (int j = 1; j <= graph.numOfVertices; j++) {
            List<Edge> inGoingEdgesOfJ = graph.getInGoingEdges(j);
            for (Edge e: inGoingEdgesOfJ) {
                if (cache[e.from] + e.cost < cache[j]) {
                    throw new IllegalArgumentException("negative cycle");
                }
            }
        }*/

        graph.vertexWeight = BellmanFord.bellmanFord(graph, 0);
    }

    public static void updateEdgeCost(Graph graph) {
        for (int i = 1; i <= graph.numOfVertices; i++) {
            List<Edge> outGoingEdgesOfI = graph.getOutGoingEdges(i);
            for (Edge e: outGoingEdgesOfI) {
                e.cost += graph.getVertexWeight(e.from)-graph.getVertexWeight(e.to);

                if (e.cost < 0) throw new RuntimeException("negative edge cost");
            }
        }
    }

    public static int johnson(Graph graph) {
        graph = graph.copy();
        computeVertexWeight(graph);
        updateEdgeCost(graph);

        int min = Integer.MAX_VALUE;
        int source = 0;
        int target = 0;
        for (int i = 1; i <= graph.numOfVertices; i++) {
            int[] dijkstraResultOfI = Dijkstra.dijkstra(graph, i);
            for (int j = 1; j <= graph.numOfVertices; j++) {
                int current = dijkstraResultOfI[j] - graph.getVertexWeight(i) + graph.getVertexWeight(j);

                if (current < min) {
                    min = current;
                    source = i;
                    target = j;
                }
            }
            // System.out.println(source + " " + target + " " + min);
        }
        System.out.println(source + " " + target + " " + min);

        return min;
    }

    public static void runJohnson(Graph graph) {
        long startTime = System.nanoTime();

        try {
            System.out.println("shortest shortest path: " + johnson(graph));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Johnson running time: " + ((System.nanoTime()-startTime)/1000000000.0 + " seconds\n"));
    }
}
