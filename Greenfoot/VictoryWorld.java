import greenfoot.*;

public class VictoryWorld extends World {
    private GreenfootSound victoryMusic = new GreenfootSound("victory.wav"); 
    private GreenfootImage victoryImg = new GreenfootImage("VictoryWorld.png");

    public VictoryWorld(int score) {
        super(1116, 602, 1);
        victoryMusic.playLoop();
        Counter1 counter = new Counter1();
        addObject(counter, 600, 565);
        counter.setValue(score);
        // Add a button to return to the main menu, passing the victory music reference
        addObject(new MainMenuButton(victoryMusic), getWidth() / 2, getHeight() / 2 + 150);
    }

    public void act() {
        setBackground(victoryImg);
    }

    public void stopped() {
        victoryMusic.pause();
    }
}