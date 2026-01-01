package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MainPuzzle1 {
    public static void main(String[] args) throws Exception {
    List<String> lines = readAllLinesFromFile("main/src/day6/input.txt");

    Worksheet worksheet = new WorksheetParser().parse(lines);
    BigInteger grandTotal = worksheet.grandTotal();

    // THIS prints the answer to the terminal
    System.out.println("Grand total: " + grandTotal);
}


    private static List<String> readAllLinesFromFile(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String s;
            while ((s = br.readLine()) != null) {
                lines.add(s);
            }
        }
        return lines;
    }
}