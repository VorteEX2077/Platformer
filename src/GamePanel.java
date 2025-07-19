import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements MouseMotionListener {

    int x,y;
    GameMap gameMap;
    GamePanel() {
        gameMap = new GameMap();
        addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(Image currImage: gameMap.getListOfImageObjects()){
            g.drawImage(currImage.getImage(), currImage.getX(), currImage.getY(),null);
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}
