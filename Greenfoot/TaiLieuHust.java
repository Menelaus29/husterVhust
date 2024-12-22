import greenfoot.*;

public class TaiLieuHust extends Actor {
    private GifImage gif = new GifImage("tailieuhust.gif");
    private int speed = 0;
    private GreenfootSound sound = new GreenfootSound("lamborghini.wav");

    public void act() {
        setImage(gif.getCurrentImage());
        move(speed);

        // Use getIntersectingObjects instead of isTouching
        java.util.List<zombie> intersectingZombies = getIntersectingObjects(zombie.class);
        if (!intersectingZombies.isEmpty()) {
            for (zombie z : intersectingZombies) {
                getWorld().removeObject(z); // More efficient removal
            }
            speed = 12;
            sound.play();
        }

        if (speed > 0) {
            setImage(gif.getCurrentImage());
        }
        checkBoundaries();
    }

    /**
     * Removes the lawnmower from the world if it reaches the right edge
     */
    protected void checkBoundaries() {
        if (getX() > getWorld().getWidth() - 10)
            getWorld().removeObject(this);
    }
}