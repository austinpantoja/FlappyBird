package game;

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
    private int score;

    private static final int HEIGHT = 720, WIDTH = HEIGHT*10/16; //Gives the screen an aspect ratio of 10x16
    private static final int UPS = 60; // Sets the amout of times the game updates per second



    public FlappyBird() {
        score = 0;
        running = true;
        gameImages = new GameImages();
        frame = new JFrame("Crappy Bird"); //I'll admit I stole this name from someone else on the internet

        JPanel frameContents = (JPanel) frame.getContentPane();
        frameContents.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frameContents.setLayout(null); //Don't need a layout, just set bounds

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        frameContents.add(canvas);

        // The mouse adapter and key adapter should be there own class in the real thing
        // Click or press space for the bird to "flap"
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
        frame.setLocationRelativeTo(null); // Places JFrame in the center of screen
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();

        canvas.requestFocus();
    }



    // Contains the game running loop that continuously calls render and update when appropriate
    public void run() {
        long currentTime, lastTime = System.currentTimeMillis();

        while (running) {
            currentTime = System.currentTimeMillis();

            // UPS = updates per second.  1000/UPS = the amount of time in milliseconds between updates
            if (currentTime - lastTime >= 1000/UPS) {
                //casting to int because this all objects in the game will take this parameter and
                //  using an int is easier and faster.  It should also be only ~(1000/60)
                update((int)(currentTime-lastTime));
                lastTime = currentTime;
            }

            // Is set so that it will render as many FPS as possible
            render();

            // I added this in so that the game wasn't so CPU intensive
            // It sleeps the thread for 3/5 of time required for an update cycle
            if (currentTime - lastTime < 1000/UPS/5) {
                try {
                    Thread.sleep(3000/UPS/5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    // The images themselves are drawn on in the GameImages class
    public void render() {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT); //Clears the previous frame
        gameImages.render(g); //Draws all of the images in gameImages instance ArrayList<Sprite>
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 32)); //Comic sans because I enjoy watching the world burn
        g.drawString(Integer.toString(score), 215, 685);
        g.dispose();
        bs.show();
    }



    //Takes in a parameter dt giving how long since the last update in milliseconds
    public void update(int dt) {
        if (gameImages.deathCheck()) reset();
        gameImages.update(dt);
        if (gameImages.scoreIncreased()) score++;
    }



    public void reset() {
        // This is a really crappy implementation as it reloads all of the images from their source files.
        // ToDO create abstrat reset method in Sprite, then call it on all objects in gameImages to reset
        gameImages = new GameImages();
        score = 0;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // All main does is start a thread that runs an instance of FlappyBird
    public static void main(String[] args) {
        Thread thread = new Thread(new FlappyBird());
        thread.start();
    }
}
