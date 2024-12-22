 
public class SinhVienCoKhiIcon extends Sidebar {
    public SinhVienCoKhiIcon(int posX, int posY) {
        setCostOfPlant(50);
        setIconCoordinates(posX, posY);
        setActiveImage("active_SVCK.png");
        setInactiveImage("inactive_SVCK.png");        
    }
    public void act() {
        createPlant(new SinhVienCoKhi());
    }
}
