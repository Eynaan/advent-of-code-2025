package day6;

import java.math.BigInteger;
import java.util.List;

final class Worksheet {
    private final List<Problem> problems;

    Worksheet(List<Problem> problems) {
        this.problems = List.copyOf(problems);
    }

    BigInteger grandTotal() {
        BigInteger sum = BigInteger.ZERO;
        for (Problem p : problems) {
            sum = sum.add(p.evaluate());
        }
        return sum;
    }
}
