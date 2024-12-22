 
public class SinhVienVatLieuIcon extends Sidebar {
    public SinhVienVatLieuIcon(int posX, int posY) {
        setCostOfPlant(25);
        setIconCoordinates(posX, posY);
        setActiveImage("active_SVVL.png");
        setInactiveImage("inactive_SVVL.png");        
    }
    public void act () {
        createPlant(new SinhVienVatLieu());
    }
}
