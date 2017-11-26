package VRPSolver;

import java.util.ArrayList;

public class Result {
    private int extension; // Sum of the route
    private ArrayList<String> route; // Nodes the route pass by

    public Result() {
        this.route = new ArrayList<>();
    }

    public Result addNode(String node, int value) {
        this.route.add(node);
        this.extension += value;
        return this;
    }

    public ArrayList<String> getRoute() {
        return route;
    }

    public int getExtension() {
        return extension;
    }
}
