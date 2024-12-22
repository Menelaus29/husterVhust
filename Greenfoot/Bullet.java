 
import greenfoot.*;
public abstract class Bullet extends Actor {
    protected int speed;
    protected int damage;

    public Bullet(int bulletSpeed, int bulletDmg) {
        this.speed = bulletSpeed;
        this.damage = bulletDmg;
    }

    public void act() {
        /*move(speed);
        checkBoundaries();
        checkCollision();*/
        if (!checkBoundaries() && getWorld() != null) {
            checkCollision();
            if (getWorld() != null) move(speed);
        }
    }

    protected boolean checkBoundaries() {
        int halfWidth = getImage().getWidth() / 2;
        int halfHeight = getImage().getHeight() / 2;
    
        if (getX() - halfWidth < 0 || getX() + halfWidth > getWorld().getWidth() ||
            getY() - halfHeight < 0 || getY() + halfHeight > getWorld().getHeight()) {
            getWorld().removeObject(this);
            return true; 
        }
        return false; 
    }

    protected void checkCollision() {
        if (getWorld() != null) { // Đảm bảo đối tượng còn trong World trước khi kiểm tra va chạm
            Actor hitActor = getOneIntersectingObject(zombie.class);
            if (hitActor != null) {
                ((zombie) hitActor).takeDamage(damage);
                if (getWorld() != null) {
                    getWorld().removeObject(this);
                }
            }
        }
    }
}