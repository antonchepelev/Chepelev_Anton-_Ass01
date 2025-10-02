import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    enum Player { X, O }
    private Player currentPlayer = Player.X;
    static TicTacToeTile[][] board = new TicTacToeTile[3][3];
    static int ROW =3;
    static int COL = 3;
    int playAgain;

    /** base setup      */
    public TicTacToeFrame() {
        setTitle("TicTacToe");

        setSize(500, 500);

        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        gamePanel();
        bottomPanel();

    }

    public void gamePanel() {
                         // 2D array

                        JPanel tileButtonPanel = new JPanel();
                        tileButtonPanel.setLayout(new GridLayout(3,3));
                        tileButtonPanel.setBackground(Color.white);

                        //adds button for every tile value in array
                        for (int row = 0; row < 3; row++) {
                            for (int col = 0; col < 3; col++) {
                                board[row][col] = new TicTacToeTile(row, col);
                                board[row][col].setBackground(Color.WHITE);


                                int rowValue = board[row][col].getRow();
                                int colValue = board[row][col].getCol();

                                board[row][col].addActionListener(_ -> {

                                    if (!board[rowValue][colValue].getText().isEmpty()) {
                                        JOptionPane.showMessageDialog(board[rowValue][colValue], "Please make a correct selection");
                                        return;
                                    }
                                    String currentPlayerString = currentPlayer.toString();
                                    board[rowValue][colValue].setFont(new Font("Arial", Font.BOLD, 70));

                                    board[rowValue][colValue].setText(currentPlayerString);


                                    switch (currentPlayer) {
                                        case X:
                                            board[rowValue][colValue].setBackground(Color.RED);
                                            break;
                                        case O:
                                            board[rowValue][colValue].setBackground(Color.BLUE);
                                            break;
                                    }

                                    if (isWin(currentPlayerString)){

                                        playAgain = JOptionPane.showOptionDialog(
                                                null,                          // parent component
                                                "player " + currentPlayerString + " won!! Do you want to play again?",     // message
                                                "Choose an option",             // title
                                                JOptionPane.YES_NO_OPTION,      // option type
                                                JOptionPane.QUESTION_MESSAGE,   // message type
                                                null,                           // icon
                                                new String[]{"Yes", "No"}, // custom buttons
                                                "Button 1"                      // default button
                                        );
                                        if (playAgain == JOptionPane.YES_OPTION) {
                                            clearBoard(board);
                                            currentPlayer = Player.X;
                                        }else {
                                            currentPlayer = Player.X;
                                            System.exit(0);
                                        }



                                    }
                                    if (isTie(board)){
                                        playAgain = JOptionPane.showOptionDialog(
                                                null,                          // parent component
                                                "Its a Tie!! Do you want to play again?",     // message
                                                "Choose an option",             // title
                                                JOptionPane.YES_NO_OPTION,      // option type
                                                JOptionPane.QUESTION_MESSAGE,   // message type
                                                null,                           // icon
                                                new String[]{"Yes", "No"}, // custom buttons
                                                "Button 1"                      // default button
                                        );
                                        if (playAgain == JOptionPane.YES_OPTION) {
                                            clearBoard(board);
                                            currentPlayer = Player.X;
                                        }else {
                                            currentPlayer = Player.X;
                                            System.exit(0);
                                        }

                                    }





                    currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;


                });

                tileButtonPanel.add(board[row][col]);


            }


        }





        add(tileButtonPanel, BorderLayout.CENTER);
    }
    private static boolean isWin(String player)
    {
        if(isColWin(player,board) || isRowWin(player,board) || isDiagnalWin(player,board))
        {
            return true;
        }

        return false;
    }
    private static boolean isColWin(String player, TicTacToeTile[][] board)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private static boolean isRowWin(String player,TicTacToeTile[][] board)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private static boolean isDiagnalWin(String player,TicTacToeTile[][] board)
    {
        // checks for a diagonal win for the specified player

        if(board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player) )
        {
            return true;
        }
        if(board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player) )
        {
            return true;
        }
        return false;
    }

    // checks for a tie before board is filled.
    // check for the win first to be efficient
    private static boolean isTie(TicTacToeTile[][] board)
    {

        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }
    private static void clearBoard(TicTacToeTile[][] board)
    {
        // sets all the board elements to a space
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col].setText("");
                board[row][col].setBackground(Color.WHITE);


            }
        }
    }
    public void bottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // adds spacing around components
        bottomPanel.setBackground(Color.white);

        JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(120, 50));
        quitButton.setMargin(new Insets(10, 10, 10, 10));

        quitButton.addActionListener(_ -> {
            System.exit(0);
        });

        bottomPanel.add(quitButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}








