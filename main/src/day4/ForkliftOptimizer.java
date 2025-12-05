package day4;

public class ForkliftOptimizer {
    private final PaperYard paperYard;

    public ForkliftOptimizer(PaperYard paperYard) {
        this.paperYard = paperYard;
    }

    public int countAccessibleRolls() {
        return paperYard.countAccessibleRolls();
    }

    /**
     * Compute the total number of removable rolls by repeatedly removing accessible rolls
     * @return the total number of rolls removed
     */
    public int computeTotalRemovableRolls() {
        int total = 0;
        int removed;
        do {
            removed = paperYard.removeAccessibleRollsOnce();
            total += removed;
        } while (removed > 0);
        return total;
    }
}
