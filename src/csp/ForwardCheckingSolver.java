package csp;

public class ForwardCheckingSolver{
    private final int boardSize = 8;
    private int[] placedQueens = new int[boardSize];
    private int[][] fieldsThreatArray = new int[boardSize][boardSize];

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
        solve8QWithForwardChecking(0);
        totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Forward checking algorithm: " + conToString());
    }

    private boolean solve8QWithForwardChecking(int col) {
        if (col == boardSize) {
            solutionsCount++;
            printSolution(placedQueens);
            return true;
        }

        nodesExpanded++;

        for (int i = 0; i < boardSize; i++) {
            if (fieldsThreatArray[i][col] == 0) {
                placedQueens[col] = i;
                addUnlegalplace(i, col);
                if (isBoardValid(col)) {
                    solve8QWithForwardChecking(col + 1);
                }
                addLegalplace(i, col);
            }
        }
        return false;
    }

    private void addUnlegalplace(int lastInsertedRow, int lastInsertedColumn) {
        updateBoard(1, lastInsertedRow, lastInsertedColumn);
    }

    private void addLegalplace(int lastInsertedRow, int lastInsertedColumn) {
        updateBoard(-1, lastInsertedRow, lastInsertedColumn);
    }

    private void updateBoard(int value, int lastInsertedRow, int lastInsertedColumn) {
        for (int j = 1; j < boardSize - lastInsertedColumn; j++) {
            fieldsThreatArray[lastInsertedRow][lastInsertedColumn + j] += value; //modify all places horizontally
            if (lastInsertedRow + j < boardSize)
                fieldsThreatArray[lastInsertedRow + j][lastInsertedColumn + j] += value; // modify places diagonally down
            if (lastInsertedRow - j >= 0)
                fieldsThreatArray[lastInsertedRow - j][lastInsertedColumn + j] += value; //modify places diagonally up
        }
    }

    private boolean isBoardValid(int lastInsertedColumn) {
        for (int i = lastInsertedColumn + 1; i < boardSize; i++) {
            boolean place = false;
            for (int row = 0; row < boardSize && !place; row++) {
                if (fieldsThreatArray[row][i] == 0) { // if no queens threat that field
                    place = true;
                }
            }
            if (!place)
                return false;
        }
        return true;
    }
}