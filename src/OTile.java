import javax.swing.*;
import java.awt.*;

public class OTile implements Tile {
    private JButton button;

    public OTile(JButton button) {
        this.button = button;
    }

    @Override
    public void render() {
        button.setText("O");
        button.setBackground(Color.BLUE);
        button.setFont(new Font("Arial", Font.BOLD, 70));
    }
}