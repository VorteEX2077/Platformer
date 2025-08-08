import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GamePanel extends JPanel implements KeyListener {

    int playerX = 300;
    int playerY = 650;
    int a = 0;
    DataLoader dataLoader;
    boolean isJumping;
    boolean isFalling;
    int velocity = -20;
    HashSet<Integer> keyEvent = new HashSet<>();

    GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        requestFocus();
        dataLoader = new DataLoader();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        /* This method is called 100 times every sec  */

        for (Image currImage : dataLoader.getImages()) {
            g.drawImage(currImage.getImage(), currImage.getX(), currImage.getY(), null);
        }

        jump();

        //player
        g.setColor(Color.MAGENTA);
        g.fillRect(playerX, playerY, 50, 100);

        if (keyEvent.contains(KeyEvent.VK_D)) {
            //playerX += 1;
            for (Image currImage : dataLoader.getImages()) {
                currImage.x -= 1;
            }
        }
        if (keyEvent.contains(KeyEvent.VK_A)) {
            for (Image currImage : dataLoader.getImages()) {
                currImage.x += 1;
            }
        }
        if (keyEvent.contains(KeyEvent.VK_W) || keyEvent.contains(KeyEvent.VK_SPACE)) {
            if (!isJumping) {
                a = 0;
                isJumping = true;
            }
            // TODO: move the background
        }
    }

    private void jump() {
        if (!isJumping) return;


        System.out.println(playerY);
        //starts at 700
        //going up to 500
        //falling at 498 adds 2
        /* inside game loop running all the time */
        if (playerY >= 650){
            velocity = -20;
            isJumping = false;
        }
        playerY += velocity; // going up
        velocity += 1;

//        if(isFalling && playerY < 650){
//            playerY += velocity; // going down
//        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyEvent.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyEvent.remove(e.getKeyCode());
    }
}
