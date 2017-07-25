

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class NQueen {

    public static void main(String[] args) {
        NQueens nqueens = new NQueens();
        int[][] solutions = nqueens.nQueens(4);
        printSolution(solutions);
    }

    private static void printSolution(int[][] solution) {
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class QueensComparator implements Comparator<int[]> {

        @Override
        public int compare(int[] o1, int[] o2) {
            for (int i = 0; i < o1.length; i++) {
                if (o1[i] != o2[i]) {
                    return o1[i] - o2[i];
                }
            }
            return 0;
        }
    }

    static class NQueens {

        int[][] nQueens(int n) {
            if (n == 1) {
                return new int[][] { { 1 } };
            }
            int[][] grid = new int[n][n];
            List<int[][]> validBoards = new ArrayList<int[][]>();
            backtrack(grid, n, validBoards);
            Set<int[]> validSet = new TreeSet<int[]>(new QueensComparator());

            for (int i = 0; i < validBoards.size(); i++) {
                validSet.add(buildSolution(validBoards.get(i)));
            }
            int[][] solutions = new int[validSet.size()][n];
            Iterator<int[]> it = validSet.iterator();
            int i = 0;
            while (it.hasNext()) {
                solutions[i++] = it.next();
            }
            return solutions;
        }

        int[] buildSolution(int[][] board) {
            int[] solution = new int[board.length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 1) {
                        solution[j] = i + 1;
                    }
                }
            }
            return solution;
        }

        int[][] clone(int[][] grid) {
            int[][] cloneGrid = new int[grid.length][grid.length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    cloneGrid[i][j] = grid[i][j];
                }
            }
            return cloneGrid;
        }

        void backtrack(int[][] grid, int n, List<int[][]> boards) {
            if (n == 0) {
                int[][] clone = clone(grid);
                boards.add(clone);
            } else {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[n - 1][j] == 0 && !isUnsafe(grid, n - 1, j)) {
                        grid[n - 1][j] = 1;
                        backtrack(grid, n - 1, boards);
                        grid[n - 1][j] = 0;
                    }
                }
            }
        }

        boolean isUnsafe(int[][] board, int x, int y) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 1) {
                        if (i == x || j == y || x + y == i + j
                                || x - y == i - j) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
}
