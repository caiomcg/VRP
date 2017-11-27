package VRPSolver;

import Graph.Graph;
import Graph.Node;

import java.util.ArrayList;

public abstract class VRPSolver {
    protected Graph graph;

    public VRPSolver(Graph graph) {
        this.graph = graph;
    }

    public abstract Result findRoute(Node start, ArrayList<Node> route);
}
