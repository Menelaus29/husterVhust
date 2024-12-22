 import greenfoot.*;
public class SinhVienDienTu extends Plants {
    private static final int shootingInterval = 1300;
    private GreenfootImage snowPea;
    private GifImage gif = new GifImage("SVDT.gif");
    @Override
    public void initializePlant() {
        this.health = 300;
        this.cost = 250;
    }
    protected void performAction() {
        setImage(gif.getCurrentImage());
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAdded >= shootingInterval) {
            shoot();
            lastAdded = currentTime;
        }
    }
    
    private void shoot() {
        if (getZombieInRow()) {
            snowPea frozenPea = new snowPea();
            getWorld().addObject(frozenPea, getX() + 30, getY());
        }
    }
    
    private boolean getZombieInRow() {
        int row = getY();
        for (zombie zombie : getWorld().getObjects(zombie.class)) {
            if (Math.abs(zombie.getY() - row) < 5) {
                return true;
            }
        }
        return false;
    }
}
