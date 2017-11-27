package VRPSolver;

import Graph.Graph;
import Graph.Node;
import java.util.ArrayList;
import java.util.Map;

public class VND extends VRPSolver {
    public VND(Graph graph) {
        super(graph);
    }

    @Override
    public ArrayList<Result> findRoute(Node start, ArrayList<Node> route) {
        ArrayList<Result> results = new ArrayList<>();
        System.out.println(this.graph.getNodes().size());

        while (true) {
            Result result = new Result();
            result.addNode(start, 0);

            while (!route.isEmpty()) {
                Node next = route.get(0);

                for (Map.Entry<Node, Integer> entry : start.getNeighbours().entrySet()) {
                    if (entry.getKey().equals(next)) {
                        result.addNode(route.remove(0), entry.getValue());
                        start = next;
                        break;
                    }
                }
            }
            results.add(result);
            break;
        }
        return results;
    }
}