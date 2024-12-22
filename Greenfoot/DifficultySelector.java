import greenfoot.*;

public class DifficultySelector extends World {
    private GreenfootImage background = new GreenfootImage("menubackground.png");

    public DifficultySelector() {
        super(1116, 602, 1);  // Kích thước của menu
        setBackground(background);

        // Thêm các nút để chọn mức độ khó
        addObject(new Button("Easy", this), 350, 320);  // Easy
        addObject(new Button("Medium", this), 800, 320);  // Medium
        addObject(new Button("Hard", this), 580, 480);
    }

    // Hàm để bắt sự kiện chọn mức độ khó
    public void setDifficulty(String difficulty) {
        Greenfoot.setWorld(new ThuvienTQBNight(difficulty));  // Chuyển sang World của Backyard với độ khó đã chọn
    }
}