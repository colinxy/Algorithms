import java.util.List;

/**
 * Created by yxy on 4/22/2015.
 */
public class BellmanFord {
    public static void bellmanFordAllPair(Graph graph) {
        long startTime = System.nanoTime();

        try {
            int min = Integer.MAX_VALUE;
            int source = 0;
            int target = 0;
            for (int i = 1; i <= graph.numOfVertices; i++) {
                int[] shortestPath = bellmanFord(graph, i);
                int thisMin = Integer.MAX_VALUE;
                int s = 0;
                int t = 0;
                for (int j = 1; j <= graph.numOfVertices; j++) {
                    if (shortestPath[j] < thisMin) {
                        thisMin = shortestPath[j];
                        s = i;
                        t = j;
                    }
                }
                System.out.println(s + " " + t + " " + thisMin);
                if (thisMin < min) {
                    min = thisMin;
                    source = s;
                    target = t;
                }
            }
            System.out.println(source + " " + target + " " + min);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("BellmanFordAllPairs running time: " + (System.nanoTime()-startTime)/1000000000.0 + " seconds\n");
    }

    public static int[] bellmanFord(Graph graph, int source) throws IllegalArgumentException{
        int[] cache = new int[graph.numOfVertices+1];  // vertex id
        for (int i = 0; i < cache.length; i++)
            cache[i] = 1 << 28;  // infinity
        cache[source] = 0;

        for (int i = 1; i < graph.numOfVertices; i++) {  // path with <= i edges, at most n-1 edges
            int[] cacheTemp = new int[graph.numOfVertices+1];

            for (int j = 1; j <= graph.numOfVertices; j++) {  // target vertex id
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
        }

        return cache;
    }

    public static int bellmanFord(Graph graph, int source, int target) throws IllegalArgumentException{
        return bellmanFord(graph, source)[target];
    }
}
