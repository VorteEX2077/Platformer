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
    int velocityY = -22;
    int GRAVITY = 1;
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
//        System.out.println()

        //player
        g.setColor(Color.MAGENTA);
        g.fillRect(playerX, playerY, playerWidth, playerHeight);

        if (keyEvent.contains(KeyEvent.VK_D)) {
            if (isJumping) playerX += 2;
            //playerX += 1;
            /* moving the background */
            for (Image currImage : dataLoader.getImages()) {
                currImage.x -= 2;
            }
        }
        if (keyEvent.contains(KeyEvent.VK_A)) {
            if (isJumping) playerX -= 2;

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

    public void update() {
        /* This method is called 100 times every sec  */
        velocityY += GRAVITY;
        playerY += velocityY;

        if(!isPlayerOnPlatform() == null){
            playerY = isPlayerOnPlatform() - rectH; // Sit on top
            velocityY = 0;
            onPlatform = true;
            isJumping = false;

        } else if (playerY + playerHeight >= 800) {
        }
        else {

        }

    }

    private Image isPlayerOnPlatform() {
        Image[] allImagesArray = dataLoader.getImages();

        for (Image img : allImagesArray) {
            if(playerY + playerHeight >= img.y && playerX >= img.x && playerX <= img.x +
                    img.getImage().getWidth() && img.type.equals("platform")) {
                return img;
            }
        }
        return null;
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
