import javax.swing.*;
import java.awt.*;

public class Game {
    JFrame frame;
    GamePanel gamePanel;

    public Game() {
        frame = new JFrame();
        gamePanel = new GamePanel();
        //gamePanel.setBackground(Color.BLUE);

        frame.add(gamePanel);
        frame.setVisible(true);
        frame.setSize(1000, 800);
    }

    public static void main(String[] a) {
        new Game();
    }
}
