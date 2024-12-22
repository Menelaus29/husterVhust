//Football zombie
 
import greenfoot.*;

public class Calculus2 extends zombie
{
    private GifImage gif = new GifImage("calculus2.gif");
    private static final int ATTACK_INTERVAL = 1200;
    private static final int moveInterval = 2;
    private int moveCounter = 0;
    public Calculus2()
    {
        setImage(gif.getCurrentImage());
        setHealth(500);
    }

    public void act() 
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAdded >= ATTACK_INTERVAL) {
            attackPlant(100);
            lastAdded = currentTime;
        }
        if (frozen) {
            isFrozen("frozen_calculus2.png", gif);
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
        zombieHit("calculus2_dying.gif", 800, 50);   
    }
}
