import Graph.Graph;
import Graph.Node;
import InputReader.InputReader;
import VRPSolver.*;
import java.util.ArrayList;

//    1  2  3  4
// 1  0  4  2  6
// 2  4  0  4  3
// 3  2  4  0  7
// 4  6  3  7  0

public class Main {
    public static void main(String[] args) {
        Graph graph = new InputReader("input.txt").fetchGraph();

//        ArrayList<Node> nodes = graph.getNodes();
//        for (Node n : nodes) {
//            System.out.println("Node: " + n.getName() + " neighbours:");
//            n.getNeighbours().forEach((key, value) -> {
//                System.out.println("" + key.getName() + " - " + value);
//            });
//        }

        ArrayList<Node> route = new ArrayList<>();

        route.add(graph.getNode("2"));
        route.add(graph.getNode("4"));

        VND solverVND = new VND(graph);
        Result resultVND = solverVND.findRoute(graph.getNode("1"), route);
        System.out.println(resultVND.toString());
        solverVND.run(graph.getNode("1"), route, resultVND.getRoute());

        GRASP solverGRASP = new GRASP(graph);
        Result resultGRASP = solverGRASP.findRoute(graph.getNode("1"), route);
        System.out.println(resultGRASP.toString());
        solverGRASP.run(graph.getNode("1"), route, resultGRASP.getRoute());
    }
}