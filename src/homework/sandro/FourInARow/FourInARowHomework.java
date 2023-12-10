package homework.sandro.FourInARow;

import homework.sandro.Colors;

import java.util.Scanner;

public class FourInARowHomework {
//    static {
//        if (Math.random() > 0.5) {
//            throw new RuntimeException("Hi Sandro, was geht ab?");
//        }
//    }

    public static void main(String[] args) {

        int[][] board = new int[6][7];
        int currentplayer = 1;
        boolean winner = false;
        boolean empty = true;

        printBoard(board, 6, 7);
        while (!winner && empty) {                       //Game runs
            int col;
            int row;
            while (true) {                               //Input read + check if legal move and column empty proof
                col = readInt("pick a Col, Player Number: " + currentplayer);
                row = emptyColumn(board, col);
                if (row == -1) {
                    System.out.println("try again, column is already full");
                } else {
                    break;
                }
            }


            board[row][col] = currentplayer;                //update board with input and print new board
            printBoard(board, 6, 7);


            winner = hasWin(board, currentplayer);          //detect if currentplayer move trigger a win condition
            empty = hasAnyMoreMoves(board, 6, 7);   //detect if board has empty spots, for Game is Draw detection

            if (currentplayer == 1) {                       // player change, swap to player 2
                currentplayer = 2;
            } else {
                currentplayer = 1;                          // player change, swap to player 1
            }
        }
        if (winner) {                                       //if win condition is triggered, print
            System.out.println("Winner" + currentplayer);
        } else {                                            //if now win condition is triggered and no empty spaces left, print
            System.out.println("Game is a Draw");
        }
    }


    private static int emptyColumn(int[][] board, int col) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][col] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static boolean hasWin(int[][] field, int currentplayer) {
        int countTopToBot = diagonalAdjacentMaxCountRecursive(field,new boolean[6][7],1,6,7);


        for (int i = 0; i < field.length; i++) {
            if (detectWinRow(field, i, currentplayer)
                    || (detectWinCol(field, i, currentplayer)       //what does "i" stand for (field, i, cur...)
                    || (countTopToBot >=4)



//                    || (detectWinDiagonalfront(field, currentplayer))
//                    || (detectWinDiagonalfront(field, row, col, currentplayer))
                                                                                   //ignore row col, testing purpose
//                    || (detectWinDiagonal2(field, currentplayer))
            )) {
                return true;
            }
        }
        return false;
    }

    public static boolean detectWinDiagonalfront(int[][] field, int currentplayer) {
        int count = 0;
        for (int i = 0; i < field.length -3; i++) {
            for (int j = 0; j < field[i].length -3; j++) {
                if (field[i][j] == currentplayer
                        || field[i + 1][j] == currentplayer
                        || field[i + 2][j] == currentplayer
                        || field[i][j + 1] == currentplayer
                        || field[i][j + 2] == currentplayer
                        || field[i][j + 3] == currentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean detectWinDiagonalfront3(int[][] field, int row, int col, int currentplayer) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == currentplayer) {
                    count++;
                }
                if (field[i + 1][j] == currentplayer) {
                    count++;
                }
                if (field[i + 2][j] == currentplayer) {
                    count++;
                }
                if (field[i][j + 1] == currentplayer) {
                    count++;
                }
                if (field[i][j + 2] == currentplayer) {
                    count++;
                }
                if (field[i][j + 3] == currentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean detectWinDiagonalfront2(int[][] field, int row, int col, int currentplayer) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == currentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (field[i + 1][j] == currentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (field[i + 2][j] == currentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (field[i][j + 1] == currentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (field[i][j + 2] == currentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (field[i][j + 3] == currentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean detectWinDiagonalfront1(int[][] field, int row, int col, int currentplayer) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == currentplayer
                        || field[i + 1][j] == currentplayer
                        || field[i + 2][j] == currentplayer
                        || field[i][j + 1] == currentplayer
                        || field[i][j + 2] == currentplayer
                        || field[i][j + 3] == currentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean detectWinDiagonalback(int[][] field, int currentplayer) {
        int count = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i][field.length - i - 1] == currentplayer
                    || field[i + 1][field.length - i - 1] == currentplayer
                    || field[i + 2][field.length - i - 1] == currentplayer
                    || field[i][field.length - (i + 1) - 1] == currentplayer
                    || field[i][field.length - (i + 2) - 1] == currentplayer
                    || field[i][field.length - (i + 3) - 1] == currentplayer) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }
    public static boolean detectWinRow(int[][] field, int row, int currentplayer) {         //do i need a int row (from "i") or could i just use field.length ?
        int count = 0;
        for (int i = 0; i < field[row].length; i++) {
            if (field[row][i] == currentplayer) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }
    public static boolean detectWinCol(int[][] field, int col, int currentplayer) {
        int count = 0;
        for (int i = 0; i < field[col].length - 1; i++) {  //why field[col].length has to be -1???
            if (field[i][col] == currentplayer) {           // condition row, did not need -1 ... what differents
                count++;                                       // is it -1, couse "i" value for col is one to big and exact for row
            } else {
                count = 0;
            }
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }
    public static boolean hasAnyMoreMoves(int[][] field, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (field[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    public static int readInt(String message) {
        int result = -1;
        Scanner sc = new Scanner(System.in);
        while (result < 0 || result > 6) {
            System.out.println(message);
            String line = sc.nextLine();
            try {
                result = Integer.parseInt(line);
                if (result < 0 || result > 6) {
                    System.out.println("illegal number");
                    System.out.println("pick a Col between 0 and 6");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("illegal input");
            }
        }
        return result;
    }
    public static void printBoard(int[][] field, int row, int col) {
        for (int i = 0; i < 7; i++) {
            System.out.print("Col:" + i + "|");
        }
        System.out.println();
        System.out.println("-----|-----|-----|-----|-----|-----|-----|");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
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



    private static final int BOARD_ROW_COUNT = 7;
    private static final int BOARD_COL_COUNT = 6;
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

}

