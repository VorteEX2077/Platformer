import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class ImageLoader {


    public ImageLoader(){

    }

    public HashMap<String, BufferedImage> loader(String directory) {
        URL url = getClass().getResource(directory);
        try {
            assert url != null;
            File file = new File(url.toURI()); // rocks file obj
            File[] listFiles = file.listFiles();
            assert listFiles != null;

            HashMap<String, BufferedImage> imageHashMap = new HashMap<>();
            int a = 0;
            for (File i : listFiles) {
                imageHashMap.put(i.getPath(), ImageIO.read(i));
                a += 1;
            }

//            for(String i: imageHashMap.keySet()){
//                System.out.println(i + " --> " + imageHashMap.get(i));
//            }
            return null;
        } catch (Exception e) {
            // Failing...
        }

        return null;
    }
}
