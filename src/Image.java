import java.awt.image.BufferedImage;

/* Java POJO class */
public class Image {
    public int x;
    public int y;
    public String type;
    public String imagePath;
    private transient BufferedImage image;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
