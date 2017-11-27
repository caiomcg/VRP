package VRPSolver;

import java.util.ArrayList;
import Graph.Node;

public class Result {
    private int extension; // Sum of the route
    private ArrayList<Node> route; // Nodes the route pass by

    public Result() {
        this.route = new ArrayList<>();
    }

    public Result addNode(Node node, int value) {
        this.route.add(node);
        this.extension += value;
        return this;
    }

    public ArrayList<Node> getRoute() {
        return route;
    }

    public int getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        String routeRepresentation = "";

        for (Node n : route) {
            routeRepresentation += n.getName() + " -> ";
        }

        return "Extension: " + String.valueOf(this.getExtension()) + "\nRoute: " + routeRepresentation;
    }
}
