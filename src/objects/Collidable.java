package objects;


/**
 * Created by austin on 1/23/15.
 * Ground and Pipes extend EnvironmentSprite
 *
 * Velocity gives the amount of pixels shifted every time update is called
 * All Environment sprites only move in the negative x direction
 */


public abstract class Collidable extends Sprite {

    protected static final int velocity = -2;

    public abstract void update();
    public abstract void birdCollided(Bird bird);
    public abstract void reset(int index);
}
