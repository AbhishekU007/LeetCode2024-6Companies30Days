/*
Problem Overview
The Sudoku board is a 9x9 grid where each cell can contain a number from 1 to 9. The objective is to fill the grid such that:

Each row contains all digits from 1 to 9 without repetition.
Each column contains all digits from 1 to 9 without repetition.
Each of the nine 3x3 subgrids contains all digits from 1 to 9 without repetition.
*/

import java.util.Scanner;

class Solution {
    public void solveSudoku(char[][] board) {
        // Start solving the Sudoku from the first empty cell
        solve(board);
    }

    private boolean solve(char[][] board) {
        // Loop through each cell in the 9x9 grid
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {3
                // If we find an empty cell (denoted by '.')
                if (board[row][col] == '.') {
                    // Try every number from 1 to 9
                    for (char num = '1'; num <= '9'; num++) {
                        // Check if placing 'num' at (row, col) is valid
                        if (isValid(board, row, col, num)) {
                            // Place the number
                            board[row][col] = num;

                            // Recursively attempt to fill the rest of the board
                            if (solve(board)) {
                                return true; // If successful, return true
                            }

                            // If placing 'num' did not lead to a solution, reset the cell
                            board[row][col] = '.'; // Backtrack
                        }
                    }
                    // If no number from 1 to 9 worked, return false
                    return false;
                }
            }
        }
        // If we reach here, it means the board is completely filled
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        // Check the current row for duplicates
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false; // Number already in row
            }
        }

        // Check the current column for duplicates
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false; // Number already in column
            }
        }

        // Check the 3x3 subgrid for duplicates
        int startRow = row - row % 3; // Start row of the subgrid
        int startCol = col - col % 3; // Start column of the subgrid
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false; // Number already in the 3x3 subgrid
                }
            }
        }

        // If no conflicts found, it's valid to place 'num'
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Solution solution = new Solution();
        char[][] board = new char[9][9];

        System.out.println("Enter the Sudoku board (use '.' for empty cells):");
        for (int i = 0; i < 9; i++) {
            String line = scanner.nextLine();
            board[i] = line.toCharArray(); // Convert the string line to char array
        }

        // Solve the Sudoku puzzle
        solution.solveSudoku(board);

        // Print the solved Sudoku board
        System.out.println("Solved Sudoku board:");
        for (char[] row : board) {
            System.out.println(row);
        }

        scanner.close(); // Close the scanner to free resources
    }
}


/*

Input
board =
[["5","3",".",".","7",".",".",".","."],
["6",".",".","1","9","5",".",".","."],
[".","9","8",".",".",".",".","6","."],
["8",".",".",".","6",".",".",".","3"],
["4",".",".","8",".","3",".",".","1"],
["7",".",".",".","2",".",".",".","6"],
[".","6",".",".",".",".","2","8","."],
[".",".",".","4","1","9",".",".","5"],
[".",".",".",".","8",".",".","7","9"]]
Output
[["5","3","4","6","7","8","9","1","2"],
["6","7","2","1","9","5","3","4","8"],
["1","9","8","3","4","2","5","6","7"],
["8","5","9","7","6","1","4","2","3"],
["4","2","6","8","5","3","7","9","1"],
["7","1","3","9","2","4","8","5","6"],
["9","6","1","5","3","7","2","8","4"],
["2","8","7","4","1","9","6","3","5"],
["3","4","5","2","8","6","1","7","9"]]

 */