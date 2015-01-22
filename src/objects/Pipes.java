package objects;

import java.util.Random;

/**
 * Created by austin on 1/21/15.
 */
public class Pipes extends Sprite {

    private int velocity;
    private Random random;

    //xpos is either 0 or 1 to indicate whether it is the first or second instance of objects.Pipes
    public Pipes(String fileName, int xpos) {
        readImage(fileName);
        width = image.getWidth(null);
        height = image.getHeight(null);
        random = new Random(System.currentTimeMillis());
        setRandomY();
        x = (xpos == 0) ? 450 : 3*450/2 + (width/2);
        velocity = -2;

    }

    public void update(int dt) {
        x += velocity;
        if (x <= -1*width) {
            x = 450;
            setRandomY();
        }
    }

    public void flap() {} //objects.Pipes don't flap

    private void setRandomY() {
        y = -430 + random.nextInt(410);
    }
}
