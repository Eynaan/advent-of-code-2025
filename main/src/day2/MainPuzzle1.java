package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPuzzle1 {
    
    public static void main(String[] args) {
        try {
            // Read the input file
            String input = readInputFile("src/main/resources/input.txt");
            
            // Parse the ranges
            String[] rangeStrings = input.trim().split(",");
            List<IdRange> ranges = new ArrayList<>();
            for (String rangeStr : rangeStrings) {
                ranges.add(new IdRange(rangeStr.trim()));
            }
            
            // Find all invalid IDs and sum them
            long sum = 0;
            for (IdRange range : ranges) {
                sum += findInvalidIdsInRange(range);
            }
            
            System.out.println("Sum of all invalid IDs: " + sum);
            
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Checks if a number is invalid (made of a sequence repeated twice)
     * Examples: 55 (5 twice), 6464 (64 twice), 123123 (123 twice)
     */
    private static boolean isInvalidId(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();
        
        // Must have even length to be repeated twice
        if (len % 2 != 0) {
            return false;
        }
        
        int halfLen = len / 2;
        String firstHalf = idStr.substring(0, halfLen);
        String secondHalf = idStr.substring(halfLen);
        
        return firstHalf.equals(secondHalf);
    }
    
    /**
     * Finds all invalid IDs in a given range and returns their sum
     */
    private static long findInvalidIdsInRange(IdRange range) {
        long sum = 0;
        for (long id = range.firstId; id <= range.lastId; id++) {
            if (isInvalidId(id)) {
                sum += id;
            }
        }
        return sum;
    }
    
    /**
     * Reads the entire content of a file
     */
    private static String readInputFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
}
