import javax.swing.*;

public class TileFactory {

    public static Tile createTile(TTTBoard.Cell cell, JButton button) {
        return switch (cell) {
            case X -> new XTile(button);
            case O -> new OTile(button);
            case EMPTY -> new EmptyTile(button);
        };
    }
}



