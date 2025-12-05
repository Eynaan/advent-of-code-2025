package day2;

public class IdRange {
    String range;
    long firstId;
    long lastId;

    public IdRange(String range){
        this.range = range;
        String[] parts = range.split("-");
        this.firstId = Long.parseLong(parts[0]);
        this.lastId = Long.parseLong(parts[1]);
    }
}
