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
        images.add(new Bird("img/bird.png"));
        images.add(new Pipes("img/pipes.png", 0));
        images.add(new Pipes("img/pipes.png", 1));
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

    protected void flap() {
        images.get(1).flap(); // 1 is the index of the bird
    }

}

