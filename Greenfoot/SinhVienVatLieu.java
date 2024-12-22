 
import java.util.List;
import greenfoot.*;

// Potato Mine Implementation
public class SinhVienVatLieu extends Plants {
    private static final int ARMING_DELAY = 5000; 
    private boolean isArmed = false;
    private GifImage gifUnarmed = new GifImage("sv_vat_lieu_unarmed.gif");
    @Override
    protected void initializePlant() {
        this.health = 150;
        this.cost = 25;
    }
    
    @Override
    protected void performAction() {
        if (isArmed == false) {
            setImage(gifUnarmed.getCurrentImage());
        }
        long currentTime = System.currentTimeMillis();
        if (!isArmed && currentTime - lastAdded >= ARMING_DELAY) {
            arm();
        }
        if (isArmed) {
            checkZombieForExplosion();
        }
    }
    
    private void arm() {
        isArmed = true;
        setImage("sv_vat_lieu_armed.png");
    }
    
    private void checkZombieForExplosion() {
        Actor hitActor = getOneIntersectingObject(zombie.class);
        if (hitActor != null) {
            ((zombie) hitActor).takeDamage(1000);
            getWorld().removeObject(this);
        }
    }
}

