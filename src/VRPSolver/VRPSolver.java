package VRPSolver;

import InputReader.InputReader;

public class VRPSolver {
    private InputReader inputReader;

    public void setInputReader(InputReader reader) {
        this.inputReader = reader;
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
