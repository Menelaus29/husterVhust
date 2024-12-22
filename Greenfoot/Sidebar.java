import greenfoot.*;
import java.util.*;

public class Sidebar extends Actor {
    // Variable declaration
    protected int costOfPlant;
    protected boolean isGrabbed, isActive = false;
    protected int iconX, iconY;
    protected GreenfootImage activeImage, inactiveImage;
    protected boolean isDragging = false;
    protected Actor dragImage;

    /**
     * Sets the cost of each plant in terms of sun
     */
    protected void setCostOfPlant(int cost) {
        costOfPlant = cost;
    }

    /**
     * Sets the position of the icon on the sidebar
     */
    protected void setIconCoordinates(int x, int y) {
        iconX = x;
        iconY = y;
    }

    /**
     * To create a plant
     */
    protected void createPlant(Plants plant) {
        ThuvienTQB world = (ThuvienTQB) getWorld();
        Counter sunCounter = world.getMoneyCounter();
        if (costOfPlant <= sunCounter.getValue()) {
            setImage(activeImage);
            createPlantDragAndDrop(plant, world, sunCounter);
        } else {
            setImage(inactiveImage);
        }
    }

    /**
     * The logic for the Drag and Drop is in this function.
     */
    protected void createPlantDragAndDrop(Plants plant, ThuvienTQB world, Counter moneyCounter) {
        MouseInfo mi = Greenfoot.getMouseInfo();

        // Check if mouse is pressed on the sidebar icon and the cost is valid
        if (Greenfoot.mousePressed(this) && costOfPlant <= moneyCounter.getValue()) {
            isDragging = true;

            // Create a new actor for the drag image
            dragImage = new Actor() {
                @Override
                public void act() {
                    if (!isDragging) {
                        world.removeObject(this);
                    }
                }
            };
            dragImage.setImage(new GreenfootImage(getImage()));
            world.addObject(dragImage, getX(), getY());
            world.setPaintOrder(Sidebar.class, Plants.class, zombie.class);
        }

        // Update drag image position when dragging
        if (isDragging && mi != null) {
            dragImage.setLocation(mi.getX(), mi.getY());
        }

        // When drag ends, place the plant in the correct position
        if (isDragging && Greenfoot.mouseDragEnded(this)) {
            isDragging = false;

            if (mi != null) {
                int plantRow = world.returnGridRowPosition(mi.getY());
                int plantColumn = world.returnGridColumnPosition(mi.getX());

                if (plantRow != -1 && plantColumn != -1) {
                    List<Actor> objectsAtLocation = world.getObjectsAt(plantColumn, plantRow, Actor.class);
                    boolean isOccupied = objectsAtLocation.stream().anyMatch(actor -> !(actor instanceof Money));

                    if (!isOccupied) {
                        try {
                            // Create and add the plant to the world at the correct grid position
                            Plants newPlant = plant.getClass().getDeclaredConstructor().newInstance();
                            world.addObject(newPlant, plantColumn, plantRow);
                            moneyCounter.add(-costOfPlant);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * Sets colorful image of the icon when it can be bought
     */
    protected void setActiveImage(String activeImageLink) {
        activeImage = new GreenfootImage(activeImageLink);
    }

    /**
     * Sets the grayscale image of the icon when it can't be bought 
     */
    protected void setInactiveImage(String inactiveImageLink) {
        inactiveImage = new GreenfootImage(inactiveImageLink);
    }
}