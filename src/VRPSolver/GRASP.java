package VRPSolver;

import Graph.Graph;
import Graph.Node;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GRASP extends VRPSolver  {

    public GRASP(Graph graph) {
        super(graph);
    }

    @Override
    public Result findRoute(Node start, ArrayList<Node> route) {
        return null;
    }

    private int searchNode(Node n, ArrayList<Node> pointsRoute){
        int i = 0;
        for (Node item : pointsRoute){
            if(item.getName().equals(n.getName()))
                return i;
            i++;
        }

        return -1;
    }

    private ArrayList<Node> searchLocal(Node start, ArrayList<Node> mapRoutes, ArrayList<Node> pR){

        ArrayList<Node> pointsRoute = new ArrayList<Node>(pR);

        ArrayList<Node> solution = new ArrayList<Node>();

        Random generator = new Random();

        Map<Node, Integer> neighBours = null;

        for (Node n : graph.getNodes()){
            n.setVisited(false);
        }
        start.setVisited(true);

        solution.add(start);

        for (;pointsRoute.size() != 0;) {
            Map<Node, Integer> neighBoursAvaliable =  new HashMap<Node, Integer>();
            neighBours = start.getNeighbours();
            for (Map.Entry<Node, Integer> item : neighBours.entrySet()) {
                Node node = item.getKey();
                if (!node.isVisited())
                    neighBoursAvaliable.put(node, item.getValue());
            }

            if (neighBoursAvaliable != null) {
                int index = generator.nextInt(neighBoursAvaliable.size()), i = 0;

                for (Map.Entry<Node, Integer> item : neighBoursAvaliable.entrySet()) {
                    if (i == index) {
                        Node node = item.getKey();
                        node.setVisited(true);
                        solution.add(node);
                        start = node;
                        try{
                            pointsRoute.remove(searchNode(node, pointsRoute));
                        }catch(ArrayIndexOutOfBoundsException e){
                            ;
                        }

                        break;
                    }
                    i++;
                }
            }
        }
        return solution;
    }

    private ArrayList<Node> graspFunction(int iteration, Node seed, ArrayList<Node> mapRoutes, ArrayList<Node> pointsRoute){
        int extension = -1;
        ArrayList<Node> solution = null;


        for(int i = 0; i < iteration; i++){
            ArrayList<Node> route = searchLocal(seed, mapRoutes, pointsRoute);
            int auxExtension = sizeSolution(route);
            if(auxExtension < extension || extension == -1) {
                solution = route;
                extension = auxExtension;
            }
        }
        return solution;
    }

    public void run(Node start, ArrayList<Node> route, ArrayList<Node> mapRoutes) {
        ArrayList<Node> ext = graspFunction(100, start, mapRoutes, route);
        System.out.println("-----------------");
        System.out.println("Grasp");
        printRoute(ext);
        System.out.println("Extension: "+sizeSolution(ext));
    }
}
