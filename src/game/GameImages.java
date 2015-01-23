package game;

import objects.*;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by austin on 1/21/15.
 *
 * To increase learning speed multiples instances of Bird could be used.  This could be done with only minor changes
 * by replacing Bird bird with ArrayList<Bird> birds, and updating the methods to iterate across the list.
 *
 */

public class GameImages {


    // Holds all of the non-bird games objects so that they can be easily iterated through to update, render, and detect collisions
    protected ArrayList<Collidable> collidables;
    protected Bird bird;
    protected Background background;


    // All of the file reading from the img folder is called from this constructor
    public GameImages() {
        //The image files themselves are loaded from the Sprite class
        collidables = new ArrayList<Collidable>();
        collidables.add(new Pipes("img/pipes.png"));
        collidables.add(new Pipes(collidables.get(collidables.size()-1).getImage()));
        collidables.add(new Ground("img/ground.png"));
        collidables.add(new Ground(collidables.get(collidables.size()-1).getImage()));
        bird = new Bird("img/bird.png");
        background = new Background("img/background.png");
    }


    // Updates all Sprite instances by looping through the images array
    protected void update() {
        for (Collidable collidable : collidables)
            collidable.update();
        bird.update();
    }


    // Renders all games images
    protected void render(Graphics2D g) {
        background.render(g);
        for (Collidable collidable : collidables) collidable.render(g);
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
        for (Collidable collidable : collidables)
            collidable.birdCollided(bird);
        return bird.alive;
    }


    // Resets all relevant objects after the bird dies
    protected void reset() {
        for (int i = 0; i < collidables.size(); i++) collidables.get(i).reset(i%2);
        bird.reset();
    }
}

