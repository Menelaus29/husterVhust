import greenfoot.*;

public class MainMenuButton extends Actor {
    private GreenfootSound victoryMusic;

    // Constructor accepting the sound instance
    public MainMenuButton(GreenfootSound victoryMusic) {
        setImage(new GreenfootImage("Main Menu", 50, Color.WHITE, Color.RED));
        this.victoryMusic = victoryMusic;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            if (victoryMusic != null) {
                victoryMusic.stop(); // Stop the victory music
            }
            Greenfoot.setWorld(new MainMenu()); // Switch to the main menu
        }
    }
}
