import java.util.ArrayList;

public class GameMap {
    private final ArrayList<Image> listOfImageObjects = new ArrayList<>();

    public GameMap(){
        DataLoader dataLoader = new DataLoader();
        ImageLoader imageLoader = new ImageLoader();
        Image[] dataArray = dataLoader.getImages();

//        for(int i = 0; dataArray.length > i; i++){
//            Image image = dataArray[i];
//            if(image.getCategory().equals("rocks")) {
//                image.setImage(imageLoader.getBigRocks()[image.getIdx()]);
//            }
//            else{
//                System.out.println(image.x + " " + image.y + " " + image.idx);
//                image.setImage(imageLoader.getPlatforms()[image.getIdx()]);
//            }
//            listOfImageObjects.add(image);
//        }
    }

    public ArrayList<Image> getListOfImageObjects() {
        return listOfImageObjects;
    }

}
