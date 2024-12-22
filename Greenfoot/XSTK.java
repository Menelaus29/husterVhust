//Bucket head
import greenfoot.*;

public class XSTK extends zombie
{
    private GifImage gif = new GifImage("xstk.gif");
    private GifImage gif_withoutbucket = new GifImage("xstk_without_bucket.gif");
    private boolean hasBucket = true;
    private static final int ATTACK_INTERVAL = 1200;
    private static final int moveInterval = 2;
    private int moveCounter = 0;
    
    public XSTK()
    {
        setImage(gif.getCurrentImage());
        setHealth(500);
    }
    @Override
    public String getFrozenImage() {
        return hasBucket ? "frozen_xstk.png" : "frozen_xstk_without_bucket.png";
    }
    
    @Override
    public GifImage getDefaultGif() {
        return new GifImage(hasBucket ? "xstk.gif" : "xstk_without_bucket.gif");
    }

    public void act() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAdded >= ATTACK_INTERVAL) {
            attackPlant(100);
            lastAdded = currentTime;
        }
        if (!frozen) {
            moveCounter++;
            if (moveCounter >= moveInterval) {
                setImage(hasBucket ? gif.getCurrentImage() : gif_withoutbucket.getCurrentImage());
                move(zombieSpeed);
                moveCounter = 0;
            }
        } else {
            isFrozen(hasBucket ? "frozen_xstk.png" : "frozen_xstk_without_bucket.png", hasBucket ? gif : gif_withoutbucket);
        }
        if (hasBucket && zombieHealth <= 300) switchToWithoutBucket();
        checkGameOver();
        setSpeed(-1);
        zombieHit("xstk_dying.gif", 800, 50);       
    }
    private void switchToWithoutBucket() {
        hasBucket = false;
        setImage(gif_withoutbucket.getCurrentImage());
    }
}
