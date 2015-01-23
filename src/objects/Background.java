package objects;

/**
 * Created by austin on 1/21/15.
 */

public class Background extends Sprite {

    public Background(String fileName) {
        readImage(fileName);
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = 0;
        y = 0;
    }

}
