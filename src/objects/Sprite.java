package objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * Created by austin on 1/21/15.
 *
 * All objects that are rendered extend Sprite
 * EnvironmentSprite, Background, and Bird extend Sprite directly
 * Ground and Pipes extend EnvironmentSprite
 *
 * Note that, in java, the origin for the coordinates (x,y) is in the top left corner.
 * i.e. The positive x axis points rightward, and the positive y axis points downward.
 */


public class Sprite {

    public Image image;
    public int x, y, width, height;



    // Sets a Sprite's image instance to an Image from a file whose name is given in the string parameter
    public void readImage(String fileName) {
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    // Draws a Sprite's image instance to a Graphics2D object
    public void render(Graphics2D g) {
        g.drawImage(image, x, y, null);
    }
}
