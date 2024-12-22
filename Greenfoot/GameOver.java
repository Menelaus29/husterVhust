 
import greenfoot.*;
public class GameOver extends World {
    private GreenfootSound backgroundMusic = new GreenfootSound("lose.mp3");
    /*
     * Counstructor for objects of class GameOver
     */
    public GameOver(int score)
    {
        super(1111, 602, 1);
        backgroundMusic.play();
        Counter counter = new Counter();
        addObject(counter, 613, 553);
        counter.setValue(score);
        Replay replay = new Replay();
        addObject(replay, 1042, 577);
    }
    /*
     * Stop the background music after playing it once
     */
    public void stopBackgroundMusic()
    {
        backgroundMusic.stop();
    }
}
