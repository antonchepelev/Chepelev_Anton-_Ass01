import javax.swing.*;
import java.awt.*;

public class EmptyTile implements Tile {
    private JButton button;

    public EmptyTile(JButton button) {
        this.button = button;
    }

    @Override
    public void render() {
        button.setText("");
        button.setBackground(Color.WHITE);
    }
}