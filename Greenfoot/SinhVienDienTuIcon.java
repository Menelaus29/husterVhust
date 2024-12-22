
public class SinhVienDienTuIcon extends Sidebar {
    public SinhVienDienTuIcon(int posX, int posY) {
        setCostOfPlant(250);
        setIconCoordinates(posX, posY);
        setActiveImage("active_SVDT.png");
        setInactiveImage("inactive_SVDT.png");        
    }
    public void act () {
        createPlant(new SinhVienDienTu());
    }
}
