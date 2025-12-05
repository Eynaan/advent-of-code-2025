package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPuzzle2 {
    
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
     * Checks if a number is invalid (made of a sequence repeated at least twice)
     * Examples: 12341234 (1234 twice), 123123123 (123 three times), 
     *           1212121212 (12 five times), 1111111 (1 seven times)
     */
    private static boolean isInvalidId(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();
        
        // Try all possible pattern lengths from 1 to len/2
        for (int patternLen = 1; patternLen <= len / 2; patternLen++) {
            // Check if the entire string can be made by repeating a pattern of this length
            if (len % patternLen == 0) {
                String pattern = idStr.substring(0, patternLen);
                boolean isRepeated = true;
                
                // Check if the pattern repeats throughout the entire string
                for (int i = patternLen; i < len; i += patternLen) {
                    String segment = idStr.substring(i, i + patternLen);
                    if (!segment.equals(pattern)) {
                        isRepeated = false;
                        break;
                    }
                }
                
                // If pattern repeats at least twice (len/patternLen >= 2)
                if (isRepeated && len / patternLen >= 2) {
                    return true;
                }
            }
        }
        
        return false;
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
