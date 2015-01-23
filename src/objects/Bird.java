package objects;

/**
 * Created by austin on 1/21/15.
 *
 * Gravity = how much velocity will increase every update cycle
 * Velocity = how many pixels the y instance will change every update cycle
 * flapVelocity = how much how fast the bird moves upward when if "flaps"
 *
 * Score is Bird instance so that each Bird instance can have it's own score if multiple instances are run at once.
 * This also allows easy scoring in the Pipes birdCollided(Bird bird) method, since the score is to be increased
 *      whenever the bird "collides" with the empty space between 2 pipes in a Pipes instance.
 *
 * Gravity and flapVelocity are static so that if there are multiple instances of bird they will not need to be stored
 *      on a per-instance basis.
 *
 */


public class Bird extends Sprite {

    // These can be changed to modify the feel/difficulty of the game
    private static final double gravity = 0.6, flapVelocity = -16*gravity;

    private double velocity;
    public int score;
    public boolean alive;


    public Bird(String fileName) {
        readImage(fileName);
        height = image.getHeight(null);
        width = image.getWidth(null);
        velocity = 0;
        x = 160;
        y = 10;
        score = 0;
        alive = true;
    }


    public void update() {
        velocity += gravity;
        y += (int)Math.round(velocity);
        if (y < 0) {
            y = 0;
            velocity = 0;
        }
    }


    // If bird is falling, on flap it's velocity changes to being upward at flapVelocity
    // If the bird is already going up, it's velocity magnitude increases by flapVelocity
    public void flap() {
        velocity = (velocity >= 0) ? flapVelocity : velocity+flapVelocity;
    }


    // Resets the bird after it dies
    public void reset() {
        score = 0;
        alive = true;
        y = 10;
        velocity = 0;
    }
}
