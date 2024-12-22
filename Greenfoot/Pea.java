 
import greenfoot.*;
public class Pea extends Bullet {
    private GifImage gif = new GifImage("logo_kinh_te.gif");
    public Pea() {
        super(4,20);
    }
    @Override
    public void act() {
        setImage(gif.getCurrentImage());
        super.act();
    }
    @Override
    protected void checkCollision() {
        Actor hitActor = getOneIntersectingObject(zombie.class);
        if (hitActor != null) {
            ((zombie) hitActor).takeDamage(20);
            getWorld().removeObject(this);
        }
    }
}
