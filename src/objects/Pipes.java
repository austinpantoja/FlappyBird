package objects;

import java.util.Random;

/**
 * Created by austin on 1/21/15.
 */
public class Pipes extends Sprite {

    // velocity gives the amount of pixels shifted every time update is called
    private int velocity;
    private Random random;

    //xpos is either 0 or 1 to indicate whether it is the first or second instance of objects.Pipes
    public Pipes(String fileName, int xpos) {
        readImage(fileName);
        width = image.getWidth(null);
        height = image.getHeight(null);
        random = new Random(System.currentTimeMillis());
        setRandomY();
        // The pipe starts either just off the screen or a distance of half of screen + half of the pipe's width further
        x = (xpos == 0) ? 450 : 3*450/2 + (width/2);
        velocity = -2;

    }

    public void update(int dt) {
        x += velocity;
        // When the pipe exits the screen to the left, reset it just to right of the screen with a new height
        if (x <= -1*width) {
            x = 450;
            setRandomY();
        }
    }

    public void flap() {} //objects.Pipes don't flap

    // The pipe image is 1050 pixels tall.  2 pipes at 450 px and a gap of 150 px
    // This sets the y instance so that the pipes have a random height each time
    // The gap between the pipes will be a minimum of 30 px away from the ground and top
    private void setRandomY() {
        y = -420 + random.nextInt(400);
    }
}
