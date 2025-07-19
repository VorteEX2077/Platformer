import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class ImageLoader {
    private HashMap<String, BufferedImage> bigRocks;
    private HashMap<String, BufferedImage> platforms;

    public ImageLoader(){
        bigRocks = loader("/rocks/");
       platforms = loader("/platforms/");
    }

    public HashMap<String, BufferedImage> loader(String directory) {
        URL url = getClass().getResource(directory);
        try {
            assert url != null;
            File file = new File(url.toURI());
            File[] listFiles = file.listFiles();
            assert listFiles != null;

            HashMap<String, BufferedImage> imageHashMap = new HashMap<>();
            int a = 0;
            for (File i : listFiles) {
                imageHashMap.put(i.getPath(), ImageIO.read(i));
                a += 1;
            }

            for(String i: imageHashMap.keySet()){
                System.out.println(i + " --> " + imageHashMap.get(i));
            }
            return null;
        } catch (Exception e) {
            // Failing...
        }

        return null;
    }

    public HashMap<String, BufferedImage> getBigRocks() {
        return bigRocks;
    }
     public HashMap<String, BufferedImage> getPlatforms(){
        return  platforms;
     }

     public static void main(String[] args){
        new ImageLoader();
     }
}
