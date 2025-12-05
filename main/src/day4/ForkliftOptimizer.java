package day4;

public class ForkliftOptimizer {
    private final PaperYard paperYard;

    public ForkliftOptimizer(PaperYard paperYard) {
        this.paperYard = paperYard;
    }

    public int countAccessibleRolls() {
        return paperYard.countAccessibleRolls();
    }
}
