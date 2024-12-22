import greenfoot.*;
import java.util.*;

public class Hammer extends Actor {
    private GreenfootImage hammerImage = new GreenfootImage("hammer.png");

    public Hammer() {
        setImage(hammerImage);
    }

    public void act() {
        followMouse();
        checkZombieClick();
    }

    // Hàm di chuyển búa theo chuột
    private void followMouse() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            setLocation(mouse.getX(), mouse.getY());
        }
    }

    // Hàm kiểm tra click chuột để đập zombie
    private void checkZombieClick() {
        if (Greenfoot.mouseClicked(null)) { // Kiểm tra sự kiện click chuột
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                // Lấy danh sách các đối tượng tại vị trí click chuột
                List<Actor> actors = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Actor.class);
                for (Actor actor : actors) {
                    if (actor instanceof WhackZombie) { // Nếu là Zombie
                        WhackZombie zombie = (WhackZombie) actor;
                        zombie.hit(); // Gọi phương thức hit() để xử lý đập zombie
                        return;       // Chỉ xử lý một zombie mỗi lần click
                    }
                }
            }
        }
    }
}
