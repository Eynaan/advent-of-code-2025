package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class MainPuzzle1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();
        String filename = "main/src/day4/input.txt";

        // Read all input lines
        lines = Files.readAllLines(Paths.get(filename));

        PaperYard paperYard = new PaperYard(lines);
        ForkliftOptimizer optimizer = new ForkliftOptimizer(paperYard);

        int result = optimizer.countAccessibleRolls();
        System.out.println("Number of accessible rolls: " + result);
    }
}
