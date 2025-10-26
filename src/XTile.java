import javax.swing.*;
import java.awt.*;

public class XTile implements Tile {
    private JButton button;

    public XTile(JButton button) {
        this.button = button;
    }

    @Override
    public void render() {
        button.setText("X");
        button.setBackground(Color.RED);
        button.setFont(new Font("Arial", Font.BOLD, 70));
    }
}