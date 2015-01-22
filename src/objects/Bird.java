package objects;

/**
 * Created by austin on 1/21/15.
 */

// Having the player object (in this case bird) extend sprite is a bad idea in a final version
public class Bird extends Sprite {

    protected double gravity, velocity, flapVelocity;
    protected boolean flapping;


    public Bird(String fileName) {
        readImage(fileName);
        height = image.getHeight(null);
        width = image.getWidth(null);
        gravity = 0.6;
        velocity = 0;
        flapVelocity = -15*gravity;
        x = 150;
        y = 10;
        flapping = false;
    }


    @Override
    public void update(int dt) {
        velocity += gravity;
        y += (int)velocity;
        if (y < 0) {
            y = 0;
            velocity = 0;
        }
    }


    public void flap() {
        velocity = (velocity >= 0) ? flapVelocity : velocity+flapVelocity;
    }
}
