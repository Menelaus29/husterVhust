import greenfoot.*;
import java.util.*;

public class SinhVienCNTT extends Plants {
    private static final int ATTACK_COOLDOWN = 2000; 
    private GifImage gif = new GifImage("SVCNTT.gif");
    private long lastAttackTime = 0; // To track the time of the last attack

    @Override
    protected void initializePlant() {
        this.health = 300;
        this.cost = 125;
    }

    @Override
    protected void performAction() {
        setImage(gif.getCurrentImage());
        long currentTime = System.currentTimeMillis();

        // Check if the attack cooldown has passed AND if there are zombies in range
        if (currentTime - lastAttackTime >= ATTACK_COOLDOWN && zombiesInRange()) {
            attackZombies();
            lastAttackTime = currentTime;
        }
    }

    protected boolean zombiesInRange() {
        int range = 300; // Range to check (adjust if needed)
        int cellWidth = 87;
        int cellHeight = 110;

        // Check the three rows in front of the plant
        for (int row = -1; row <= 1; row++) { // -1, 0, 1 (above, same, below)
            for (int i = 1; i <= range / cellWidth; i++) {
                int checkX = getX() + i * cellWidth;
                int checkY = getY() + row * cellHeight;

                // Get objects at the calculated position
                List<zombie> zombies = getWorld().getObjectsAt(checkX, checkY, zombie.class);
                if (!zombies.isEmpty()) {
                    return true; // Zombie found!
                }
            }
        }
        return false; // No zombies found
    }

    private void attackZombies() {
        int[][] directions = {{1, 0}, {1, -1}, {1, 1}}; // right, above-right, below-right
        for (int[] direction : directions) {
            binaryBullet binary = new binaryBullet(direction[0], direction[1]);
            if (getWorld() != null) {
                getWorld().addObject(binary, getX(), getY());
            }
        }
    }
}