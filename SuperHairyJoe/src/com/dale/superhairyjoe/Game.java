package com.dale.superhairyjoe;

import com.dale.superhairyjoe.entity.GameObject;
import com.dale.superhairyjoe.entity.mob.Player;
import com.dale.superhairyjoe.gfx.Sprite;
import com.dale.superhairyjoe.gfx.SpriteSheet;
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
  public static Sprite grass;
  public static Sprite[] player = new Sprite[8];
  public static Sprite mushroom;
  public static Sprite[] goomba;
  public static Sprite[] seagull;
  public static Sprite powerUp;
  public static Sprite usedPowerUp;
  public static Camera cam;
  private BufferedImage image;
  private BufferedImage imageIntro;
  
  
  public static Sprite coin;
  public static int coins=0;
  public static Sprite life;
  public static int lives=1;
  
  
  public static boolean showDeathScreen = false;
  public static int deathScreenTime = 0;
  public static boolean startGame=false;
  
  public static int gameStatus=0; // 0 = Intro , 1 = Menu , 2 = Game 
  public static boolean showIntro = true;
  public static int introTime = 0;
  
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
    handler = new Handler(cam);
    
    addKeyListener(new KeyInput());
    
    grass = new Sprite(sheet, 2, 1);
    player = new Sprite[8];
    mushroom = new Sprite(sheet, 3, 1);
    goomba = new Sprite[8];
    seagull = new Sprite[8];
    powerUp = new Sprite(sheet, 4, 1);
    usedPowerUp = new Sprite(sheet, 5, 1);
    
    
    coin = new Sprite(sheet,6,1);
    life = new Sprite(sheet,1,16);
    
    
    for (int i = 0; i < player.length; i++) {
      player[i] = new Sprite(sheet, i + 1, 16);
    }
    for (int i = 0; i < goomba.length; i++) {
      goomba[i] = new Sprite(sheet, i + 1, 15);
    }
    for (int i = 0; i < seagull.length; i++) {
      seagull[i] = new Sprite(sheet, i + 1, 14);
    }
    
    try
    {
      this.image = ImageIO.read(getClass().getResource("/level.png"));
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
    if(gameStatus==0){
        Sound.play(introWav); 
    }
    if(gameStatus==2){
        
    handler.createLevel(this.image);
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
    
    
    
    
    
     if(gameStatus==0){
        //g.drawImage(Game.coin.getBufferedImage(),5,5, 75,75,null);
        g.drawString("Super Hairy Joe", 80,50);
        g.drawImage(imageIntro, (WIDTH - imageIntro.getWidth())  /2, (HEIGHT - imageIntro.getHeight())  /2, this);
   
    }   
    
    
    
    if(gameStatus==2){
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
         gameStatus=2;
         showIntro=false;
         introTime=0;
         
         
         handler.clearLevel();
         System.out.println("end intro");
         gameStatus=2;
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
         
         if (lives==0)
         {
             gameStatus=0;
             lives=3;
         }
         

         
         
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