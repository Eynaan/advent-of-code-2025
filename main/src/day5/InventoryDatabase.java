package day5;

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
}
