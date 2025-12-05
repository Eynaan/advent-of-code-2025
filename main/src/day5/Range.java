package day5;

public class Range {
    private final long start;
    private final long end;

    public Range(long start, long end) {
        if (start > end) {
            throw new IllegalArgumentException("Start of range cannot be greater than end: " + start + " > " + end);
        }
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    /**
     * Check if the range contains the given value.
     * @param value
     * @return true if the value is within the range, false otherwise.
     */
    public boolean contains(long value) {
        return value >= start && value <= end;
    }

    /**
     * Get a string representation of the range in the format "start-end".
     * @return String representation of the range.
     */
    @Override
    public String toString() {
        return start + "-" + end;
    }
}