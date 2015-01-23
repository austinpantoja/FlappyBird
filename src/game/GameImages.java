package game;

import objects.*;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by austin on 1/21/15.
 *
 * To increase learning speed multiples instances of Bird could be used.  This could be done with only minor changes
 * by replacing Bird bird with ArrayList<Bird> birds, and updating the methods to iterate across the list.
 */

public class GameImages {


    // Holds all of the non-bird games objects so that they can be easily iterated through to update, render, and detect collisions
    protected ArrayList<EnvironmentSprite> images;
    protected Bird bird;
    protected Background background;


    // All of the file reading from the img folder is called from this constructor
    public GameImages() {
        //The image files themselves are loaded from the Sprite class
        images = new ArrayList<EnvironmentSprite>();
        images.add(new Pipes("img/pipes.png"));
        images.add(new Pipes(images.get(images.size()-1).image));
        images.add(new Ground("img/ground.png"));
        images.add(new Ground(images.get(images.size()-1).image));
        bird = new Bird("img/bird.png");
        background = new Background("img/background.png");
    }


    // Updates all Sprite instances by looping through the images array
    protected void update() {
        for (EnvironmentSprite sprite : images)
            sprite.update();
        bird.update();
    }


    // Renders all games images
    protected void render(Graphics2D g) {
        background.render(g);
        for (Sprite sprite : images) sprite.render(g);
        bird.render(g);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 32)); //Comic sans because I enjoy watching the world burn
        g.drawString(Integer.toString(bird.score), 215, 685);
    }


    // Method is called from keyAdapter and mouseAdapter event handlers
    // Calls the Bird's flap method
    protected void flap() {
        bird.flap();
    }



    // Checks to see if the bird is still alive
    protected boolean stillAlive() {
        for (EnvironmentSprite sprite : images)
            sprite.birdCollided(bird);
        return bird.alive;
    }


    // Resets all relevant objects after the bird dies
    protected void reset() {
        for (int i = 0; i < images.size(); i++) images.get(i).reset(i%2);
        bird.reset();
    }
}

