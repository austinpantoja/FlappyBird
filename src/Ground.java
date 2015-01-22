/**
 * Created by austin on 1/21/15.
 */
public class Ground extends Sprite {

    int velocity;

    // xpos = 0 or 1
    // This signifies whether it is the 1st or 2nd instance of ground
    // 2 instances of ground will scroll across the screen,
    //   once an instance has passed it will be placed just past the other

    public Ground(String fileName, int xpos) {
        readImage(fileName);
        width = image.getWidth(null);
        height = image.getHeight(null);
        velocity = -2;
        x = (xpos == 0) ? 0 : width;
        y = 600;
    }


    @Override
    protected void update(int dt) {
        x += velocity;
        if (x < -1*width) x += 2*width;
    }

    @Override
    protected void flap() {} //Ground doesn't flap
}
