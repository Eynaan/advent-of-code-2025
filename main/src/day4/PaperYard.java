package day4;

import java.util.List;

public class PaperYard {
    private final char[][] grid;
    private final int rows;
    private final int columns;

    public PaperYard(List<String> lines) {
        this.rows = lines.size();
        this.columns = lines.get(0).length();
        this.grid = new char [rows][columns];

        for (int r = 0; r < rows; r++) {
            String line = lines.get(r);
            for (int c = 0; c < columns; c++) {
                grid[r][c] = line.charAt(c);
            }
        }
    }

    /**
     * Determine if there is a roll at position p
     * @param p the position to check
     * @return true if there is a roll, false otherwise
     */
    public boolean isRoll(Position p) {
        int row = p.getRow();
        int column = p.getColumn();
        if(!inBounds(row, column)) return false;
        return grid[row][column] == '@';
    }

    /**
     * Determine if a roll at position p is accessible
     * @param Postion p the position to check
     * @return true if accessible, false otherwise
     */
    public boolean isAccessible(Position p) {
        if (!isRoll(p)) {
            return false;
        }

        int row = p.getRow();
        int column = p.getColumn();
        int neighbourRolls = 0;

        // Check all 8 neighbouring cells
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) {
                    continue; // skip the cell itself
                }
                int nr = row + dr;
                int nc = column + dc;
                if (inBounds(nr, nc) && grid[nr][nc] == '@') {
                    neighbourRolls++;
                }
            }
        }

        // Accessible if fewer than 4 neighbouring rolls
        return neighbourRolls < 4;
    }

    /**
     * Count all accessible rolls in the yard
     * @return the number of accessible rolls
     */
    public int countAccessibleRolls() {
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                Position p = new Position(r, c);
                if (isAccessible(p)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean inBounds(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
}
