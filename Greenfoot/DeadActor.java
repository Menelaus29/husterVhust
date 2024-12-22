import greenfoot.*;

public class DeadActor extends Actor
{
    private GifImage gif;
    private int loopTime;
    public DeadActor(String filename, int timeforloop)
    {
        gif = new GifImage(filename);
        setImage(gif.getCurrentImage());
        loopTime = timeforloop;
    }

    private long lastAdded = System.currentTimeMillis();    
    public void act() 
    {
        long curTime  = System.currentTimeMillis();
        setImage(gif.getCurrentImage());
        if (curTime >= lastAdded + loopTime) //5000ms = 5s
        {
            lastAdded  = curTime;
            getWorld().removeObject(this);            
        }
    }   
}
