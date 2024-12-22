 

public class SinhVienKinhTeIcon extends Sidebar {
    public SinhVienKinhTeIcon(int posX, int posY) {
        setCostOfPlant(100);
        setIconCoordinates(posX, posY);
        setActiveImage("active_SVKT.png");
        setInactiveImage("inactive_SVKT.png");        
    }
    public void act () {
        createPlant(new SinhVienKinhTe());
    }
}
