import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class GamePanel extends JPanel implements KeyListener {

    int playerWidth = 50;
    int playerHeight = 100;
    int playerX = 370;
    int playerY = 400;
    int maxJumpHeight = 22 - playerY;
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
                isJumping = true;
            }
            // TODO: move the background
        }
    }

    private void jump() {
        if (!isJumping) return;

        if (playerY >= maxJumpHeight){
            velocity = -22;
            isJumping = false;
        }
        playerY += velocity; // going up
        velocity += 1;
    }

    private void collisionCheck(){
        Image[] allImagesArray = dataLoader.getImages();

        for(Image currImages : allImagesArray){
            if(playerY + playerHeight >= currImages.y && playerX >= currImages.x && playerX <= currImages.x +
                    currImages.getImage().getWidth() && currImages.type.equals("platform")){
                System.out.println("collided " + maxJumpHeight);
                //TODO stop player = currImages.y - playerHeight prevent from looping
                playerY = currImages.y - playerHeight;
                isJumping = false;
                return;
            }
            else{
                if(!isJumping) playerY +=1;
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
