package VRPSolver;

import Graph.Graph;
import Graph.Node;


import java.util.ArrayList;
import java.util.Map;

public class GRASP extends VRPSolver  {

    public GRASP(Graph graph) {
        super(graph);
    }

    @Override
    public Result findRoute(Node start, ArrayList<Node> route) {
        Result result = new Result();
        result.addNode(start, 0);
        int MAX = Integer.MAX_VALUE;
        Node node = null;

        ArrayList<Node> removed = new ArrayList<>();
        removed.add(start);
        start.setVisited(true);

        while (!route.isEmpty()) {
            MAX = Integer.MAX_VALUE;

            for (Map.Entry<Node, Integer> entry : start.getNeighbours().entrySet()) {
                if (entry.getValue() < MAX && !entry.getKey().isVisited()) {
                    MAX = entry.getValue();
                    node = entry.getKey();
                }
            }

            if (route.size() == 1) {
                if (!route.contains(node)) {
                    if (start.getNeighbours().containsKey(node)) {
                        start = removed.remove(removed.size()-1); // Readd last removed
                        start.getNeighbours().containsKey(node); // We do not need this route
                    }
                }
            }


            if (route.contains(node)) {
                route.remove(node);
            }

            start = node;
            start.setVisited(true);
            removed.add(start);

            result.addNode(node, MAX);
        }

        return result;
    }

    private ArrayList<Node> searchLocal(Node point, ArrayList<Node> mapRoutes, ArrayList<Node> pointsRoute){
        ArrayList<Node> solution = null;
        return solution;
    }



    private ArrayList<Node> graspFunction(int iteration, Node seed, ArrayList<Node> mapRoutes, ArrayList<Node> pointsRoute){
        int extension = -1;
        ArrayList<Node> solution = null;
        for(int i = 0; i < iteration; i++){
            ArrayList<Node> route = searchLocal(seed, mapRoutes, pointsRoute);

            if(sizeSolution(route) < extension) {
                solution = route;
                extension = sizeSolution(route);
            }
        }
        return solution;
    }

    public void run(Node start, ArrayList<Node> route, ArrayList<Node> mapRoutes) {
        graspFunction(100, start, mapRoutes, route);
    }
}
