import java.util.*;
public class SUDOKU_SOLVER {

    public static boolean isafe(char[][] board,int row,int col, int num)
    {
        //checkiing if rows and cols are safe 
        for(int i = 0;i<board.length;i++)
        {
            if(board[row][i] == (char)(num + '0'))
            {
                return false;
            }
            if(board[i][col] == (char)(num + '0'))
            {
                return false;
            }
        }



        //checking if the number that we are placing not in the grid
        int startrow = (row/3) * 3;
        int startcol = (col/3) * 3;

        for(int i = startrow;i<startrow+3;i++)
        {
            for(int j = startcol;j<startcol+3;j++)
            {
                if(board[i][j] == (char)(num+'0'))
                {
                    return false;
                }
            }
        }



        return true;


    }

    public  static boolean helper(char[][] board, int row, int col)
    {
        if(row == board.length)   //base case
        {
            return true;
        }

        int nrow = 0;
        int ncol = 0;

        if(col != board.length-1)   //cell increment condition one by one traversing whole row ....then next whole row...then next.....
        {
            nrow = row;
            ncol = col + 1;
        }
        else
        {
            nrow = row + 1;
            ncol = 0;
        }

        if(board[row][col] != '.')    //checking if there is free space to put the character
        {
            if(helper(board,nrow,ncol))
            {
                return true;
            }
        }
        else
        {
            for(int i = 1;i<=9;i++)
            {
                if(isafe(board,row,col,i))
                {
                    board[row][col] = (char)(i + '0');      //putting the character 

                    if(helper(board,nrow,ncol))   // here going to the next cell if the number we put gives the wrong soluton we jump into else for backtracking
                    {
                        return true;
                    }
                    else
                    {
                        board[row][col] = '.';    //backtracking
                    }
                }
            }
        }

        return false;

    }

    public static void sudoku(char[][] board)
    {
        helper(board,0,0);
    }
    public static void main(String[] args)
    {
        char[][] board = { { '3', '.', '6', '5', '.', '8', '4', '.', '.' },
                        { '5', '2', '.', '.', '.', '.', '.', '.', '.' },
                        { '.', '8', '7', '.', '.', '.', '.', '3', '1' },
                        { '.', '.', '3', '.', '1', '.', '.', '8', '.' },
                        { '9', '.', '.', '8', '6', '3', '.', '.', '5' },
                        { '.', '5', '.', '.', '9', '.', '6', '.', '.' },
                        { '1', '3', '.', '.', '.', '.', '2', '5', '.' },
                        { '.', '.', '.', '.', '.', '.', '.', '7', '4' },
                        { '.', '.', '5', '2', '.', '6', '3', '.', '.' } };


        sudoku(board);


        for(int i = 0;i<9;i++)
        {
            for(int j = 0;j<9;j++)
            {
                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }
        

    }
}
