 
import greenfoot.*;
public class snowPea extends Bullet {
    private GifImage gif = new GifImage("logo_dien_tu.gif");
    public snowPea() {
        super(4,20);
    }
    @Override
    public void act() {
        setImage(gif.getCurrentImage());
        super.act();
    }
    @Override
    protected void checkCollision() {
        Actor zombie = getOneIntersectingObject(zombie.class);
        if (zombie != null) {
            zombie target = (zombie) zombie;
            target.takeDamage(20);
            String frozenImg = target.getFrozenImage();
            GifImage gif = target.getDefaultGif();
            target.freeze(frozenImg, gif);
            getWorld().removeObject(this);
        }
    }
}
