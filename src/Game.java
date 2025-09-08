import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Game implements Runnable {
    JFrame frame;
    GamePanel gamePanel;

    public Game() {
        frame = new JFrame();
        gamePanel = new GamePanel();
        //gamePanel.setBackground(Color.BLUE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate top-left corner so the frame's center is at the screen's center
        int x = (screenSize.width - 1000) / 2;
        int y = (screenSize.height - 800) / 2;

        frame.setLocation(x, y); // Manually center the full window

        frame.add(gamePanel);
        frame.setVisible(true);
        //frame.setResizable(false);
        frame.setSize(1000, 800);
        start();
    }

    public static void main(String[] a) {
        new Game();
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;

        while (true) {
            long currentTime = System.nanoTime();
            int TARGET_FPS = 100;
            double TIME_PER_FRAME = 1000000000.0 / TARGET_FPS;
            delta += (currentTime - lastTime) / TIME_PER_FRAME;
            lastTime = currentTime;

            while (delta >= 1) {
                gamePanel.repaint();
                delta--;
            }

            // Optional: Sleep a bit to reduce CPU usage
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
