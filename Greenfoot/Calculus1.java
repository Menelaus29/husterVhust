//Normal zombie
import greenfoot.*;

public class Calculus1 extends zombie
{
    private GifImage gif = new GifImage("calculus1.gif");
    private static final int ATTACK_INTERVAL = 1500; //1.5 seconds
    private static final int moveInterval = 2;
    private int moveCounter = 0;
    
    public Calculus1()
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
            isFrozen("frozen_calculus1.png",gif); 
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
        zombieHit("calculus1_dying.gif", 800, 10);   
    }
}
