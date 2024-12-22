public class SinhVienCoKhi extends Plants {
    private static final int CRACK_THRESHOLD_1 = 1300;
    private static final int CRACK_THRESHOLD_2 = 600;
    private GifImage gif = new GifImage("SVCK.gif");
    private GifImage gif1 = new GifImage("SVCK_cracked1.gif");
    private GifImage gif2 = new GifImage("SVCK_cracked2.gif");

    @Override
    protected void initializePlant() {
        this.health = 2000;
        this.cost = 50;
    }
    @Override
    protected void performAction() {
        setImage(gif.getCurrentImage());
        updateAppearance();
    }
    public void updateAppearance() {
        if (health > CRACK_THRESHOLD_2 && health <= CRACK_THRESHOLD_1 && !getImage().toString().equals("SVCK_cracked1.gif")) {
            setImage(gif1.getCurrentImage());
        } else if (health <= CRACK_THRESHOLD_2 && !getImage().toString().equals("SVCK_cracked2.gif")) {
            setImage(gif2.getCurrentImage());
        }
    }
}