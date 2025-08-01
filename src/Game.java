import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Game implements Runnable {
    JFrame frame;
    GamePanel gamePanel;
    double delta;
    double lastTime;


    public Game() {
        frame = new JFrame();
        gamePanel = new GamePanel();
        //gamePanel.setBackground(Color.BLUE);

        frame.add(gamePanel);
        frame.setVisible(true);
        frame.setSize(1000, 800);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
