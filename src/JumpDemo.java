import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JumpDemo extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private int rectX = 200, rectY = 300; // Player position
    private int rectW = 40, rectH = 40;
    private int velocityY = 0;
    private int velocityX = 0;
    private boolean onGround = false;
    private boolean jumping = false;

    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -15;
    private final int MOVE_SPEED = 5;
    private final int FLOOR_Y = 400;
    private final Rectangle platform = new Rectangle(150, 300, 200, 20);

    public JumpDemo() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);
        timer = new Timer(15, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Apply gravity
       velocityY += GRAVITY;
       rectY += velocityY;
       //System.out.println(rectY + "," + velocityY);

        // Apply horizontal movement
        rectX += velocityX;

        // Stay in bounds
        if (rectX < 0) rectX = 0;
        if (rectX + rectW > getWidth()) rectX = getWidth() - rectW;

        // Check collision with platform
        Rectangle player = new Rectangle(rectX, rectY, rectW, rectH);
        if (player.intersects(platform) ) {
            rectY = platform.y - rectH; // Sit on top
            velocityY = 0;
            onGround = true;
            jumping = false;
        }
        // Check collision with floor
        else if (rectY + rectH >= FLOOR_Y) {
            rectY = FLOOR_Y - rectH;
            velocityY = 0;
            onGround = true;
            jumping = false;
        } else {
            onGround = false;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw platform
        g.setColor(Color.GRAY);
        g.fillRect(platform.x, platform.y, platform.width, platform.height);

        // Draw floor
        g.setColor(Color.BLACK);
        g.drawLine(0, FLOOR_Y, getWidth(), FLOOR_Y);

        // Draw rectangle player
        g.setColor(Color.RED);
        g.fillRect(rectX, rectY, rectW, rectH);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                System.out.println(onGround + ", " + jumping);
                if (onGround && !jumping) {
                    velocityY = JUMP_STRENGTH;
                    System.out.println(velocityY);
                    jumping = true;
                    onGround = false;
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                velocityX = -MOVE_SPEED;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                velocityX = MOVE_SPEED;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A ||
                e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            velocityX = 0;
        }
    }

    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jump + Move Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JumpDemo());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
