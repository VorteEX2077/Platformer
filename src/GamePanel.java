import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class GamePanel extends JPanel implements KeyListener {

    int playerWidth = 50;
    int playerHeight = 100;
    int playerX = 300;
    int playerY = 400;
    int maxJumpHeight = 400;
    int a = 0;
    DataLoader dataLoader;
    boolean isJumping;
    int velocity = -22;
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
            BufferedImage image = currImage.getImage();
            g.setColor(Color.GREEN);
            g.drawImage(image, currImage.getX(), currImage.getY(), null);
            g.drawRect(currImage.x, currImage.y, image.getWidth(), image.getHeight());
        }

        collisionCheck();

        jump();

        //player
        g.setColor(Color.MAGENTA);
        g.fillRect(playerX, playerY, playerWidth, playerHeight);

        if (keyEvent.contains(KeyEvent.VK_D)) {
            if(isJumping) playerX += 2;
            //playerX += 1;
                /* moving the background */
            for (Image currImage : dataLoader.getImages()) {
                currImage.x -= 2;
            }
        }
        if (keyEvent.contains(KeyEvent.VK_A)) {
            if(isJumping) playerX -= 2;

            for (Image currImage : dataLoader.getImages()) {
                currImage.x += 2;
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

       // System.out.println(playerY);
        //starts at 700
        //going up to 500
        //falling at 498 adds 2
        /* inside game loop running all the time */
        //System.out.println(maxJumpHeight + "," + playerY);
        if (playerY >= maxJumpHeight){
            velocity = -22;
            isJumping = false;
        }
        playerY += velocity; // going up
        velocity += 1;

//        if(isFalling && playerY < 650){
//            playerY += velocity; // going down
//        }
    }

    private void collisionCheck(){
        Image[] allImagesArray = dataLoader.getImages();

        for(Image currImages : allImagesArray){
            if(playerX >= currImages.x && playerX <= currImages.x + currImages.getImage().getWidth()){
                maxJumpHeight = currImages.y;
                isJumping = false;
                System.out.println("collided " + maxJumpHeight);
                return;
            }
        }
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
