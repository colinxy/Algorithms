import java.util.Arrays;
import java.util.List;

/**
 * Created by yxy on 4/25/2015.
 */
public class FloydWarshall {
    public static int[][] floydWarshallAllPair(Graph graph) throws IllegalArgumentException{
        int[][] cache = new int[graph.numOfVertices+1][graph.numOfVertices+1];

        // initial state
        for (int i = 1; i <= graph.numOfVertices; i++) {
            for (int j = 1; j <= graph.numOfVertices; j++) {
                if (i != j) {
                    cache[i][j] = 1 << 28;  // infinity
                }
            }
        }
        for (int i = 1; i <= graph.numOfVertices; i++) {
            List<Edge> outGoingEdgesOfI = graph.getOutGoingEdges(i);
            for (Edge e: outGoingEdgesOfI) {
                cache[e.from][e.to] = e.cost;
            }
        }

        /*for (int i = 1; i <= graph.numOfVertices; i++) {
            for (int j = 1; j <= graph.numOfVertices; j++) {
                if (i == j) cache[i][j] = 0;

                else if (graph.hasEdge(i, j)) cache[i][j] = graph.getEdgeLength(i, j);

                else cache[i][j] = 1 << 28;  // infinity
            }
        }*/

        for (int k = 1; k <= graph.numOfVertices; k++) {  // Vertex set: {1, 2, 3, ... k}
            int[][] cacheTemp = new int[graph.numOfVertices+1][graph.numOfVertices+1];

            for (int i = 1; i <= graph.numOfVertices; i++) {
                for (int j = 1; j <= graph.numOfVertices; j++) {
                    int withVertexK = cache[i][k] + cache[k][j];

                    if (withVertexK < cache[i][j]) cacheTemp[i][j] = withVertexK;
                    else cacheTemp[i][j] = cache[i][j];
                }
            }

            cache = cacheTemp;
        }

        // check for negative cycles
        for (int i = 1; i <= graph.numOfVertices; i++) {
            if (cache[i][i] < 0) {
                throw new IllegalArgumentException("negative cycle");
            }
        }

        return cache;
    }

    public static void runFloydWarshall(Graph graph) {
        long startTime = System.nanoTime();

        try {
            int[][] allPair = floydWarshallAllPair(graph);
            System.out.println("all pairs shortest path computed by " + (System.nanoTime()-startTime)/1000000000.0 + " seconds");
            int min = Integer.MAX_VALUE;
            int source = 0;
            int target = 0;
            for (int i = 1; i <= graph.numOfVertices; i++) {
                for (int j = 1; j <= graph.numOfVertices; j++) {
                    if (allPair[i][j] < min) {
                        min = allPair[i][j];
                        source = i;
                        target = j;
                    }
                }
            }
            System.out.println(source + " " + target + " " + min);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("FloydWarshall running time: " + ((System.nanoTime()-startTime)/1000000000.0 + " seconds\n"));
    }
}
