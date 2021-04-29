import java.util.Scanner;
public class Sudoku {
 
    public static void main(String[] args) {

        int[][] board = {{0,2,0,0,0,8,0,0,0},
        {4,8,0,0,1,0,0,0,5},
        {6,0,7,0,0,0,0,0,0},
        {0,5,0,0,6,0,7,0,0},
        {0,0,0,4,8,7,0,0,0},
        {0,0,4,0,9,0,0,2,0},
        {0,0,0,0,0,0,6,0,9},
        {9,0,0,0,7,0,0,8,1},
        {0,0,0,5,0,0,0,3,0}};  
        

        
        
        //we print starting board
        print_initial(board, board.length);
        //we call function <sudoku> to solve the board
        sudoku(board);
        //print solved board
        System.out.println("AFTER SOLVING : ");
        print(board, board.length);
 
    }
    //function for solving board
    public static boolean sudoku(int[][] board) {
        //we make array for rows, and if its empty, returns true
        int[] ra = Unassigned(board);
        if (ra[0] == -1) {
            return true;
        }
 
        int row = ra[0];
        int col = ra[1];
        //for every num we check with <isSafe> if num is safe to put in the cell
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
 
                //if its safe to put the num in the cell we save it in the cell and save boolean check = call sudoku again until board is solved
                board[row][col] = num;
                boolean check = sudoku(board);
                if (check) {
                    return true;
                }
                board[row][col] = 0;
 
 
            }
        }
        //returns false if none of nums is safe to use in the cell
        return false;
    }
 
    //this function returns first position where there is no num entered
    public static int[] Unassigned(int[][] board) {
 
        int[] ra = new int[2]; 
        ra[0] = -1;
        ra[1] = -1;
 
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == 0) {
                    ra[0] = row;
                    ra[1] = col;
                    return ra;
                }
            }
        }
 
        return ra;
 
 
    }
    //next three functions check if num is either used in box, col or row
 
    public static boolean usedInRow(int[][] grid, int row, int num) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][i] == num) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean usedIncol(int[][] grid, int col, int num) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] == num) {
                return true;
            }
        }
        return false;
    }
 
    public static boolean usedInBox(int[][] grid, int row1Start, int col1Start, int num) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid[row + row1Start][col + col1Start] == num) {
                    return true;
                }
        return false;
 
    }
 
    //if num is not used in row and colum and in box return true
    public static boolean isSafe(int[][] grid, int row, int col, int num) {//is it safe to place that number at that position, might not be correct nut just safe
 
        return (!usedIncol(grid, col, num) && !usedInRow(grid, row, num) && !usedInBox(grid, row - row % 3, col - col % 3, num));
 
    }
    // prints solved  sudoku board
    public static void print(int[][] board, int N) {
        for (int i = 0; i < N; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("----------|---------|----------");
            }
            
            for (int j = 0; j < N; j++) {
 
 
                if (j % 3 == 0) {
                    System.out.print("|");
                }
                System.out.print(" " + board[i][j]
                        + " ");
 
            }
            System.out.println();
        }
 
 
    }

    // prints the initial sudoku board
    public static void print_initial(int[][] board, int N) {
        for (int i = 0; i < N; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("----------|---------|----------");
            }
            
            for (int j = 0; j < N; j++) {
 
 
                if (j % 3 == 0) {
                    System.out.print("|");
                }
                if(board[i][j]==0){
                    System.out.print(" " + "-"
                            + " ");
                }
                else {
                    System.out.print(" " + board[i][j]
                            + " ");
                }
 
            }
            System.out.println();
        }
 
 
    }
 
    //this function checks if num is safe to use in given row, col
    public static boolean isSafe(int row, int col, int[][] board, int N) {
        //checking rows
        int i;
        int j;
 
        /* Check this row on left side */
        for (i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;
 
        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;
 
        /* Check lower diagonal on left side */
        for (i = row, j = col; i >= 0 && j < N; j++, i--)
            if (board[i][j] == 1)
                return false;
 
        return true;
 
    }
 
}
