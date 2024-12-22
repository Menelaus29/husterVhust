import greenfoot.*;
public class SinhVienKinhTe extends Plants {
    private static final int shootingInterval = 1300;
    private GifImage gif = new GifImage("SVKT.gif");
    @Override
    public void initializePlant() {
        this.health = 300;
        this.cost = 100;
    }

    @Override
    public void performAction() {
        setImage(gif.getCurrentImage());
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAdded >= shootingInterval) {
            shoot();
            lastAdded = currentTime;
        }
    }

    private void shoot() {
        if (getZombieInRow()) {
            Pea pea = new Pea();
            getWorld().addObject(pea, getX() + 30, getY());
        }
    }

    private boolean getZombieInRow() {
        int row = getY();
        for (Actor zombie : getWorld().getObjects(zombie.class)) {
            if (Math.abs(zombie.getY() - row) < 5) {
                return true;
            }
        }
        return false;
    }
}
