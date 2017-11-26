import Graph.Graph;
import Graph.Node;
import InputReader.InputReader;
import VRPSolver.VRPSolver;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        VRPSolver solver = new VRPSolver();
        Graph graph = new InputReader("input.txt").fetchGraph();
        ArrayList<Node> nodes = graph.getNodes();
        for (Node n : nodes) {
            System.out.println("Node: " + n.getName() + " neighbours:");
            n.getNeighbours().forEach((key, value) -> {
                System.out.println("" + key.getName() + " - " + value);
            });
        }
    }
}