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

    public int sizeSolution(ArrayList<Node> possibleSolution){
        int extension = 0;
        for (int i = 0; i < possibleSolution.size()-1; i++) {
            extension += possibleSolution.get(i).getNeighbours().get(possibleSolution.get(i+1));
        }
        return extension;
    }

    public void printRoute(ArrayList<Node> ext){
        for (Node n : ext) {
            System.out.print(n.getName() + " -> ");
        }
    }

}
