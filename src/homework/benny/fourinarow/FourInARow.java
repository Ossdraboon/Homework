package homework.benny.fourinarow;

public class FourInARow {

    private static final int BOARD_ROW_COUNT = 7;
    private static final int BOARD_COL_COUNT = 6;

    //top left to bottom right
    // board -> your game state
    // alreadyVisitedFields -> this is just a boolean 2d array of the same size of your game board to ensure that your recursion does not run endlessly -> stack overflow. You use it to remember which row, col combinations you already considered in your calculation
    // currentPlayer -> the player who just dropped their stone
    // row, col -> the LATEST move
    private static int diagonalOne(int[][] board, boolean[][] alreadyVisitedFields, int currentPlayer, int row, int col) {
        if(isIndexOutOfBounds(row, col) ||  alreadyVisitedFields[row][col]) {
            return 0;
        } else if(board[row][col] != currentPlayer) {
            return 0;
        } else {
            alreadyVisitedFields[row][col] = true;
            return 1 +  diagonalOne(board, alreadyVisitedFields, currentPlayer, row - 1, col - 1) + diagonalOne(board, alreadyVisitedFields, currentPlayer, row + 1, col + 1);
        }
    }

    // bottom left to top right
    private static int diagonalTwo(int[][] board, boolean[][] alreadyVisitedFields,  int currentPlayer, int row, int col) {
        if(isIndexOutOfBounds(row, col) || alreadyVisitedFields[row][col]) {
            return 0;
        } else if(board[row][col] != currentPlayer) {
            return 0;
        } else {
            alreadyVisitedFields[row][col] = true;
            return 1 +  diagonalTwo(board, alreadyVisitedFields, currentPlayer, row - 1, col + 1) + diagonalTwo(board, alreadyVisitedFields, currentPlayer, row +1, col - 1);
        }
    }

    private static boolean isIndexOutOfBounds(int row, int col) {
        return row < 0 || row >= BOARD_ROW_COUNT  || col < 0 || col >= BOARD_COL_COUNT;
    }

    public static void main(String[] args) {
        int[][] bottomLeftToTopRight = {
            {0,0,0,0,0,0},
            {0,0,0,0,0,0},
            {0,0,0,0,1,0},
            {0,0,0,1,0,0},
            {0,0,1,0,0,0},
            {0,1,0,0,0,0},
            {0,2,0,0,0,0},
        };

        int countBotToTop = diagonalTwo(bottomLeftToTopRight, new boolean[7][6],1, 5, 1);
        if(countBotToTop >= 4) {
            System.out.println("Yey! Winner bottomLeftToTopRight");
        }

        int[][] topLeftToBottomRight = {
            {0,0,0,0,0,0},
            {0,0,0,0,0,0},
            {0,2,0,0,0,0},
            {0,0,2,0,0,0},
            {0,0,0,2,0,0},
            {0,0,0,0,2,0},
            {0,2,0,0,0,0},
        };
        int countTopToBot = diagonalOne(topLeftToBottomRight, new boolean[7][6],2, 3, 2);
        System.out.println(countTopToBot);
        if(countTopToBot >= 4) {
            System.out.println("Yey! Winner topLeftToBottomRight");
        }
    }

}
