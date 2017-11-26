package VRPSolver;

import InputReader.InputReader;

public class VRPSolver {
    private InputReader inputReader;

    public void setInputReader(InputReader reader) {
        this.inputReader = reader;
    }

    public Result getRoute() {
        if (this.inputReader == null) {
            throw new RuntimeException("Invalid Input Reader");
        }
        int[][] matrix = this.inputReader.fetchMatrix();
        this.printMatrix(matrix);

        // Run the VPR

        return new Result().addNode("A", 0);
    }

    private void printMatrix(int[][] matrix) {
        for (int[] lines: matrix) {
            for (int values: lines) {
                System.out.print(values + " ");
            }
            System.out.println("");
        }
    }
}
