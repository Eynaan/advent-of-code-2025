package day6;

import java.math.BigInteger;
import java.util.List;

final class Problem {
    enum Op { ADD, MUL }

    private final Op op;
    private final List<BigInteger> operands;

    Problem(Op op, List<BigInteger> operands) {
        if (operands.isEmpty()) {
            throw new IllegalArgumentException("Problem must have at least one operand.");
        }
        this.op = op;
        this.operands = List.copyOf(operands);
    }

    BigInteger evaluate() {
        BigInteger acc = (op == Op.ADD) ? BigInteger.ZERO : BigInteger.ONE;
        for (BigInteger x : operands) {
            acc = (op == Op.ADD) ? acc.add(x) : acc.multiply(x);
        }
        return acc;
    }
}