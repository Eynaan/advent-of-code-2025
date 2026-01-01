package day6;

import java.math.BigInteger;
import java.util.*;

public final class WorksheetParserRTLColumns {

    public Worksheet parse(List<String> rawLines) {
        if (rawLines == null || rawLines.isEmpty()) {
            return new Worksheet(List.of());
        }
        if (rawLines.size() < 2) {
            throw new IllegalArgumentException("Need at least one digit row and one operator row.");
        }

        int height = rawLines.size();
        int width = rawLines.stream().mapToInt(String::length).max().orElse(0);

        List<String> lines = new ArrayList<>(height);
        for (String s : rawLines) lines.add(padRight(s, width));

        boolean[] separatorCols = findSeparatorColumns(lines, width);
        List<int[]> segments = findNonSeparatorSegments(separatorCols);

        String opLine = lines.get(height - 1);
        List<Problem> problems = new ArrayList<>();

        for (int[] seg : segments) {
            int start = seg[0], end = seg[1]; // [start, end)
            Problem.Op op = parseOperator(opLine.substring(start, end));

            // Read columns from right to left, each column -> one number
            List<BigInteger> operands = new ArrayList<>();

            for (int c = end - 1; c >= start; c--) {
                StringBuilder digits = new StringBuilder();
                for (int r = 0; r < height - 1; r++) { // exclude operator row
                    char ch = lines.get(r).charAt(c);
                    if (ch != ' ') digits.append(ch);
                }
                if (digits.length() > 0) {
                    // digits are already top->bottom = most->least
                    operands.add(new BigInteger(digits.toString()));
                }
            }

            if (!operands.isEmpty()) {
                problems.add(new Problem(op, operands));
            }
        }

        return new Worksheet(problems);
    }

    private static String padRight(String s, int width) {
        if (s.length() >= width) return s;
        StringBuilder sb = new StringBuilder(width);
        sb.append(s);
        while (sb.length() < width) sb.append(' ');
        return sb.toString();
    }

    private static boolean[] findSeparatorColumns(List<String> lines, int width) {
        boolean[] sep = new boolean[width];
        Arrays.fill(sep, true);

        for (int c = 0; c < width; c++) {
            for (String line : lines) {
                if (line.charAt(c) != ' ') {
                    sep[c] = false;
                    break;
                }
            }
        }
        return sep;
    }

    private static List<int[]> findNonSeparatorSegments(boolean[] isSep) {
        List<int[]> segs = new ArrayList<>();
        int n = isSep.length;
        int i = 0;
        while (i < n) {
            while (i < n && isSep[i]) i++;
            if (i >= n) break;
            int start = i;
            while (i < n && !isSep[i]) i++;
            int end = i;
            segs.add(new int[]{start, end});
        }
        return segs;
    }

    private static Problem.Op parseOperator(String opSlice) {
        String t = opSlice.trim();
        if (t.equals("+")) return Problem.Op.ADD;
        if (t.equals("*")) return Problem.Op.MUL;
        throw new IllegalArgumentException("Unknown operator in slice: '" + t + "'");
    }
}

