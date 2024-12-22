 
import greenfoot.*;


public class DaiHocBachKhoa extends Plants {
    private static final int SUN_INTERVAL = 5000;
    private GifImage gif = new GifImage("dhbk.gif");
    @Override
    protected void initializePlant() {
        health = 300;
        cost = 50;
        /* gif = new GreenfootImage("dhbk.gif");
        setImage(gif); */
    }
    
    @Override
    protected void performAction() {
        setImage(gif.getCurrentImage());
        produceMoney(SUN_INTERVAL);
    }
    
    public void produceMoney(int timeInterval) {
        if (System.currentTimeMillis() - lastAdded >= timeInterval) {
            Money money = new Money();
            getWorld().addObject(money, getX() - 40, getY() + 40);
            lastAdded = System.currentTimeMillis();
        }
    }
}
