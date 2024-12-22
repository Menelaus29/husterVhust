import greenfoot.*;
import java.util.List;

// Cherry Bomb Implementation
public class SinhVienHoa extends Plants {
    private static final int EXPLOSION_DELAY = 1300; // 1.3 seconds in milliseconds
    private static final int TILE_HEIGHT = 150; // Row height in pixels
    private static final int TILE_WIDTH = 150;   // Column width in pixels
    private boolean hasExploded = false;
    private GifImage gif = new GifImage("SVHoa.gif");

    @Override
    protected void initializePlant() {
        this.cost = 150;
        this.health = 10000;
    }

    @Override
    protected void performAction() {
        setImage(gif.getCurrentImage());
        if (!hasExploded && System.currentTimeMillis() - lastAdded >= EXPLOSION_DELAY) {
            explode();
        }
    }

    private void explode() {
        hasExploded = true;

        // Get the grid's center coordinates for this plant
        int centerX = getX();
        int centerY = getY();

        // Calculate the bounds of the 3x3 grid explosion
        int minX = centerX - TILE_WIDTH;
        int maxX = centerX + TILE_WIDTH;
        int minY = centerY - TILE_HEIGHT;
        int maxY = centerY + TILE_HEIGHT;

        // Find all zombies in the explosion area
        List<zombie> allZombies = getWorld().getObjects(zombie.class);
        for (zombie z : allZombies) {
            int zombieX = z.getX();
            int zombieY = z.getY();

            // Check if the zombie is within the 3x3 grid area
            if (zombieX >= minX && zombieX <= maxX && zombieY >= minY && zombieY <= maxY) {
                z.takeDamage(1800);
            }
        }

        // Play the explosion animation and remove this plant
        dyingPlantAnimation(getImage().toString(), "10");
        getWorld().removeObject(this);
    }
}