package day4;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainPuzzle2 {
    public static void main(String[] args) throws Exception {
        String filename = "main/src/day4/input.txt";

        List<String> lines = Files.readAllLines(Paths.get(filename));

        PaperYard yard = new PaperYard(lines);
        ForkliftOptimizer optimizer = new ForkliftOptimizer(yard);

        int totalRemoved = optimizer.computeTotalRemovableRolls();
        System.out.println("Total number of removable rolls: " + totalRemoved);
    }
}
