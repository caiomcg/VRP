package Graph;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addNode(String name) {
        this.nodes.add(new Node(name));
    }

    public void addNeighbour(String node_name, String neighbour_name, int weight) {
        Node from = this.getNode(node_name);
        if (from == null) {
            from = new Node(node_name);
            this.nodes.add(from);
        }

        Node to = this.getNode(neighbour_name);
        if (to == null) {
            to = new Node(node_name);
            this.nodes.add(to);
        }

        from.addNeighbour(to, weight);
    }

    public Node getNode(String name) {
        for (Node n : nodes) {
            if (n.getName().equals(name)) {
                return n;
            }
        }
        return null;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
