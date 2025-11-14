import java.awt.image.BufferedImage;

/* Java POJO class */
public class Tile {
    public int x;
    public int y;
    public int width;
    public int height;
    public String type;
    public String imagePath;
    private transient BufferedImage image;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImagePath(){
        return imagePath;
    }

    public String getType(){
        return type;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
