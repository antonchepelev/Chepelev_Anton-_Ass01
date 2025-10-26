import java.sql.Array;

public class TTTBoard {


    public enum Cell {
        X, O, EMPTY
    }

    public static Cell[][] board;


    //constructor that initializes board with EMPTY values 3x3
    public TTTBoard() {

        board = new Cell[3][3];
        clearBoard();




    }
    protected void setValue(int row, int col, Cell value) {
        board[row][col] = value;
    }




    protected void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    board[i][j] = Cell.EMPTY;
                }
            }
        }
    }


    protected Cell getValue(int row, int col) {
        return board[row][col];
    }


}





