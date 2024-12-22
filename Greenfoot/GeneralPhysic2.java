//Conehead
import greenfoot.*;
public class GeneralPhysic2 extends zombie 
{
    private GifImage gif = new GifImage("general_physic2.gif");
    private GifImage gif_withoutcone = new GifImage("general_physic2_without_cone.gif");
    private boolean hasCone = true;
    private static final int ATTACK_INTERVAL = 1500;
    private static final int moveInterval = 2;
    private int moveCounter = 0;
    
    public GeneralPhysic2()
    {
        setImage(gif.getCurrentImage());
        setHealth(250);
    }
    
    @Override
    public String getFrozenImage() {
        return hasCone ? "frozen_general_physic2.png" : "frozen_general_physic2_without_cone.png";
    }
    
    @Override
    public GifImage getDefaultGif() {
        return new GifImage(hasCone ? "general_physic2.gif" : "general_physic2_without_cone.gif");
    }

    
    public void act() 
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAdded >= ATTACK_INTERVAL) {
            attackPlant(50);
            lastAdded = currentTime;
        }
        if (frozen) {
            String frozenImage = hasCone ? "frozen_general_physic2.png" : "frozen_general_physic2_without_cone.png";
            GifImage currentGif = hasCone ? gif : gif_withoutcone;
            isFrozen(frozenImage, currentGif);
        } else {
            moveCounter++;
            if (moveCounter >= moveInterval) {
                setImage(hasCone ? gif.getCurrentImage() : gif_withoutcone.getCurrentImage());
                move(zombieSpeed);
                moveCounter = 0;
            }
        }
        if (hasCone && zombieHealth <= 150) switchToWithoutCone();
        checkGameOver();
        setSpeed(-1);
        zombieHit("general_physic2_dying.gif", 800, 20);       
    }
    
    private void switchToWithoutCone() {
        hasCone = false;
        setImage(gif_withoutcone.getCurrentImage());
    }
}