package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryParser {
    // Parse the inventory file and return an InventoryDatabase object
    public InventoryDatabase parse(String filePath) throws IOException {
        List<Range> ranges = new ArrayList<>();
        List<Long> ingredientIds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            boolean readingRanges = true;
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Blank line switches from ranges section to ingredient IDs section
                if (line.isEmpty()) {
                    readingRanges = false;
                    continue;
                }

                if (readingRanges) {
                    ranges.add(parseRange(line));
                } else {
                    ingredientIds.add(Long.parseLong(line));
                }
            }
        }

        return new InventoryDatabase(ranges, ingredientIds);
    }

    /**
     * Parse a line representing a range in the format "start-end".
     * @param line
     * @return Range object
     */
    private Range parseRange(String line) {
        String[] parts = line.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid range line: " + line);
        }
        long start = Long.parseLong(parts[0].trim());
        long end = Long.parseLong(parts[1].trim());
        return new Range(start, end);
    }
}
