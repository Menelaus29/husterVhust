import greenfoot.*;

public class WhackZombie extends Actor {
    private int hitCount = 1;           
    private int scoreValue = 100;       
    private GifImage spawnGif;          
    private GifImage whackedGif;
    private int despawnTimer = 0;   
    private boolean isWhacked = false;
    private int whackedAnimationTimer = 0;
    private static final int WHACKED_ANIMATION_DURATION = 30; 
    public WhackZombie(String spawn, String whacked, int hitCount, int scoreValue) {
        this.spawnGif = new GifImage(spawn);
        this.whackedGif = new GifImage(whacked);
        this.hitCount = hitCount;
        this.scoreValue = scoreValue;
        setImage(spawnGif.getCurrentImage());
    }

    public void act() {
        if (!isWhacked) {
            despawnTimer++;
            if (despawnTimer > 95) {
                getWorld().removeObject(this);
                return;
            }

            if (Greenfoot.mouseClicked(this)) {
                hit();
            } else {
                setImage(spawnGif.getCurrentImage());
            }
        } else {
            whackedAnimationTimer++;
            if (whackedAnimationTimer > WHACKED_ANIMATION_DURATION) {
                getWorld().removeObject(this);
                return;
            }
            setImage(whackedGif.getCurrentImage());
        }
    }

    public void hit() {
        hitCount--;
        if (hitCount <= 0) {
            World world = getWorld();
            if (world != null && world instanceof ThuvienTQBNight) {
                ((ThuvienTQBNight) world).addScore(scoreValue);
            }
            isWhacked = true;
        }
    }
}
