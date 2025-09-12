import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class GamePanel extends JPanel implements KeyListener {

    int playerWidth = 50;
    int playerHeight = 100;
    int playerX = 370;
    int playerY = 400;
    int platformY;
    DataLoader dataLoader;
    boolean isJumping;
    int velocity = -22;
    boolean onPlatform;
    HashSet<Integer> keyEvent = new HashSet<>();
    ArrayList<Image> visibleImages = new ArrayList<>();

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

        visibleImages.clear();
        for (Image currImage : dataLoader.getImages()) {
            BufferedImage image = currImage.getImage();
            g.setColor(Color.GREEN);
            g.drawImage(image, currImage.getX(), currImage.getY(), null);
            g.drawRect(currImage.x, currImage.y, image.getWidth(), image.getHeight());

            if ((currImage.x >= 0 && currImage.x + currImage.getImage().getWidth() >= 0) ||
                    (currImage.x <= 1000 && currImage.x + currImage.getImage().getWidth() <= 1000))
                visibleImages.add(currImage);
        }

//        for (Image i : visibleImages) System.out.print(i.getImagePath() + ", ");
//        System.out.println();

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
        System.out.println(platformY);
        if (playerY + playerHeight >= platformY){
            velocity = -22;
            isJumping = false;
        }
        playerY += velocity; // going up
        velocity += 1;
    }

    private void collisionCheck(){
        Image[] allImagesArray = dataLoader.getImages();

        for(Image currImages : allImagesArray){
            if (isPlayerOnPlatform(currImages)) {
                //TODO stop player = currImages.y - playerHeight prevent from looping
                playerY = currImages.y - playerHeight;
                System.out.println("playerY:" + playerY);
                platformY = currImages.y;
                return;
            }
            else{
              //  if(!isJumping) playerY +=1;
            }
        }

    }

    private boolean isPlayerOnPlatform(Image img) {
        return playerY + playerHeight >= img.y && playerX >= img.x && playerX <= img.x +
                img.getImage().getWidth() && img.type.equals("platform");
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
