package homework.benny.fourinarow;

public class FourInARow {

    private static final int BOARD_ROW_COUNT = 7;
    private static final int BOARD_COL_COUNT = 6;

    //top left to bottom right
    // board -> your game state
    // alreadyVisitedFields -> this is just a boolean 2d array of the same size of your game board to ensure that your recursion does not run endlessly -> stack overflow. You use it to remember which row, col combinations you already considered in your calculation
    // currentPlayer -> the player who just dropped their stone
    // row, col -> the LATEST move
    private static int diagonalAdjacentMaxCountRecursive(int[][] board, boolean[][] alreadyVisitedFields, int currentPlayer, int row, int col) {
        if (isIndexOutOfBounds(row, col) || alreadyVisitedFields[row][col]) {
            return 0;
        } else if (board[row][col] != currentPlayer) {
            return 0;
        } else {
            alreadyVisitedFields[row][col] = true;
            int topLeftToBottomRightCount = 1 + diagonalAdjacentMaxCountRecursive(board, alreadyVisitedFields, currentPlayer, row - 1, col - 1) + diagonalAdjacentMaxCountRecursive(board, alreadyVisitedFields, currentPlayer, row + 1, col + 1);
            int bottomLetftToTopRightCount = 1 + diagonalAdjacentMaxCountRecursive(board, alreadyVisitedFields, currentPlayer, row - 1, col + 1) + diagonalAdjacentMaxCountRecursive(board, alreadyVisitedFields, currentPlayer, row + 1, col - 1);
            // I check both diagonals and then return the bigger value
            return Math.max(topLeftToBottomRightCount, bottomLetftToTopRightCount);
        }
    }

    //TODO sandro: find a solution

    private static int diagonalAdjacentMaxCountLoop(int[][] board, int currentPlayer, int row, int col) {
        int topLeftToBottomRightCount = 0;

        //[row -3, col -3], [row-2, col-2], [row-1, col-1], [row, col], [row + 1, col + 1], [row + 2, col +2], [row + 3, col +3]
        for (int i = -3; i <= 3; i++) {
            if (board[row][col] == currentPlayer) {
                topLeftToBottomRightCount++;
            } else {
                topLeftToBottomRightCount = 0;
            }
            if (topLeftToBottomRightCount == 4) {
                return topLeftToBottomRightCount;
            }
        }

        // try to figure out what I mean with lower and upper. Look at the way I structured the [row, column] pairs, it is not random. They are aligned this way for a reason
        //[row -1, col + 1], [row + 1, col -1], [row -2, col + 2], [row + 2, col -2], [row - 3, col + 3], [row + 3, col - 3]
//        int
//        for (int i = 1; i <= 3; i++) {

//        }
        // bottomLeftToTopRight will be the sum of both
        return 0;
    }

    private static boolean isIndexOutOfBounds(int row, int col) {
        return row < 0 || row >= BOARD_ROW_COUNT || col < 0 || col >= BOARD_COL_COUNT;
    }

    public static void main(String[] args) {
        int[][] bottomLeftToTopRight = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0},
        };

        int countBotToTop = diagonalAdjacentMaxCountRecursive(bottomLeftToTopRight, new boolean[7][6], 1, 5, 1);
        if (countBotToTop >= 4) {
            System.out.println("Yey! Winner bottomLeftToTopRight");
        }

        int[][] topLeftToBottomRight = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 0},
                {0, 0, 0, 2, 0, 0},
                {0, 0, 0, 0, 2, 0},
                {0, 2, 0, 0, 0, 0},
        };
        int countTopToBot = diagonalAdjacentMaxCountRecursive(topLeftToBottomRight, new boolean[7][6], 2, 3, 2);
        if (countTopToBot >= 4) {
            System.out.println("Yey! Winner topLeftToBottomRight");
        }
    }

}
