package csp;
public class BacktrackingSolver{
    private int boardSize = 8;
    private int[] placedQueens = new int[boardSize];;
    int solutionsCount;
    int nodesExpanded;
    long totalTime;


    public void printSolution(int[] placedQueens) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == placedQueens[j])
                    System.out.print("Q ");
                else
                    System.out.print("# ");
            }
            System.out.print("\n");
        }
        System.out.println("---------------------------------------------------------");
    }

    private String conToString() {
        return totalTime + " ms, " + solutionsCount + " solutions, " + nodesExpanded + " nodes expanded.";
    }

    public void solve8Queens() {
        long startTime = System.currentTimeMillis();
        solve8QBacktracking(0);
        totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Backtracking algorithm: " + conToString());
    }

    private boolean solve8QBacktracking(int col) {
        if (col == boardSize) {
            solutionsCount++;
            printSolution(placedQueens);
            return true;
        }

        nodesExpanded++;

        for (int i = 0; i < boardSize; i++) {
            placedQueens[col] = i;
            if (isBoardValid(col)) {
                solve8QBacktracking(col + 1);
            }
        }
        return false;
    }

    private boolean isBoardValid(int lastInsertedColumn) {
        for (int i = 0; i < lastInsertedColumn; i++) {
            if (placedQueens[i] == placedQueens[lastInsertedColumn] ||
                    Math.abs(placedQueens[i] - placedQueens[lastInsertedColumn]) == Math.abs(lastInsertedColumn - i))
                return false;
        }
        return true;
    }

}