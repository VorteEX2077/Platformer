import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    private final Image[] images;

    DataLoader() {
        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        // STEP 1 : Read the file the map1.json file into a String variable
        try {
            URI levelsDir = getClass().getResource("/levels/map1.json").toURI();
            FileReader fileReader = new FileReader(new File(levelsDir));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String bufferTemp = bufferedReader.readLine(); // [
            while(true) {
                if(null == bufferTemp){ // is [ == null??
                    break;
                }
                stringBuilder.append(bufferTemp);
                bufferTemp = bufferedReader.readLine();
            }
            images = gson.fromJson(stringBuilder.toString(), Image[].class);

            /* loading the images in the map */
            for(Image i : images) {
                i.setImage(ImageIO.read(new File(getClass().getResource(i.imagePath).toURI())));
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Image[] getImages(){
        return images;
    }

}
