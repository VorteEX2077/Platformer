import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class GamePanel extends JPanel implements KeyListener {

    private static final int JUMP_STRENGTH = 40;
    int playerWidth = 50;
    int playerHeight = 100;
    int playerX = 370;
    int playerY = 400;
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
        dataLoader = new DataLoader(); // when the new dataloader is ran it calls the constructor and loads
        // all the images
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
            if (onPlatform && !isJumping) {
                isJumping = true;
                onPlatform = false;
                velocityY = -JUMP_STRENGTH;  // <-- the missing upward impulse
            }
            // TODO: move the background
        }
    }

    public void update() {
        // Apply gravity if in the air
        if (!onPlatform) {
            velocityY += GRAVITY;
            playerY += velocityY;
        }

        Image imgObj = isPlayerOnPlatform();

        if (imgObj != null && velocityY >= 0) { // land only when falling or resting
            playerY = imgObj.y - playerHeight;  // <-- use playerHeight, not image height
            velocityY = 0;
            onPlatform = true;
            isJumping = false;
        }
        // Floor collision
        else if (playerY + playerHeight >= 800) {
            playerY = 800 - playerHeight;
            velocityY = 0;
            onPlatform = true;
            isJumping = false;
        } else {
            onPlatform = false;
        }
    }


    private Image isPlayerOnPlatform() {
       Image[] allImagesArray = dataLoader.getImages();
        // Player's bounding box
        int playerBottom = playerY + playerHeight;
        int playerLeft = playerX;
        int playerRight = playerX + playerWidth;

        for (Image img : allImagesArray) {
            if (!"platform".equals(img.type)) continue;

            int platLeft = img.x;
            int platRight = img.x + img.getImage().getWidth();
            int platTop = img.y;

            boolean horizontallyOverlapping = playerRight > platLeft && playerLeft < platRight;

            // Consider as "standing" if player's bottom is at or just above the platform top
            // and the player is moving downward (velocityY >= 0).
            boolean touchingTop =
                    playerBottom >= platTop && playerBottom <= platTop + Math.max(2, Math.abs(velocityY) + 2);

            if (horizontallyOverlapping && touchingTop) {
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
