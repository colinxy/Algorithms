import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yxy on 4/25/2015.
 */
public class ReadGraph {
    public static Graph readGraph(String filePath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine();
        String[] firstLine = line.split(" ");
        int numOfVertices = Integer.parseInt(firstLine[0]);
        int numOfEdges = Integer.parseInt(firstLine[1]);
        Graph graph = new Graph(numOfEdges, numOfVertices);

        while ((line = br.readLine()) != null) {
            String[] edgeAndCost = line.split(" ");
            int tail = Integer.parseInt(edgeAndCost[0]);
            int head = Integer.parseInt(edgeAndCost[1]);
            int cost = Integer.parseInt(edgeAndCost[2]);

            graph.addEdges(new Edge(tail, head, cost));
        }

        return graph;
    }
}
