package day6;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class WorksheetParser {

    Worksheet parse(List<String> rawLines) {
        // Normalize widths (pad with spaces) so column indexing is safe.
        int width = rawLines.stream().mapToInt(String::length).max().orElse(0);
        List<String> lines = new ArrayList<>(rawLines.size());
        for (String s : rawLines) {
            lines.add(padRight(s, width));
        }

        if (lines.size() < 2) {
            throw new IllegalArgumentException("Need at least one number row and one operator row.");
        }

        // Identify all-space columns (separators).
        boolean[] isSeparatorCol = findSeparatorColumns(lines, width);

        // Convert columns into segments [start, endExclusive] that are NOT separator columns.
        List<int[]> segments = findNonSeparatorSegments(isSeparatorCol);

        // Build problems from segments.
        String opLine = lines.get(lines.size() - 1);
        List<Problem> problems = new ArrayList<>();

        for (int[] seg : segments) {
            int start = seg[0], end = seg[1];

            Problem.Op op = parseOperator(opLine.substring(start, end));
            List<BigInteger> nums = new ArrayList<>();

            for (int r = 0; r < lines.size() - 1; r++) {
                String cell = lines.get(r).substring(start, end).trim();
                if (!cell.isEmpty()) {
                    nums.add(new BigInteger(cell));
                }
            }

            if (!nums.isEmpty()) {
                problems.add(new Problem(op, nums));
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
            // skip separators
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