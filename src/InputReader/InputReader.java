package InputReader;

import Graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader implements Reader{
    private String filePath;

    public InputReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Graph fetchGraph() { // Null should be replaced with a custom exception
        Graph graph = new Graph();
        String line;

        int matrixSize = 0;
        int depth = 1;
        int offset = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath));

            if ((line = bufferedReader.readLine()) != null) {
                try {
                    matrixSize = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            offset = matrixSize - 1;

            for (int i = 1; i <= matrixSize; i++) {
                graph.addNode(String.valueOf(i));
                graph.addNeighbour(String.valueOf(i), String.valueOf(i), 0);
            }

            while ((line = bufferedReader.readLine()) != null) {
                String[] nodes = line.split(" ");
                for (int i = nodes.length; i >= 1; i--) {
                    int index = i + (matrixSize - offset);
                    graph.addNeighbour(String.valueOf(depth), String.valueOf(index), Integer.parseInt(nodes[i-1]));
                    graph.addNeighbour(String.valueOf(index), String.valueOf(depth), Integer.parseInt(nodes[i-1]));
                }
                depth++;
                offset--;
            }

            return graph;
        } catch(IOException e) {
            return null;
        }
    }

}
