import greenfoot.*;
public class Money extends Actor
{
    private GifImage gif = new GifImage("money.gif");
    private boolean moneyFall = false;
    private int moneySpeed = 2;
    /**
     * Constructor for Money
     */
    public Money()
    {
        setImage(gif.getCurrentImage());        
    }
    
    /**
     * Act method for the sun, checks if the sun is collected and then adds up 25 to sun_counter
     */
    public void act() 
    {
       setImage(gif.getCurrentImage());
        
        if(moneyFall == true)
        {
            moneyDrop();
        }
       
        if (Greenfoot.mouseClicked(this))
        {
            ThuvienTQB world = (ThuvienTQB)getWorld();
            Counter sunCounter = world.getMoneyCounter();
            sunCounter.add(25);
            world.removeObject(this);
        }
    }
    
    /**
     * Stops the sun when it reaches the bottom of the screen
     */
    private void moneyDrop()
    {
        move(moneySpeed);
        if(getY() >= getWorld().getHeight() - 30)
            moneySpeed = 0;
    }
    
    /**
     * Enables the sun to fall
     */
    public void letTheMoneyDrop()
    {
        moneyFall = true;      
        turn(90);
    }
}
