import greenfoot.*;  
import java.util.List;

public class ThuvienTQBNight extends World {
    private int score = 0;
    private int timeLeft = 60;
    private GreenfootImage background = new GreenfootImage("ThuvienTQBNight.png");
    private long lastSecond = -1;
    private MinigameCounter minigamecounter_score = new MinigameCounter();
    private MinigameCounter minigamecounter_time = new MinigameCounter();
    private String difficulty;
    private GreenfootSound backgroundMusicHard = new GreenfootSound("thigiuakyBG.mp3");
    private GreenfootSound backgroundMusicEasyMed = new GreenfootSound("thigiuakyBG1.mp3");

    // Tọa độ các hàng và cột của grid
    private int[] rows = {59, 170, 281, 392, 503};  // Y
    private int[] columns = {326, 413, 500, 588, 679, 765, 857, 943, 1042};  // X

    private int timeElapsed = 0;  // Biến lưu thời gian đã trôi qua tính bằng mili giây
    private int zombieSpawnTimer = 0; // Thêm biến đếm thời gian để tạo zombie

    public ThuvienTQBNight(String difficulty) {    
        super(1116, 602, 1);
        this.difficulty = difficulty;
        setBackground(background);
        setRowsCoordinates();
        setColumnsCoordinates();
    
        setPaintOrder(Hammer.class, WhackZombie.class); 
    
        addObject(new Hammer(), 500, 500);
        addObject(minigamecounter_score, 49, 78);
        addObject(minigamecounter_time, 49, 180);  // Thêm Counter cho thời gian
        //showText("Time: ", 50, 150);
        drawCustomText("Time: ", 20, 130, Color.WHITE, 30);
        
        minigamecounter_score.setValue(score);
        minigamecounter_time.setValue(timeLeft);  // Khởi tạo giá trị cho Counter thời gian
    
        lastSecond = System.currentTimeMillis();
        if (difficulty.equals("Easy") || difficulty.equals("Medium")) {
            backgroundMusicEasyMed.play();
        } else {
            backgroundMusicHard.play();
        }
    }
    
    
    /**
     * Possible Row Cordinates
     */
    private void setRowsCoordinates()
    {
        rows = new int[5];
        rows[0] = 59;
        rows[1] = 170;
        rows[2] = 281;
        rows[3] = 392;
        rows[4] = 503;
    }
    
    /**
     * Possible Column Cordinates
     */
    private void setColumnsCoordinates()
    {
        columns = new int[9];
        columns[0] = 326;
        columns[1] = 413;
        columns[2] = 500;
        columns[3] = 588;
        columns[4] = 679;
        columns[5] = 765;
        columns[6] = 857;
        columns[7] = 943;
        columns[8] = 1042;
    }
    
    /**
     * Ensures that the user can't randomly place plants in the world but only in the grid.
     */
    public int returnGridRowPosition(int y)
    {
        int row;
        
        int[] rowGrid = {0, 137, 246, 357, 462, 569};
        for(row = 0; row < 5; row += 1)
        {
            if(y > rowGrid[row] && y < rowGrid[row + 1])
            {
                return rows[row];
            }
        }
        return -1;
    }
    
    /**
     * Ensures that the user can't randomly place plants in the world but only in the grid.
     */
    public int returnGridColumnPosition(int x)
    {
        int column;
        
        int[] columnGrid = {280, 364, 449, 543, 632, 721, 812, 897, 985, 1089};
        for(column = 0; column < 9; column += 1)
        {
            if(x > columnGrid[column] && x < columnGrid[column + 1])
            {
                return columns[column];
            }
        }
        return -1;
    }

    private void addZombie(WhackZombie zombie, int row, int column) // Thêm tham số column
    {
        addObject(zombie, columns[column], rows[row]);        
    }
    
    private void randomZombie()
    {
        int randomRow = Greenfoot.getRandomNumber(5); // Chọn ngẫu nhiên hàng
        int randomColumn = Greenfoot.getRandomNumber(9); // Chọn ngẫu nhiên cột từ 0 đến 8
        int randomType = Greenfoot.getRandomNumber(5);
        WhackZombie zombie;
        
        switch (randomType) {
            case 0:
                zombie = new WhackCalculus1();
                break;
            case 1:
                zombie = new WhackCalculus2();
                break;
            case 2:
                zombie = new WhackLinearAlgebra();
                break;
            case 3:
                zombie = new WhackGeneralPhysics2();
                break;
            case 4:
                zombie = new WhackXSTK();
                break;
            default:
                zombie = new WhackCalculus1(); // Giá trị mặc định phòng khi có lỗi
                break;
        }

        addZombie(zombie, randomRow, randomColumn);
    }
    
    public void act() {
        long currentTime = System.currentTimeMillis();
        zombieSpawnTimer++;
        int spawnSpeed = getSpawnSpeed(difficulty);
    
        if (currentTime - lastSecond >= 1000) {
            if (timeLeft > 0) {
                timeLeft--;
                minigamecounter_time.setValue(timeLeft);  // Cập nhật Counter thời gian
            }
            lastSecond = currentTime;
        }
    
        if (zombieSpawnTimer >= spawnSpeed) {
            if (timeLeft > 0) {
                randomZombie();
            }
            zombieSpawnTimer = 0;
        }
    
        if (timeLeft <= 0) {
            stopBackgroundMusic();
            youWin();
            //showText("Game Over! Final Score: " + score, getWidth() / 2, getHeight() / 2);
        }
    }
    
    private int getSpawnSpeed(String difficulty) {
        if (difficulty.equals("Easy")) {
            return 130;
        } else if (difficulty.equals("Medium")) {
            return 100;
        } else if (difficulty.equals("Hard")) {
            return 70;
        } else {
            return 100;  // Mặc định là Medium nếu không có độ khó hợp lệ
        }
    }
    private void youWin() {
        stopBackgroundMusic();
        removeObjects(getObjects(null));
        Greenfoot.setWorld(new VictoryWorld(minigamecounter_score.getValue()));
    }
    
    public void addScore(int scoreValue) {
        score += scoreValue;
        minigamecounter_score.setValue(score);
    }
    
    private MinigameCounter getScoreCounter()
    {
        return minigamecounter_score;
    }
    private void stopBackgroundMusic()
    {
       if (difficulty.equals("Easy") || difficulty.equals("Medium")) {
           backgroundMusicEasyMed.stop();
       } else {
           backgroundMusicHard.stop();
       }
    }
    
    private void drawCustomText(String text, int x, int y, Color color, int fontSize) {
        GreenfootImage customText = new GreenfootImage(text, fontSize, color, null); // null để nền trong suốt
        getBackground().drawImage(customText, x, y);
    }
}