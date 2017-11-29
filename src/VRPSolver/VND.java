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

    private void printRoute(ArrayList<Node> ext){
        for (Node n : ext) {
            System.out.print(n.getName() + " -> ");
        }
    }

    private ArrayList<Node> optSwap(ArrayList<Node> route,int i, int k) {
        ArrayList<Node> new_route = new ArrayList<Node>();

        for (int count = 0; count < i; count++)
            new_route.add(route.get(count));

        for (int count = k; i < count; count--)
            new_route.add(route.get(count));

        for (int count = k+1; count < route.size(); count++)
            new_route.add(route.get(count));

        return new_route;
    }

    private int sizeSolution(ArrayList<Node> possibleSolution){
        int extension = 0;
        for (int i = 0; i < possibleSolution.size()-1; i++) {
            extension += possibleSolution.get(i).getNeighbours().get(possibleSolution.get(i+1));
        }
        return extension;
    }

    private ArrayList<Node> optFunction(ArrayList<Node> route){
        int best_distance = sizeSolution(route);

        for (int i = 1; i < route.size() - 2; i++) {
            for (int k = i + 1; k < route.size() - 1; k++) {
                ArrayList<Node> new_route = optSwap(route, i, k);
                int new_distance = sizeSolution(new_route);
                if (new_distance < best_distance) {
                    return optFunction(new_route);
                }
            }
        }

        return route;
    }

    private ArrayList<Node> minimumPath (ArrayList<Node> solution){
        ArrayList<ArrayList<Node>> solutions = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> variation = new ArrayList<>();
        for (Node n : variation) {
            for (Node others : variation) {
                ArrayList<Node> partialSolution = new ArrayList<>();
                partialSolution.add(solution.get(0));

                if (!others.equals(n)) {
                    if (solution.indexOf(n) < solution.indexOf(others)) {
                        partialSolution.add(others);
                        for (int i = solution.indexOf(others) + 1; i < solution.size(); i++) {
                            if (i == solution.indexOf(others) + 1) {
                                partialSolution.add(n);
                            }
                            partialSolution.add(solution.get(i));
                        }
                    } else {
                        partialSolution.add(n);
                        for (int i = 1; i < solution.indexOf(n); i++) {
                            partialSolution.add(solution.get(i));
                        }
                        for (int i = solution.indexOf(n) + 1; i < solution.size(); i++) {
                            partialSolution.add(solution.get(i));
                        }
                    }
                } else {
                    if (solution.indexOf(others) > 1) {
                        for (int i = 1; i < solution.indexOf(others); i++) {
                            partialSolution.add(solution.get(i));
                        }
                    }
                    for (int i = solution.indexOf(others) + 1; i < solution.size(); i++) {
                        partialSolution.add(solution.get(i));
                    }
                    // the same value so we only remove it from the variation
                }
                solutions.add(partialSolution);
            }
        }
        int extension = sizeSolution(solution);
        ArrayList<Node> solutionFinal = solution;
        for (ArrayList<Node> possibleSolution : solutions) {
            extension = sizeSolution(possibleSolution);
            if(extension > sizeSolution(possibleSolution)){
                extension = sizeSolution(possibleSolution);
                solutionFinal = possibleSolution;
            }
        }
        return solutionFinal;
    }

    public void run(Node start, ArrayList<Node> route, ArrayList<Node> solution) {
        System.out.println("\nRunning VND...\n");

        ArrayList<Node> variation = new ArrayList<>();
        ArrayList<ArrayList<Node>> solutions = new ArrayList<ArrayList<Node>>();

        for (int i = 1; i < solution.size() - 1; i++) {
            variation.add(solution.get(i)); // Get the middle section (candidates of variation)
        }

        if (variation.isEmpty()) {
            System.out.println("Cannot run VND");
            return;
        }
        System.out.println();

        System.out.println("2-OPT");
        ArrayList<Node> routeOpt = optFunction(solution);
        printRoute(routeOpt);
        int sizeOpt = sizeSolution(routeOpt);
        System.out.println("Extension: "+sizeOpt);

        System.out.println("-----------------");

        System.out.println("Caminho Mínimo");
        ArrayList<Node> routeMinimumPath = minimumPath(solution);
        printRoute(routeMinimumPath);
        int sizeMinimumPath = sizeSolution(routeMinimumPath);
        System.out.println("Extension: "+sizeMinimumPath);

    }
}