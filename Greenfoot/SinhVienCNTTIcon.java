 
public class SinhVienCNTTIcon extends Sidebar {
    public SinhVienCNTTIcon(int posX, int posY) {
        setCostOfPlant(125);
        setIconCoordinates(posX, posY);
        setActiveImage("active_SVCNTT.png");
        setInactiveImage("inactive_SVCNTT.png");        
    }
    public void act () {
        createPlant(new SinhVienCNTT());
    }
}
