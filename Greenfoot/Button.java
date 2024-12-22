import greenfoot.*;

public class Button extends Actor {
    private String difficulty;
    private DifficultySelector menu;

    public Button(String difficulty, DifficultySelector menu) {
        this.difficulty = difficulty;
        this.menu = menu;

        // Chọn ảnh cho các nút (thay bằng tên file ảnh của bạn)
        String imageName = difficulty.toLowerCase() + ".png";  // Tạo tên file ảnh từ mức độ khó

        // Tải ảnh vào nút
        setImage(new GreenfootImage(imageName));
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            menu.setDifficulty(difficulty);  // Gọi hàm chọn mức độ khó từ menu
        }
    }
}