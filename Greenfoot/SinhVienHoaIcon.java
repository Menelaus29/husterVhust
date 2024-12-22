 
public class SinhVienHoaIcon extends Sidebar {
    public SinhVienHoaIcon(int posX, int posY) {
        setCostOfPlant(150);
        setIconCoordinates(posX, posY);
        setActiveImage("active_SVH.png");
        setInactiveImage("inactive_SVH.png");        
    }
    public void act () {
        createPlant(new SinhVienHoa());
    }
}
