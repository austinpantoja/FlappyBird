import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

/**
 * Created by austin on 1/21/15.
 */

public class FlappyBird implements Runnable {

    private Canvas canvas;
    private BufferStrategy bs;
    private JFrame frame;
    private boolean running;
    private GameImages gameImages;

    private static final int HEIGHT = 720, WIDTH = HEIGHT*10/16; //Gives the screen an aspect ratio of 10x16
    private static final int UPS = 60; // Sets the amout of times the game updates per second



    public FlappyBird() {
        running = true;
        gameImages = new GameImages();
        frame = new JFrame("Crappy Bird"); //I'll admit I stole this name from someone else on the internet

        JPanel frameContents = (JPanel) frame.getContentPane();
        frameContents.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frameContents.setLayout(null); //We don't need a layout, just set bounds

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        frameContents.add(canvas);

        // The mouse adapter and key adapter should be there own class in the real thing
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameImages.flap();
            }
        });
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    gameImages.flap();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Places JFrame in center of screen
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();

        canvas.requestFocus();
    }



    // Contains the game running loop that continuously calls render and update when appropriate
    public void run() {
        long currentTime, lastTime = System.currentTimeMillis();

        while (running) {
            // Is set so that it will render as many FPS as possible
            render();
            currentTime = System.currentTimeMillis();

            // UPS = updates per second.  1000/UPS = the amount of time in milliseconds between updates
            if (currentTime - lastTime >= 1000/UPS) {
                //casting to int because this all objects in the game will take this parameter and
                //  using an int is easier and faster.  It should also be only ~(1000/60)
                update((int)(currentTime-lastTime));
                lastTime = currentTime;
            }
        }
    }



    public void render() {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        gameImages.render(g);
        g.dispose();
        bs.show();
    }



    //Takes in a parameter dt giving how long since the last update in milliseconds
    public void update(int dt) {
        gameImages.update(dt);
    }



    // All main does is start a thread that runs an instance of FlappyBird
    public static void main(String[] args) {
        Thread thread = new Thread(new FlappyBird());
        thread.start();
    }
}
