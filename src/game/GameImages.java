package game;

import objects.*;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by austin on 1/21/15.
 */

public class GameImages {


    // Holds all of the games objects so that they can be easily iterated through to update and render
    protected ArrayList<Sprite> images;

    public GameImages() {
        //Load all game images here
        //The image files themselves are loaded from the objects.Sprite
        images = new ArrayList<Sprite>();
        images.add(new Background("img/background.png"));
        images.add(new Pipes("img/pipes.png", 0));
        images.add(new Pipes("img/pipes.png", 1));
        images.add(new Bird("img/bird.png")); // The Bird's index is 3
        images.add(new Ground("img/ground.png", 0));
        images.add(new Ground("img/ground.png", 1));
    }


    // Updates all objects.Sprite instances by looping through the images array
    protected void update(int dt) {
        for (Sprite sprite : images) {
            sprite.update(dt);
        }
    }


    // Renders all games images
    protected void render(Graphics2D g) {
        for (Sprite sprite : images)
            g.drawImage(sprite.image, sprite.x, sprite.y, null);
    }


    // Method is called from keyAdapter and mouseAdapter event handler's
    // Calls the Bird's flap method
    protected void flap() {
        images.get(3).flap(); // 3 is the index of the Bird Instance
    }



    // images.get(1) and images.get(2) are the 2 instances of Pipes
    // images.get(3) is the Bird instance
    // 2 is the number of pixels moved every update cycle "velocity"
    // This checks if the bird moved fully in between 2 pipes in the last update cycle
    protected boolean scoreIncreased() {
        return (images.get(1).x >= images.get(3).x && images.get(1).x-2 < images.get(3).x) ||
                (images.get(2).x >= images.get(3).x && images.get(2).x-2 < images.get(3).x);
    }


    protected boolean deathCheck() {
        if (images.get(3).y + images.get(3).height > images.get(4).y) return true;
        return false;
    }

}

