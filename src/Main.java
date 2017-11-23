import InputReader.InputReader;
import VRPSolver.VRPSolver;

public class Main {
    public static void main(String[] args) {
        VRPSolver solver = new VRPSolver();
        solver.setInputReader(new InputReader("input.txt"));
        solver.getRoute();
    }
}