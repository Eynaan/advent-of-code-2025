package day5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventoryDatabase {
    private final List<Range> freshRanges;
    private final List<Long> ingredientIds;

    public InventoryDatabase(List<Range> freshRanges, List<Long> ingredientIds) {
        this.freshRanges = freshRanges;
        this.ingredientIds = ingredientIds;
    }

    /**
     * Count the number of fresh ingredients based on the defined fresh ranges.
     * @return the count of fresh ingredients
     */
    public int countFreshIngredients() {
        int count = 0;
        for (Long id : ingredientIds) {
            if (isFresh(id)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Check if the given ingredient ID is fresh.
     * @param id
     * @return true if the ingredient ID is fresh, false otherwise.
     */
    private boolean isFresh(long id) {
        for (Range range : freshRanges) {
            if (range.contains(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Count the total number of distinct fresh ingredient IDs covered by the fresh ranges.
     * @return the count of distinct fresh ingredient IDs
     */
    public long countDistinctFreshIds() {
        if (freshRanges.isEmpty()) {
            return 0L;
        }

        // Copy and sort ranges by start
        List<Range> sorted = new ArrayList<>(freshRanges);
        sorted.sort(Comparator.comparingLong(Range::getStart));

        long total = 0L;
        long currentStart = sorted.get(0).getStart();
        long currentEnd = sorted.get(0).getEnd();

        for (int i = 1; i < sorted.size(); i++) {
            Range r = sorted.get(i);
            long start = r.getStart();
            long end = r.getEnd();

            // If the next range overlaps or touches the current one, merge
            if (start <= currentEnd + 1) {
                if (end > currentEnd) {
                    currentEnd = end;
                }
            } else {
                // Add the size of the current merged range
                total += (currentEnd - currentStart + 1);
                // Start a new merged range
                currentStart = start;
                currentEnd = end;
            }
        }

        // Add the last merged range
        total += (currentEnd - currentStart + 1);

        return total;
    }
}
