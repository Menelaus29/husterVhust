 
import greenfoot.*;

public class ThuvienTQB extends World
{
    //Variables Declation
    private int cellWidth;
    private int[] rows;
    private int[] columns;
    private Counter counter_money = new Counter();
    private Counter counter_score = new Counter();
    private GreenfootSound backgroundMusic = new GreenfootSound("background.mp3");
    
    /*
     Constructor for objects of class backyard.
     */
    public ThuvienTQB()
    {   
        // Create a new world with 1111x602 cells with a cell size of 1x1 pixels.
        super(1116, 602, 1);
        
        setRowsCoordinates();
        setColumnsCoordinates();
        //calculateCellWidth();
        prepare();
        backgroundMusic.playLoop();        
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
    
    /**
     * Prepares Lawnmovers which serve as an extra life
     */
    private void prepareTailieuHust()
    {
        int[] rows = {83, 190, 300, 410, 520};
        int[] columns = {265, 258, 250, 243, 235};
        for(int i = 0; i<5; i++)
        {
            addObject(new TaiLieuHust(), columns[i] , rows[i]);
        }
    }
    
    /**
     * Prepares the sidebar which houses all the icons
     */
    private void prepareSidebar()
    {
        addObject(new DaiHocBachKhoaIcon(49, 140)   , 49, 140);
        addObject(new SinhVienKinhTeIcon(49, 236) , 49, 188);
        addObject(new SinhVienDienTuIcon(49, 332), 49, 236);
        addObject(new SinhVienVatLieuIcon(49, 428) , 49, 284);
        addObject(new SinhVienCNTTIcon(49, 428) , 49, 332);
        addObject(new SinhVienHoaIcon(49, 428) , 49, 380);
        addObject(new SinhVienCoKhiIcon(49, 428) , 49, 428);
        
        addObject(counter_score, 49, 78);
        addObject(counter_money, 49, 567);
        counter_money.setValue(20000);
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */

    private void prepare()
    {
        prepareSidebar();
        prepareTailieuHust();
        addObject(new Duoihoc(1035, 40), 1035, 40); 
    }
    
    /**
     * Adds a zombie in a specific cell
     */
    private void addZombie(zombie zombie, int row)
    {
        //int centerX = columns[columns.length - 1] + cellWidth / 2; // Calculate center of the last column
        //addObject(zombie, centerX, rows[row]);
        addObject(zombie, columns[8] + 70, rows[row]);        
    }
    
    /**
     * Use to randomize the type of zombie being spawned (Normal OR Football)
     */
    
    private void randomZombie(int row) {
        if (currentWave == 0) { 
            if (Greenfoot.getRandomNumber(2) == 0) {
                addZombie(new Calculus1(), row);
            } else {
                addZombie(new LinearAlgebra(), row);
            }
        } else if (currentWave == 1) { 
            int random = Greenfoot.getRandomNumber(3);
            if (random == 0) {
                addZombie(new Calculus1(), row);
            } else if (random == 1) {
                addZombie(new LinearAlgebra(), row);
            } else {
                addZombie(new GeneralPhysic2(), row);
            }
        } else if (currentWave == 2) { 
            int random = Greenfoot.getRandomNumber(4);
            if (random == 0) {
                addZombie(new Calculus1(), row);
            } else if (random == 1) {
                addZombie(new LinearAlgebra(), row);
            } else if (random == 2) {
                addZombie(new Calculus2(), row);
            } else {
                addZombie(new GeneralPhysic2(), row);
            }
        } else { 
            int random = Greenfoot.getRandomNumber(5);
            if (random == 0) {
                addZombie(new Calculus1(), row);
            } else if (random == 1) {
                addZombie(new LinearAlgebra(), row);
            } else if (random == 2) {
                addZombie(new Calculus2(), row);
            } else if (random == 3) {
                addZombie(new GeneralPhysic2(), row);
            } else {
                addZombie(new XSTK(), row);
            }
        }
    }
    
    /**
     * Spawns a zombie in every row i.e a wave  
     */
    private void createWave()
    {
        currentWave++;
        for(int i = 0; i < 5; i += 1)
        {
            randomZombie(i);
        }
    }
    
    //Variables used for creatin a wave.
    private long lastAdded = System.currentTimeMillis();
    private int timeUnit = 1; // 1 time unit = 10 seconds
    private int numberOfWaves = 0;
    private int waveNumber = 0;
    private int currentWave = 0;
    private int totalWaves = 6;
    
    /**
     *   The act method is called by the greenfoot framework to give actors a chance to perform some action.
     *   Here it keeps the record of the time and accodingly spawns zombies
     *   
     */
    public void act()
    {      
        long curTime = System.currentTimeMillis();

        if (curTime >= lastAdded + 5000) { 
            lastAdded = curTime;
            timeUnit += 1;
            produceMoneyFromSky();
            if (currentWave == 0) {
                randomZombie(Greenfoot.getRandomNumber(5));
            }
        }

         if (timeUnit % 3 == 0 && timeUnit > 3) {
            if (curTime <= lastAdded + 25) {
                if (currentWave < totalWaves) {
                    addObject(new ZombiesAreComing(), 639, 273);
                    numberOfWaves = (timeUnit - 3) / 3;
                    createWave();
                    waveNumber = 1;
                } else if (currentWave == totalWaves && getObjects(zombie.class).isEmpty()) {
                    youWin();
                }
            }

            // Spawn additional waves within the wave period
            if ((curTime > lastAdded + waveNumber * 10000) && (curTime < lastAdded + numberOfWaves * 10000) && currentWave < totalWaves) {
                waveNumber += 1;
                createWave();
            }
        }
    }
    private void youWin() {
    stopBackgroundMusic();
    removeObjects(getObjects(null));
    Greenfoot.setWorld(new VictoryWorld(counter_score.getValue()));
    }
    
    /**
     * Randomly produces sun from the sky
     */
    private void produceMoneyFromSky()
    {
        Money money = new Money();
        addObject(money, columns[Greenfoot.getRandomNumber(8)], 0);
        money.letTheMoneyDrop();
    }
    
    /**
     * Returns the sun counter
     */
    public Counter getMoneyCounter()
    {
        return counter_money;
    }
    
    /**
     * Reuturns the score counter
     */
    public Counter getScoreCounter()
    {
        return counter_score;
    }
    
    public void stopBackgroundMusic()
    {
       backgroundMusic.stop();
    }

}
