package Graph;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private String name;
    private boolean visited;
    private Map<Node, Integer> neighbours;

    public Node(String name) {
        this.name = name;
        this.neighbours = new HashMap<>();
    }

    public void addNeighbour(Node node, Integer weight) {
        this.neighbours.put(node, weight);
    }

    public Map<Node, Integer> getNeighbours() {
        return neighbours;
    }

    public String getName() {
        return name;
    }
}
