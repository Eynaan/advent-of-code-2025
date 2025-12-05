package day5;

import java.io.IOException;

public class MainPuzzle1 {
    public static void main(String[] args) {
        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            // Default file name if none is provided as an argument
            filePath = "main/src/day5/input.txt";
        }

        InventoryParser parser = new InventoryParser();
        try {
            InventoryDatabase db = parser.parse(filePath);
            int result = db.countFreshIngredients();
            System.out.println("Number of fresh ingredient IDs: " + result);
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input format: " + e.getMessage());
        }
    }
}
