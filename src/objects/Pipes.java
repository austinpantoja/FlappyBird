package objects;

import java.awt.*;
import java.util.Random;

/**
 * Created by austin on 1/21/15.
 */

public class Pipes extends Collidable {

    // Used to assign random y values for pipes
    private static final Random random = new Random(System.currentTimeMillis());


    // Constructor is used to create the first instance of pipes in game images
    // The x value will be set to the screen width
    public Pipes(String fileName) {
        readImage(fileName);
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = 450;
        setRandomY();
    }


    // Constructor is used for the 2nd instance of Pipes in GameImages
    // Prevents the image source file from being read twice
    // Also this sets the 2nd Pipes' x value (screen width)/2 + (pipe img width)/2 further than the 1st Pipes' x value
    public Pipes(Image img) {
        image = img;
        width = image.getWidth(null);
        height = image.getHeight(null);
        setRandomY();
        x = 3*450/2 + width/2;
    }



    // The ground moves from the right to the left side of the screen at -(velocity) pixels per update.
    // Once the instance of Pipes has exited the screen, it is placed to the right of the screen and given a new height
    public void update() {
        x += velocity;
        if (x <= -1*width) {
            x = 450;
            setRandomY();
        }
    }


    // The height of the pipes image is 1050 px.  Comprised of two 450 px pipe sections with a 150 px gap between them
    // If bird is passing between the pipes for the first time, it's score is increased
    public void birdCollided(Bird bird) {
        if (bird.x + bird.width >= x && bird.x <= x + width) {
            if (bird.y <= y + 450 || bird.y + bird.height >= y + 600)
                bird.alive = false;
            else if (bird.x + bird.width >= x && bird.x + bird.width < x - velocity)
                bird.score++;
        }
    }


    public Image getImage() {
        return image;
    }

    // Resets the instances of GameImages after the bird has died
    // index 0 means that it is the 1st instance of Ground in GameImages
    // index 1 means that it is the 2nd instance of Ground in GameImages
    public void reset(int index) {
        setRandomY();
        x = (index == 0) ? 450 : 3*450/2 + width/2;

    }


    // The pipe image is 1050 pixels tall.  2 pipes at 450 px and a gap of 150 px
    // This sets the y instance so that the pipes have a random height each time
    // The gap between the pipes will be a minimum of 30 px away from the ground and top
    private void setRandomY() {
        y = -420 + random.nextInt(400);
    }
}
