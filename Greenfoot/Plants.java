 
import greenfoot.*;
public abstract class Plants extends Actor {
    protected int health;
    protected int cost;
    protected long lastAdded;
    protected boolean isActive;
    protected GreenfootImage gif;
    protected GreenfootSound plantEaten;
    public Plants() {
        this.lastAdded = System.currentTimeMillis();
        this.plantEaten = new GreenfootSound("chomp.wav");
        this.isActive = true;
        initializePlant();
    }
    protected abstract void initializePlant(); // to allow each each subclass of Plants to define specific initialization logic

    public void act() {
        if (isActive) {
            performAction();
        }
    }

    protected abstract void performAction(); // to allow each each subclass of Plants to define specific actions


    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            dyingPlantAnimation(getImage().toString(), "1000");
            getWorld().removeObject(this);
            return;
        }
        plantHit(getImage().toString(), 500);
    }

    public void dyingPlantAnimation(String fileName, String timeLoop) {
        GreenfootImage originalImage = getImage();
        for (int transparency = 255; transparency > 0; transparency -= 51) {
            GreenfootImage fadGreenfootImage = new GreenfootImage(originalImage);
            fadGreenfootImage.setTransparency(transparency);
            setImage(fadGreenfootImage);
            Greenfoot.delay(2);
        }
    }

    private void plantHit(String fileName, int timeLoop) {
        if (isTouching(zombie.class)) {
            plantEaten.play();
        }
        GreenfootImage oGreenfootImage = getImage();
        GreenfootImage hGreenfootImage = new GreenfootImage(oGreenfootImage);
        hGreenfootImage.setTransparency(100);
        setImage(hGreenfootImage);
        Greenfoot.delay(timeLoop/100);
        setImage(oGreenfootImage);
    }

    private int getCost() {
        return this.cost;
    }
}
