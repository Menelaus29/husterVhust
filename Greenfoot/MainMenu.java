import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

public class MainMenu extends World {
    private GreenfootSound background = new GreenfootSound("menu.mp3");

    public MainMenu() {    
        super(1116, 602, 1); 
        prepare();
        background.playLoop();
    }

    private void prepare() {
        // Pass 'this' to ImageButton instances
        addObject(new ImageButton("MainGamebutton.png", "MainGame", this), getWidth() / 2, 400);
        addObject(new ImageButton("MiniGame1Button.png", "MiniGame1", this), getWidth() / 2, 485);
        addObject(new ImageButton("QuitGameButton.png", "QuitGame", this), getWidth() / 2, 560);
    }

    public void stopBackgroundMusic() {
        background.stop();
    }
}
