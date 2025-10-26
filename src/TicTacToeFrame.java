import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    Game game = new Game();
    TTTBoard tttboard = new TTTBoard();

    public TicTacToeFrame() {
        setTitle("TicTacToe");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel();
        bottomPanel();

        setVisible(true);
    }

    public void gamePanel() {
        JPanel tileButtonPanel = new JPanel();
        tileButtonPanel.setLayout(new GridLayout(3, 3));
        tileButtonPanel.setBackground(Color.white);

        // adds button for every tile value in array
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {



                JButton cellButton = new JButton();
                TTTBoard.Cell cell = tttboard.getValue(row, col);

                // create the tile using the factory
                Tile tile = TileFactory.createTile(cell, cellButton);
                tile.render();
                tileButtonPanel.add(cellButton);

                int finalRow = row;
                int finalCol = col;

                cellButton.addActionListener(_ -> {
                    Game.Player currentPlayer = game.getCurrentPlayer();
                    // get the current value from board each click
                    TTTBoard.Cell currentCell = tttboard.getValue(finalRow, finalCol);
                    TTTBoard.Cell currentPlayerValue = TTTBoard.Cell.valueOf(currentPlayer.name());

                    if (currentCell != TTTBoard.Cell.EMPTY) {
                        JOptionPane.showMessageDialog(cellButton, "Cell already taken!");
                        return;
                    }

                    // set the move for the current player
                    tttboard.setValue(finalRow, finalCol, currentPlayerValue);


                    // re-render tile
                    Tile newTile = TileFactory.createTile(currentPlayerValue, cellButton);
                    newTile.render();

                    // check for win
                    if (game.isWin(currentPlayer)) {
                        int playAgain = JOptionPane.showOptionDialog(
                                null,
                                "Player " + currentPlayer + " won! Play again?",
                                "Game Over",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new String[]{"Yes", "No"},
                                "Yes"
                        );

                        if (playAgain == JOptionPane.YES_OPTION) {
                            tttboard.clearBoard();
                            new TicTacToeFrame(); // restart game


                        } else {
                            System.exit(0);
                        }
                        return;
                    }



                // check for tie
                if (game.isTie()) {
                    int playAgain = JOptionPane.showOptionDialog(
                            null,
                            "It's a tie! Do you want to play again?",
                            "Game Over",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new String[]{"Yes", "No"},
                            "Yes"
                    );

                    if (playAgain == JOptionPane.YES_OPTION) {
                        tttboard.clearBoard();
                        new TicTacToeFrame(); // restart game


                    } else {
                        System.exit(0);
                    }
                    return;
                }

                game.changePlayer();

                });



            }



        }
        // add panel and refresh frame
        this.add(tileButtonPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();

    }



    public void bottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // adds spacing around components
        bottomPanel.setBackground(Color.white);

        JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(120, 50));
        quitButton.setMargin(new Insets(10, 10, 10, 10));
        quitButton.setBackground(Color.white);
        quitButton.setFont(new Font("Arial", Font.BOLD, 20));

        quitButton.addActionListener(_ -> {
            System.exit(0);
        });

        bottomPanel.add(quitButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}














