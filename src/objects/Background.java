package objects;

/**
 * Created by austin on 1/21/15.
 */
public class Background extends Sprite {

    public Background(String fileName) {
        readImage(fileName);
        x = 0;
        y = 0;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }


    public void update(int dt) {
        // objects.Background doesn't do anything on update
    }


    public void flap() {
        // objects.Background doesn't flap
    }
}