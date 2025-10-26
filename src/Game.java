public class Game {

    enum Player { X, O }
    private Player currentPlayer = Player.X;
    private TTTBoard tttboard = new TTTBoard();



    public Game(){

        //fills board/ clears previous game
        tttboard.clearBoard();

    }

    public boolean isWin(Player player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }

        return false;
    }
    private boolean isColWin(Player player) {
        TTTBoard.Cell c = TTTBoard.Cell.valueOf(player.name());
        for (int col = 0; col < 3; col++) {
            if (tttboard.getValue(0, col) == c &&
                    tttboard.getValue(1, col) == c &&
                    tttboard.getValue(2, col) == c) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin(Player player) {
        TTTBoard.Cell c = TTTBoard.Cell.valueOf(player.name());

        for (int row = 0; row < 3; row++) {
            if (tttboard.getValue(row, 0) == c &&
                    tttboard.getValue(row, 1) == c &&
                    tttboard.getValue(row, 2) == c) {
                return true;
            }
        }
        return false;
    }
    private boolean isDiagonalWin(Player player) {
        // Convert Player to matching Cell enum value
        TTTBoard.Cell cell = TTTBoard.Cell.valueOf(player.name());

        // Main diagonal (top-left → bottom-right)
        if (tttboard.getValue(0, 0) == cell &&
                tttboard.getValue(1, 1) == cell &&
                tttboard.getValue(2, 2) == cell) {
            return true;
        }

        // Anti-diagonal (top-right → bottom-left)
        if (tttboard.getValue(0, 2) == cell &&
                tttboard.getValue(1, 1) == cell &&
                tttboard.getValue(2, 0) == cell) {
            return true;
        }

        return false;
    }

    public boolean isTie() {
        boolean xFlag = false;
        boolean oFlag = false;

        // Row checks
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                TTTBoard.Cell cell = tttboard.getValue(row, col);
                if (cell == TTTBoard.Cell.X) xFlag = true;
                if (cell == TTTBoard.Cell.O) oFlag = true;
            }

            if (!(xFlag && oFlag)) {
                return false; // One player can still win on this row
            }
            xFlag = oFlag = false;
        }

        // Column checks
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                TTTBoard.Cell cell = tttboard.getValue(row, col);
                if (cell == TTTBoard.Cell.X) xFlag = true;
                if (cell == TTTBoard.Cell.O) oFlag = true;
            }

            if (!(xFlag && oFlag)) {
                return false; // One player can still win on this column
            }
            xFlag = oFlag = false;
        }

        // Diagonal (top-left → bottom-right)
        for (int i = 0; i < 3; i++) {
            TTTBoard.Cell cell = tttboard.getValue(i, i);
            if (cell == TTTBoard.Cell.X) xFlag = true;
            if (cell == TTTBoard.Cell.O) oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false; // Still a possible win diagonal
        }

        // Diagonal (top-right → bottom-left)
        xFlag = oFlag = false;
        for (int i = 0; i < 3; i++) {
            TTTBoard.Cell cell = tttboard.getValue(i, 2 - i);
            if (cell == TTTBoard.Cell.X) xFlag = true;
            if (cell == TTTBoard.Cell.O) oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false; // Still a possible win diagonal
        }

        // If all vectors have both X and O, it's a tie
        return true;
    }



    protected void changePlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    protected Player getCurrentPlayer() {

        return currentPlayer;

    }

    public void reset() {
        tttboard.clearBoard();
        currentPlayer = Player.X;
    }


}
