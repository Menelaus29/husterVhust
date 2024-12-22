import greenfoot.*;
public class DaiHocBachKhoaIcon extends Sidebar {
    public DaiHocBachKhoaIcon(int posX, int posY) {
        setCostOfPlant(50);
        setIconCoordinates(posX, posY);
        setActiveImage("active_DHBK.png");
        setInactiveImage("inactive_DHBK.png");        
    }
    public void act () {
        createPlant(new DaiHocBachKhoa());
    }
}
