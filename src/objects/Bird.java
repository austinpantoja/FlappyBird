package objects;

/**
 * Created by austin on 1/21/15.
 */

// Having the player object (in this case bird) extend sprite is a bad idea in a final version
public class Bird extends Sprite {

    //Gravity gives how much velocity will increase every update cycle
    //Velocity gives how many pixels the y instance will change every update cycle
    //flapVelocity gives how much how fast the bird moves upward when if "flaps"
    protected double gravity, velocity, flapVelocity;


    public Bird(String fileName) {
        readImage(fileName);
        height = image.getHeight(null);
        width = image.getWidth(null);
        velocity = 0;
        gravity = 0.6;
        flapVelocity = -16*gravity;
        x = 160;
        y = 10;
    }


    @Override
    public void update(int dt) {
        velocity += gravity;
        y += (int)velocity;
        if (y < 0) {
            y = 0;
            velocity = 0;
        }
        if (y + height > 601) y = 601-height; // So the bird doesn't fall into the ground
    }


    public void flap() {
        velocity = (velocity >= 0) ? flapVelocity : velocity+flapVelocity;
    }
}
