package com.dale.superhairyjoe;


import com.dale.superhairyjoe.entity.GameObject;
import com.dale.superhairyjoe.entity.mob.Player;
import com.dale.superhairyjoe.gfx.Sprite;
import com.dale.superhairyjoe.gfx.SpriteBig;
import com.dale.superhairyjoe.gfx.SpriteSheet;
import com.dale.superhairyjoe.gfx.SpriteSheetBig;
import com.dale.superhairyjoe.input.KeyInput;
import com.dale.superhairyjoe.sounds.Sound;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


/**
 *
 * @author David
 */

public class Game
  extends Canvas
  implements Runnable
{
  public static final int WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
  public static final int HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
  public static final int SCALE = 1;
  public static final String TITLE = "Super Hairy Joe";
  public static final int scale1 = 1;
  private Color randomColor;
  private Thread thread;
  public boolean running = false;
  public static Handler handler;
  public static SpriteSheet sheet;
  public static SpriteSheetBig sheetbig;
  public static Sprite grass;
  public static Sprite lava;
  public static Sprite portal;
  public static SpriteBig[] player = new SpriteBig[14];
  public static Sprite mushroom;
  public static Sprite[] goomba;
  public static Sprite[] seagull;
  public static Sprite powerUp;
  public static Sprite usedPowerUp;
  public static Sprite[] birdPoop;
  public static Camera cam;
  private BufferedImage image;
  private BufferedImage imageLevel1;
  private BufferedImage imageLevel2;
  private BufferedImage imageLevel3;
  private BufferedImage imageLevel4;
  private BufferedImage imageIntro;
  private BufferedImage imageGameOver;
  private BufferedImage imageMenu1;
  private BufferedImage imageMenu2;
  private BufferedImage imageMenu3;
  private BufferedImage imageCredits;
  private BufferedImage imageLevels;
  private BufferedImage imageNextLevel;
  private BufferedImage imageGameWon;
  private BufferedImage background;
  
  
  public static Sprite coin;
  public static int coins=0;
  public static Sprite life;
  public static int lives=3;
  public static int levels=4;
  
  
  public static boolean showDeathScreen = false;
  public static int deathScreenTime = 0;
  public static boolean startGame=false;
  
  public static int gameStatus=0; // 0 = Intro , 1 = Menu , 2 = Game , 38 = credits , 3=Levels , 4 = Next Level , 5= Game won
  public static boolean showIntro = true;
  public static int introTime = 0;
  public static boolean showNextLevel = false;
  public static int nextLevelTime = 0;
  public static boolean showWon = false;
  public static int wonTime = 0;
  
  public static int menuItem = 1;
  public static boolean menuMoveDown=false;
  public static boolean menuMoveUp=false;
  public static boolean menuChoose=false;
  
  public static int levelItem = 1;
  public static boolean levelMoveDown=false;
  public static boolean levelMoveUp=false;
  public static boolean levelChoose=false;
  public static int countSeagullShot=0;
  
  
  public String introWav;
  
  
//  public static ArrayList<Birdshit> birdshit;
  
  
  private synchronized void start()
  {
    System.out.println("Game started");
    if (this.running) {
      return;
    }
    this.running = true;
    this.thread = new Thread(this, "Thread");
    this.thread.start();
  }
  
  private synchronized void stop()
  {
    if (!this.running)
    {
      System.out.println("Game already running");
      return;
    }
    this.running = false;
    try
    {
      this.thread.join();
    }
    catch (InterruptedException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void init()
  {
      
//    birdshit = new ArrayList<Birdshit>();  
      
      
    introWav = "cc-start.wav" ;
    cam = new Camera();
    
    sheet = new SpriteSheet("/spritesheet.png");
    sheetbig = new SpriteSheetBig("/spritesheetbig.png");
    handler = new Handler(cam);
    
    addKeyListener(new KeyInput());
    
    grass = new Sprite(sheet, 2, 1);
    player = new SpriteBig[14];
    mushroom = new Sprite(sheet, 3, 1);
    goomba = new Sprite[8];
    seagull = new Sprite[8];
    powerUp = new Sprite(sheet, 4, 1);
    usedPowerUp = new Sprite(sheet, 5, 1);
    birdPoop = new Sprite [2];
    
    
    coin = new Sprite(sheet,6,1);
    life = new Sprite(sheet,1,16);
    
    
    portal = new Sprite(sheet, 7, 1);
    lava = new Sprite(sheet, 8, 1);
    
    
    for (int i = 0; i < player.length; i++) {
      player[i] = new SpriteBig(sheetbig, i + 1, 13);
    }
    for (int i = 0; i < goomba.length; i++) {
      goomba[i] = new Sprite(sheet, i + 1, 15);
    }
    
    for (int i = 0; i < birdPoop.length; i++) {
      birdPoop[i] = new Sprite(sheet, i + 1, 3);
    }
    
    for (int i = 0; i < seagull.length; i++) {
      seagull[i] = new Sprite(sheet, i + 1, 14);
    }
    
    try
    {
      this.imageLevel1 = ImageIO.read(getClass().getResource("/level1.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    try
    {
      this.imageLevel2 = ImageIO.read(getClass().getResource("/level2.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try
    {
      this.imageLevel3 = ImageIO.read(getClass().getResource("/level3.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try
    {
      this.imageLevel4 = ImageIO.read(getClass().getResource("/level4.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    
    try
    {
      this.imageGameOver = ImageIO.read(getClass().getResource("/SuperHairyJoe_gameover.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    
    try
    {
      this.imageIntro = ImageIO.read(getClass().getResource("/SuperHairyJoe_logo.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    try
    {
      this.imageMenu1 = ImageIO.read(getClass().getResource("/SuperHairyJoe_menu2.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    try
    {
      this.imageMenu2 = ImageIO.read(getClass().getResource("/SuperHairyJoe_menu3.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try
    {
      this.imageMenu3 = ImageIO.read(getClass().getResource("/SuperHairyJoe_menu4.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try
    {
      this.imageCredits = ImageIO.read(getClass().getResource("/SuperHairyJoe_credits2.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try
    {
      this.imageLevels = ImageIO.read(getClass().getResource("/SuperHairyJoe_levels.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    try
    {
      this.imageNextLevel = ImageIO.read(getClass().getResource("/SuperHairyJoe_nextlevel.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    try
    {
      this.imageGameWon = ImageIO.read(getClass().getResource("/SuperHairyJoe_gamewon.png"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try
    {
      this.background = ImageIO.read(getClass().getResource("/vives.PNG"));
    }
    catch (IOException ex)
    {
      Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    if(gameStatus==0){
        if (lives!=0)
        {
            Sound.play(introWav); 
        }
    }
    if(gameStatus==2){
        
        
            Sound.play("pause.wav"); 
        if(levelItem==1) this.image = this.imageLevel1;
        if(levelItem==2) this.image = this.imageLevel2;
        if(levelItem==3) this.image = this.imageLevel3;
        if(levelItem==4) this.image = this.imageLevel4;
        
        
         handler.createLevel(this.image);
    }
    
      if(gameStatus==38){
        
        Sound.play("credits.wav"); 
    }  
    
            
    
    
    
  }
  
  public void run()
  {
    init();
    requestFocus();
    
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    double delta = 0.0D;
    double ns = 1.6666666666666666E7D;
    int frames = 0;
    int ticks = 0;
    while (this.running)
    {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1.0D)
      {
        tick();
        ticks++;
        delta -= 1.0D;
      }
      render();
      frames++;
      if (System.currentTimeMillis() - timer > 1000L)
      {
        timer += 1000L;
        System.out.println("Frames/s : " + frames + "    Ticks/s : " + ticks);
        frames = 0;
        ticks = 0;
      }
    }
  }
  
  public int getFrameWidth()
  {
    return WIDTH * 1;
  }
  
  public int getFrameHeight()
  {
    return HEIGHT * 1;
  }
  
  public void render()
  {
      
      
      
      
      
    BufferStrategy bs = getBufferStrategy();
    if (bs == null)
    {
      createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());
    
    
    
    
    //intro
     if(gameStatus==0){
        if(lives==0)
        {
            //gameover
            g.drawImage(imageGameOver, (WIDTH - imageGameOver.getWidth())  /2, (HEIGHT - imageGameOver.getHeight())  /2 , this);
            levelItem=1;
        }
        else
        {
                g.drawImage(imageIntro, (WIDTH - imageIntro.getWidth())  /2, (HEIGHT - imageIntro.getHeight())  /2, this);
        }
    }           
  
        //Menu
         if(gameStatus==1){

            if(menuItem==1)
            {
                g.drawImage(imageMenu1, (WIDTH - imageMenu1.getWidth())  /2, (HEIGHT - imageMenu1.getHeight())  /2 , this);

            }  

            if(menuItem==2)
            {
                g.drawImage(imageMenu2, (WIDTH - imageMenu2.getWidth())  /2, (HEIGHT - imageMenu2.getHeight())  /2 , this);

            }   

            if(menuItem==3)
            {
                g.drawImage(imageMenu3, (WIDTH - imageMenu3.getWidth())  /2, (HEIGHT - imageMenu3.getHeight())  /2 , this);

            }     
        }
       
     
     
     
    
    //Playing the game
    if(gameStatus==2){
        
        if(levelItem==4) g.drawImage(background,0,0,getWidth(),getHeight(),null);
        
        
        //Draw the score on the screen 
        //Must be before translate & handler.render , so i doesnt move with the camera
        g.drawImage(Game.coin.getBufferedImage(),5,5, 75,75,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD , 25));
        g.drawString("x " + coins, 80,50);

         //Draw the nr of lives on the screen
        //Must be before translate & handler.render , so i doesnt move with the camera
        g.drawImage(Game.life.getBufferedImage(),5,80, 75,75,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD , 25));
        g.drawString("x " + lives, 80,125);   
        
        
               if(showDeathScreen) 
                {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Courier", Font.BOLD , 100));
                    g.drawString("You died", WIDTH /2 - (2*100),HEIGHT /2); 
                    g.setColor(Color.RED);
                    g.setFont(new Font("Courier", Font.BOLD , 100));
                    g.drawString("You died", (WIDTH /2) - (2*100) -3 ,(HEIGHT /2) +3); 
                } 
               else
               {
               }
        
        
        
    }
    
    
    
    
    
    // Credits
    if(gameStatus==3){
        g.drawImage(imageLevels, (WIDTH - imageLevels.getWidth())  /2, (HEIGHT - imageLevels.getHeight())  /2 , this); 
        
        
        
                g.setColor(Color.WHITE);
                g.setFont(new Font("Courier", Font.BOLD , 100));
                g.drawString("<<< " + levelItem + " >>>", (WIDTH /2) - (2*100),(HEIGHT /3)*2); 
                g.setColor(Color.BLUE);
                g.setFont(new Font("Courier", Font.BOLD , 100));
                g.drawString("<<< " + levelItem + " >>>", (WIDTH /2) - (2*100) -3 ,((HEIGHT /3)*2) +3); 
        
        
    }
    
    
    
    
    
     
    // next level
    if(gameStatus==4){
        g.drawImage(imageNextLevel, (WIDTH - imageNextLevel.getWidth())  /2, (HEIGHT - imageNextLevel.getHeight())  /2 , this);  
                g.setColor(Color.WHITE);
                g.setFont(new Font("Comic", Font.BOLD , 100));
                g.drawString("Next level : " + (levelItem+1), (WIDTH /2) - (2*125),(HEIGHT /4)*2); 
                g.setColor(Color.RED);
                g.setFont(new Font("Comic", Font.BOLD , 100));
                g.drawString("Next level : " + (levelItem+1) + "", (WIDTH /2) - (2*125) -3 ,((HEIGHT /4)*2) +3); 
                if(levelItem+1==4)
                {
                    
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Comic", Font.BOLD , 100));
                    g.drawString("Trippy Level", (WIDTH /2) - (2*125),((HEIGHT /4)*2)+100); 
                    g.setColor(Color.ORANGE);
                    g.setFont(new Font("Comic", Font.BOLD , 100));
                    g.drawString("Trippy Level", (WIDTH /2) - (2*125) -3 ,((HEIGHT /4)*2) +103); 
                    g.setColor(Color.GREEN);
                    g.setFont(new Font("Comic", Font.BOLD , 40));
                    g.drawString("by Dylan Lejeune", (WIDTH /2) - (2*45),((HEIGHT /4)*2)+180); 
                    g.setColor(Color.BLUE);
                    g.setFont(new Font("Comic", Font.BOLD , 40));
                    g.drawString("by Dylan Lejeune", (WIDTH /2) - (2*45) -3 ,((HEIGHT /4)*2) +183); 
                }
    }
    
       
    // game won
    if(gameStatus==5){
        g.drawImage(imageGameWon, (WIDTH - imageGameWon.getWidth())  /2, (HEIGHT - imageGameWon.getHeight())  /2 , this);  
                  
    }
    
    
     
    // Credits
    if(gameStatus==38){
        g.drawImage(imageCredits, (WIDTH - imageCredits.getWidth())  /2, (HEIGHT - imageCredits.getHeight())  /2 , this);   
    }
    
    
    
    
    
    
    
    
    
    
    
    
    g.translate(cam.getX(), cam.getY());
    
    handler.render(g);
    
    g.dispose();

    bs.show();

  }
  
  public void tick()
  {
    handler.tick();
     //show the intro
     if(showIntro) 
     {
         introTime++;
         System.out.println(""+introTime);
     }

     if (introTime >=300)
     {
         
         gameStatus=1;
         showIntro=false;
         introTime=0;
         
         
         handler.clearLevel();
         System.out.println("end intro");
         gameStatus=1;
         init();
         
     }  
    
    
     //deathscreen
     if(showDeathScreen) 
     {
          
         deathScreenTime++;
         
     }
     if (deathScreenTime >=250)
     {
         
         System.out.println("dst " + deathScreenTime);
         showDeathScreen = false;
         deathScreenTime=0;
         handler.clearLevel();
         init();
         System.out.println("end ds");
         
         if (lives==0 && gameStatus==0)
         {
             lives=3;
         }
         

         
         
     }
 
     
     
          //show next level
     if(showNextLevel) 
     {
         nextLevelTime++;
         System.out.println(""+nextLevelTime);
     }

     if (nextLevelTime >=260)
     {
         
         gameStatus=2;
         showNextLevel=false;
         handler.clearLevel();
         nextLevelTime=0;
         
         
         handler.clearLevel();
         System.out.println("end nxt");
         levelMoveUp=true;
         levelChoose=true;
         init();
         
     }  
     
     
          //show the intro
     if(showWon) 
     {
         wonTime++;
         System.out.println(""+wonTime);
     }

     if (wonTime >=380)
     {
         
         gameStatus=1;
         showWon=false;
         wonTime=0;
         
         
         handler.clearLevel();
         System.out.println("end won");
         gameStatus=1;
         levelItem=1;
         init();
         
     }  
  
     
     
     
     
     
      //updating Menu screen  
      if (menuMoveDown)
      {
          countUp();
      }
      if (menuMoveUp)
      {
          countDown();
      }
      if (menuChoose)
      {
          chooseMenuItem();
      }
      //updating Levels screen
      if (levelMoveUp)
      {
          levelUp();
      }
      if (levelMoveDown)
      {
          levelDown();
      }
      if (levelChoose)
      {
          levelMenuItem();
      }
     
    
    for (GameObject gameObject : handler.gameObjects) {
      if ((gameObject instanceof Player))
      {
        cam.tick(gameObject);
        break;
      }
      
//      for (int i = 0; i < birdshit.size(); i++)
//      {
//          boolean remove = birdshit.get(i).update();
//          if(remove)
//          {
//              birdshit.remove(i);
//              i--;
//          }
//      }
      

     
    }
  
    
  }
  
  public void countUp()
  {
      if (menuMoveDown)
      {
        menuItem +=1;
        menuMoveDown=false;
        if (menuItem > 3) menuItem = 3;
      }
  }
  
  public void countDown()
  {      
      if (menuMoveUp)
      {
        menuItem -=1;
        menuMoveUp=false;
        if (menuItem <1) menuItem = 1;
      }
  } 
  
  public void chooseMenuItem()
  {      
      if (menuChoose)
      {
        menuChoose=false;
        if (menuItem ==1) gameStatus = 2; //Play
        if (menuItem ==2) gameStatus = 3; // Levels
        if (menuItem ==3) // Credits
        {
            gameStatus = 38;
            
        } 
        init();
      }
  } 
  
  public void levelDown()
  {
      if (levelMoveDown)
      {
        levelItem -=1;
        levelMoveDown=false;
        if (levelItem <1) levelItem = 1;
      }
  }
  
  public void levelUp()
  {      
      if (levelMoveUp)
      {
        levelItem +=1;
        levelMoveUp=false;
        if (levelItem >levels) levelItem = levels;
      }
  } 
  
  public void levelMenuItem()
  {      
      if (levelChoose)
      {
          if(levelItem>0 & levelItem<= levels )
          {
            levelChoose=false;
            gameStatus=2;
            //if (levelItem ==1) gameStatus = 2; //Play
            //if (levelItem ==2) gameStatus = 3; // Levels
            //if (levelItem ==3) gameStatus = 38; // Credits
            init();
          }
      }
  } 
  
  
  public Game()
  {
    Dimension size = new Dimension(WIDTH * 1, HEIGHT * 1);
    setPreferredSize(size);
    setMaximumSize(size);
    setMinimumSize(size);
  }
  
  public static void main(String[] args)
  {
    Game game = new Game();
    JFrame frame = new JFrame("Super Hairy Joe");
    frame.add(game);
    frame.pack();
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(3);
    frame.setVisible(true);
    
    game.start();
  }
}