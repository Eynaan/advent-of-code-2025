package day6;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class MainPuzzle2 {
    public static void main(String[] args) throws Exception {
        List<String> lines = readAllLinesFromFile("main/src/day6/input.txt");

        Worksheet worksheet = new WorksheetParserRTLColumns().parse(lines);
        BigInteger grandTotal = worksheet.grandTotal();

        System.out.println("Grand total (Part 2): " + grandTotal);
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

