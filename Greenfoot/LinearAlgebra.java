//Flag zombie
import greenfoot.*;

public class LinearAlgebra extends zombie
{
    private GifImage gif = new GifImage("linear_algebra.gif");
    private static final int ATTACK_INTERVAL = 1500;
    private static final int moveInterval = 2;
    private int moveCounter = 0;
    public LinearAlgebra()
    {
        setImage(gif.getCurrentImage());
        setHealth(100);
    }
    public void act() 
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAdded >= ATTACK_INTERVAL) {
            attackPlant(25);
            lastAdded = currentTime;
        }
        if (frozen) {
            isFrozen("frozen_linear_algebra.png", gif);
        } else {
            moveCounter++;
            if (moveCounter >= moveInterval) {
                setImage(gif.getCurrentImage());
                move(zombieSpeed);
                moveCounter = 0;
            }
        }
        checkGameOver();
        setSpeed(-1);
        zombieHit("linear_algebra_dying.gif", 1000, 10);       
    }
}
