import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxy on 5/11/2015.
 */
public class Node {
    public int id;
    public List<Integer> out;

    public Node(int id) {
        this.id = id;
        out = new ArrayList<>();
    }
}
