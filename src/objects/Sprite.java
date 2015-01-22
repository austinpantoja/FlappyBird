package objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * Created by austin on 1/21/15.
 */
public abstract class Sprite {

    // The image that will actually render when game.FlappyBird calls render(Graphics2D g)
    // If you would like to animate a particular on screen object you could hold an ArrayList<Image> that image could
    //      be set to in the update call
    public Image image;
    // Give the position x, y that the object will be rendered on the screen
    // Keep in mind the origin is the top left corner in java with the positive y going downward
    public int x, y, width, height;


    // Has to be implemented in each class extending objects.Sprite
    public abstract void update(int dt);
    public abstract void flap();


    public void readImage(String fileName) {
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
