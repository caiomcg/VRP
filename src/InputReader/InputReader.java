package InputReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader implements Reader{
    private String filePath;

    public InputReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int[][] fetchMatrix() { // Null should be replaced with a custom exception
        String line;
        int matrixSize = 0;

        int lineIndex    = 0;
        int columnIndex = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath));

            if ((line = bufferedReader.readLine()) != null) {
                try {
                    matrixSize = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            int[][] matrix = new int[matrixSize][matrixSize];

            while ((line = bufferedReader.readLine()) != null) {
                String[] nodes = line.split(" ");
                for (String node : nodes) {
                    matrix[lineIndex][columnIndex++] = Integer.parseInt(node);
                }
                columnIndex = 0;
                lineIndex++;
            }
            return matrix;
        } catch(IOException e) {
            return null;
        }
    }

}
