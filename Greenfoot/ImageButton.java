import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

public class ImageButton extends Actor {
    private String action;
    private MainMenu mainMenu; // Reference to MainMenu

    public ImageButton(String imageName, String action, MainMenu mainMenu) {
        setImage(new GreenfootImage(imageName));
        this.action = action;
        this.mainMenu = mainMenu; // Store reference to MainMenu
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            handleAction();
        }
    }

    private void handleAction() {
        // Stop the background music from MainMenu
        mainMenu.stopBackgroundMusic();

        switch (action) {
            case "MainGame":
                Greenfoot.setWorld(new ThuvienTQB());
                break;
            case "MiniGame1":
                Greenfoot.setWorld(new DifficultySelector());
                break;
            case "QuitGame":
                Greenfoot.stop();
                break;
        }
    }
}
