package day5;

public class MainPuzzle2 {
    public static void main(String[] args) {
        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = "main/src/day5/input.txt";
        }

        InventoryParser parser = new InventoryParser();
        try {
            InventoryDatabase db = parser.parse(filePath);
            long result = db.countDistinctFreshIds();
            System.out.println("Number of ingredient IDs considered fresh by the ranges: " + result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
