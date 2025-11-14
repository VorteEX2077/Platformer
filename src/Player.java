import java.awt.*;

public class Player extends Rectangle {

    Player(int x, int y,int width, int height){
        super(x, y, width, height);
    }

    public void draw(Graphics g){
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, width, height);
    }

    public void isPlayerDead() {

    }
}
