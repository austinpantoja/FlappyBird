package objects;

import java.awt.*;

/**
 * Created by austin on 1/21/15.
 */

public class Ground extends EnvironmentSprite {


    // Used for the first Ground instance in GameImages
    // x value is set for the left side of the screen
    public Ground(String fileName) {
        readImage(fileName);
        width = image.getWidth(null);
        height = image.getHeight(null);
        velocity = -2;
        x = 0;
        y = 600;
    }


    // Used for the second Ground instance in GameImages
    // Will be initially placed off screen just to the right of the first instance
    // Using this constructor also allows GameImages for not need to read the same file twice
    public Ground(Image img) {
        image = img;
        width = image.getWidth(null);
        height = image.getHeight(null);
        velocity = -2;
        x = width;
        y = 600;
    }



    // The ground moves from the right to the left side of the screen at -(velocity) pixels per update.
    // Once the instance of Ground has exited the screen, it is placed to the right of the other instance
    public void update() {
        x += velocity;
        if (x < -1*width) x += 2*width;
    }


    // Detects if the bird has touched the ground
    // Also keeps the bird from falling into the ground
    public void birdCollided(Bird bird) {
        if (bird.y + bird.height >= y) {
            bird.y = y - bird.height;
            bird.alive = false;
        }
    }


    // Resets the instances of GameImages after the bird has died
    // index 0 means that it is the 1st instance of Ground in GameImages
    // index 1 means that it is the 2nd instance of Ground in GameImages
    public void reset(int index) {
        x = (index == 0) ? 0 : width;
    }

}
