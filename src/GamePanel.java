import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.util.HashMap;

public class GamePanel extends JPanel implements MouseMotionListener, KeyListener {

    int x,y;
    int playerX;
    DataLoader dataLoader;
    HashMap<Integer, String> keyEvent = new HashMap<>();

    GamePanel() {
        addMouseMotionListener(this);
        setFocusable(true);
        addKeyListener(this);
        requestFocus();
        dataLoader = new DataLoader();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(Image currImage:dataLoader.getImages()){
            g.drawImage(currImage.getImage(), currImage.getX(), currImage.getY(),null);
        }

        //player
        g.setColor(Color.MAGENTA);
        g.fillRect(playerX,60, 50, 100);
        if(keyEvent.containsKey(KeyEvent.VK_D)) {
            playerX += 1;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        if(!keyEvent.containsKey(e.getKeyCode())){
             keyEvent.put(e.getKeyCode(), "pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyEvent.remove(e.getKeyCode());
    }
}
