 

import greenfoot.*;
import java.util.Random;

public class binaryBullet extends Bullet {
    private int xDirection;
    private int yDirection;
    private GifImage gif0 = new GifImage("bit0.gif");
    private GifImage gif1 = new GifImage("bit1.gif");
    private Random rand = new Random();

    public binaryBullet(int xDirection, int yDirection) {
        super(5, 25);
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        if (rand.nextInt(2) == 0) {
            setImage(gif0.getCurrentImage());
        } else {
            setImage(gif1.getCurrentImage());
        }
        
    }

    @Override
    public void act() {
        if (rand.nextInt(5) == 0) {
            if (rand.nextInt(2) == 0) {
                setImage(gif0.getCurrentImage());
            } else {
                setImage(gif1.getCurrentImage());
            }
        }
        // Move the bullet in the specified direction
        setLocation(getX() + xDirection * speed, getY() + yDirection * speed);
        checkBoundaries();
        checkCollision();
    }
    
}
