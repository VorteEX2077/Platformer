import java.awt.image.BufferedImage;

public class Image {
    public int x;
    public int y;
    public String location;
    public String category;
    private transient BufferedImage image;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public String getLocation(){
        return location;
    }
    public String getCategory(){
        return category;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
