package homework.benny.fourinarow;

import homework.sandro.Colors;

import java.util.Scanner;

public class FourInARowSandroModified {
    private static final int ROW_COUNT = 6;
    private static final int COL_COUNT = 7;
    private static final Scanner sc = new Scanner(System.in);


    private static int emptyColumn(int[][] board, int col) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][col] == 0) {
                return i;
            }
        }
        return -1;
    }

    private static boolean hasWinner(int[][] field, int row, int col, int currentPlayer) {
        return detectWinRow(field, row, currentPlayer)
            || detectWinCol(field, col, currentPlayer)
            || detectWinDiagonalSolutionTwo(field, row, col, currentPlayer);
    }

    private static boolean isMoveInvalid(int row, int col) {
        return !isRowValid(row) || !isColValid(col);
    }

    private static boolean isColValid(int c) {
        return c >= 0 && c < COL_COUNT;
    }

    private static boolean isRowValid(int r) {
        return r >= 0 && r < ROW_COUNT;
    }

    // compressed form of detectWinDiagonalOne by using the same strategy for both diagonals
    private static boolean detectWinDiagonalSolutionTwo(int[][] field, int row, int col, int currentPlayer) {
        int[][] directions = {
            {-1, -1},
            {1, 1},
            {-1, 1},
            {1, -1}
        };
        //here we store the sum of our four different diagonal directions (with the latest move in the center)
        int[] partialSums = new int[4];

        //for each direction
        for (int i = 0; i < directions.length; i++) {
            // check up to 3 fields in this direction (k = 1 -> first field, k = 2 -> second field, k = 3 -> third field)
            for (int k = 1; k <= 3; k++) {
                // if field is out of bounds, we stop
                if (isMoveInvalid(row + k * directions[i][0], col + k * directions[i][1])) {
                    break;
                }
                // if field contains value we search, we increment partial sum for that direction
                else if (field[row + k * directions[i][0]][col + k * directions[i][1]] == currentPlayer) {
                    partialSums[i]++;
                }
                // field has valid board coordinates, but it does not contain our player. We stop
                else {
                    break;
                }
            }
        }

        // partialSums[0] + partialSums[1] is topLeftToBottomRight diagonal
        // partialSums[2] + partialSums[3] is bottomLeftToTopRight diagonal
        // >= 3, because we do not consider the latest move itself in the loop. But that one holds our currentPlayer. So we do not have to find 4, just 3
        return partialSums[0] + partialSums[1] >= 3 || partialSums[2] + partialSums[3] >= 3;
    }


    private static boolean detectWinDiagonalSolutionOne(int[][] field, int row, int col, int currentPlayer) {
        int topLeftToBottomRight = 0;
        for (int i = -3; i <= 3; i++) {
            if (isMoveInvalid(row + i, col + i)) {
                continue;
            } else if (field[row + i][col + i] == currentPlayer) {
                topLeftToBottomRight++;
            } else if (i < 0) {
                topLeftToBottomRight = 0;
            } else {
                break;
            }
            if (topLeftToBottomRight >= 4) {
                return true;
            }
        }

        int bottomLeftToTopRightUpper = 0;
        for (int i = 1; i <= 3; i++) {
            if (isMoveInvalid(row - i, col + i)) {
                break;
            } else if (field[row - i][col + i] == currentPlayer) {
                bottomLeftToTopRightUpper++;
            } else {
                break;
            }
        }
        int bottomLeftToTopRightLower = 0;
        for (int i = 1; i <= 3; i++) {
            if (isMoveInvalid(row + i, col - i)) {
                break;
            } else if (field[row + i][col - i] == currentPlayer) {
                bottomLeftToTopRightLower++;
            } else {
                break;
            }
        }

        return bottomLeftToTopRightLower + bottomLeftToTopRightUpper + 1 >= 4;
    }

    private static int getCount(int currentPlayer, int targetFieldPlayer, int currentCount) {
        return currentPlayer == targetFieldPlayer ? ++currentCount : 0;
    }

    private static boolean detectWinRow(int[][] field, int row, int currentPlayer) {
        int count = 0;
        for (int i = 0; i < COL_COUNT; i++) {
            count = getCount(currentPlayer, field[row][i], count);
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }

    private static boolean detectWinCol(int[][] field, int col, int currentPlayer) {
        int count = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            count = getCount(currentPlayer, field[i][col], count);
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasAnyMoreMoves(int[][] field) {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                if (field[row][col] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int readInt(String message) {
        int nextCol = -1;
        do {
            System.out.println(message);
            String line = sc.nextLine();
            try {
                nextCol = Integer.parseInt(line);
                if (!isColValid(nextCol)) {
                    System.out.println("illegal number");
                    System.out.println("pick a Col between 0 and 6");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("illegal input");
            }

        } while (!isColValid(nextCol));

        return nextCol;
    }


    private static void printBoard(int[][] field) {
        for (int i = 0; i < COL_COUNT; i++) {
            System.out.print("Col:" + i + "|");
        }
        System.out.println();
        System.out.println("-----|-----|-----|-----|-----|-----|-----|");
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                int board = field[i][j];
                if (board == 1) {
                    System.out.print("  " + Colors.COLORS[1] + board + Colors.RESET + "  |");
                } else if (board == 2) {
                    System.out.print("  " + Colors.COLORS[2] + board + Colors.RESET + "  |");
                } else {
                    System.out.print("  " + Colors.COLORS[5] + board + Colors.RESET + "  |");
                }
            }
            System.out.println();
        }


        System.out.println("==========================================");
    }

    public static void main(String[] args) {

        int[][] board = new int[ROW_COUNT][COL_COUNT];
        int currentPlayer = 1;
        boolean hasWinner = false;
        boolean isTie = false;

        printBoard(board);
        while (!(hasWinner || isTie)) {                       //Game runs
            int col, row;
            while (true) {                               //Input read + check if legal move and column isTie proof
                col = readInt("pick a Col, Player Number: " + currentPlayer);
                row = emptyColumn(board, col);
                if (row != -1) {
                    break;
                }
                System.out.println("try again, column is already full");
            }

            board[row][col] = currentPlayer;                //update board with input and print new board
            printBoard(board);

            hasWinner = hasWinner(board, row, col, currentPlayer);          //detect if currentPlayer move trigger a win condition
            isTie = !hasAnyMoreMoves(board);   //detect if board has isTie spots, for Game is Draw detection

            if (!hasWinner) {
                currentPlayer = currentPlayer == 1 ? 2 : 1;
            }
        }
        if (hasWinner) {                                       //if win condition is triggered, print
            System.out.println("Winner" + currentPlayer);
        } else {                                            //if now win condition is triggered and no isTie spaces left, print
            System.out.println("Game is a Draw");
        }
    }

}

