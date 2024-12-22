import greenfoot.*;

public class zombie extends Actor
{
    protected int zombieSpeed;
    protected int zombieHealth;   
    protected GreenfootSound comingZombie = new GreenfootSound("zombies_coming.wav");
    protected static boolean gameOver = false;
    protected static int zombieCount = 0;
    protected boolean frozen = false;
    private long frozenStartTime;
    private boolean frozenImageShown = false;
    protected long lastAdded;
    
    /**
     * Constructor for Zombie Class , plays sound when the first zombie comes 
     */
    public zombie()
    {   
        this.lastAdded = System.currentTimeMillis();
        if (zombieCount == 0)
        {
            comingZombie.play();
        }
        zombieCount += 1;
    }

    
    protected void setSpeed(int speed)
    {
        if (isTouching(Plants.class))
        {
            zombieSpeed = 0;
        }
        else
        {
            zombieSpeed = speed;
        }
    }
    
    protected void isFrozen(String frozenImage, GifImage gif) {
        /*int currentSpeed = zombieSpeed;
        if (!frozen) {
            frozen = true;
            zombieSpeed = 0;

            setImage(frozenImage);
            frozenStartTime = System.currentTimeMillis();
        }

        if (frozen && System.currentTimeMillis() - frozenStartTime >= 1000) {
            frozen = false;
            setImage(gif.getCurrentImage());
            zombieSpeed = currentSpeed;
        }*/
        int currentSpeed = zombieSpeed;

        if (!frozen) {
            frozen = true; 
            frozenImageShown = true; 
            zombieSpeed = 0;
            frozenStartTime = System.currentTimeMillis(); 
            setImage(frozenImage);
           // frozenStartTime = System.currentTimeMillis(); 
        }
    
        long elapsedTime = System.currentTimeMillis() - frozenStartTime;

        if (frozenImageShown && elapsedTime >= 2000) { // Sau 2 giây
            frozenImageShown = false; // Kết thúc hiển thị hình ảnh đóng băng
            frozen = false;
            setImage(gif.getCurrentImage()); // Trở lại ảnh GIF ban đầu
            zombieSpeed = currentSpeed;
        }
    }

    protected void setHealth(int health)
    {
        zombieHealth = health;
    }
    
    /**
     * This method is called whenever a zombie is hit by an object of the bullet class.
     * It takes four inputs:
     * 1. the damage caused by the bullet, which is reduced from its health,
     * 2. the filename where the animation for its removal is stored,
     * 3. the time of a loop of the gif, and
     * 4. the points for killing the zombie
     */
    protected void zombieHit(String filename, int timeLoop, int points)
    {         
        if (zombieHealth <= 0) {
            removeTouching(Bullet.class);                
            dyingZombieAnimation(filename,timeLoop);
                
            ThuvienTQB world = (ThuvienTQB)getWorld();
            Counter score = world.getScoreCounter();
            score.add(points);
            world.removeObject(this);
        }
    }

    public void attackPlant(int damage) {
        if (isTouching(Plants.class)) {
            Plants plant = (Plants) getOneIntersectingObject(Plants.class);
            if (plant != null) plant.takeDamage(damage);
        }
    }
    
    /**
     * This class is related to the animation part of the the zombie being removed from the screen
     */
    protected void dyingZombieAnimation(String filename, int timeLoop)
    {
        DeadActor dead = new DeadActor(filename,timeLoop);
        World world = getWorld();
        world.addObject(dead, getX(), getY());
    }
    
    /**
     * Checks if any Zombie crosses the garden and enters the house and ends the game
     */
    protected void checkGameOver()
    {
       ThuvienTQB world = (ThuvienTQB)getWorld();
       int column = world.returnGridColumnPosition(getX());
       if (getX() < 260)
       {   
           setHealth(-1);
           Counter score = world.getScoreCounter();          
           world.stopBackgroundMusic();
           gameOver = true;
           Greenfoot.setWorld(new GameOver(score.getValue()));            
        }    
    }
    public void takeDamage(int damage) {
        zombieHealth -= damage;
    }
    public GifImage getDefaultGif() {
        String filename = convertToSnakeCase(this.getClass().getSimpleName());
        return new GifImage(filename + ".gif");
    }
    public void freeze(String frozenImage, GifImage gif) {
        isFrozen(frozenImage, gif);
    }
    public String getFrozenImage() {
        String filename = convertToSnakeCase(this.getClass().getSimpleName());
        return "frozen_" + filename + ".png";
    }
    private String convertToSnakeCase(String camelCase) {
        return camelCase.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
    
}
