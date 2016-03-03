/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe;

import com.dale.superhairyjoe.entity.Entity;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.dale.superhairyjoe.entity.Player;
import com.dale.superhairyjoe.gfx.Sprite;
import com.dale.superhairyjoe.gfx.SpriteSheet;
import com.dale.superhairyjoe.input.KeyInput;

/**
 *
 * @author David
 * @about this is the Main class for the game
 * new source location skydrive/dale/superhairyjoe_java
 * 
 */


public class Game extends Canvas implements Runnable 
{
    
    public static final int WIDTH = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    public static final int HEIGHT = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height - 50;
    public static final int SCALE = 1;
    public static final String TITLE = "Super Hairy Joe";
    public static final int scale1 = 1;
    private Color randomColor;
    
    private Thread thread;
    public boolean running = false;
    public static Handler handler;
    public static SpriteSheet sheet;
    public static Sprite grass;
    public static Sprite player[] = new Sprite[8];
    public static Camera cam;
    
    private synchronized void start()
    {
        System.out.println("Game started");
        if(running) 
        {
            return;
        }
        else
        {
            running = true;
            thread = new Thread(this, "Thread");
            thread.start();
        }
        
    }
    
    private synchronized void stop()
    {
 
        if(!running) 
        {
            System.out.println("Game already running");
            return;
        }
        else
        {
            running = false;
            try 
            { 
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }    
    }
    
    public void init()
    {
        cam = new Camera();
        
    	sheet = new SpriteSheet("/spritesheet.png");
        handler = new Handler();
        
        addKeyListener(new KeyInput());
        
        grass = new Sprite(sheet,2,1);
        for(int i = 0;i<player.length;i++)
        {
            player[i]= new Sprite(sheet, i+1,16);
        }
        
        
        handler.addEntity(new Player(200,400,64 * scale1 ,64 * scale1,true,Id.player,handler));
        //handler.addTile(new Wall(200,200,64,64,true,Id.wall, handler));
    }
    
    
    public void run() 
    {
        init();
        requestFocus();
        
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
        double ns = 1000000000.0/60.0;
        int frames = 0;
        int ticks = 0;
        
        
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns ;
            lastTime = now;
            
            while(delta>=1)
            {
                tick();
                ticks++;
                delta--;
            }
            
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("Frames/s : " + frames + "    Ticks/s : " + ticks );
                frames = 0;
                ticks = 0;
            }
            
        }
    }
    
    
    public int getFrameWidth()
    {
        return WIDTH * SCALE;
    }
    
    public int getFrameHeight()
    {
        return HEIGHT * SCALE;
    }
    
    
    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs==null)
        {
            createBufferStrategy(3);
            return;
        }
        

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        //g.setColor(new Color(56,111,222));
        g.fillRect(0, 0, getWidth(), getHeight());
        //g.setColor(Color.RED);
        //g.fillRect(200, 200, getWidth() - 400 , getHeight() - 400);
          
        
        g.translate(cam.getX(), cam.getY());
        
        handler.render(g);
        
        g.dispose();
        bs.show();
        
    }
    
    public void tick()
    {
        handler.tick();
        
        for(Entity e:handler.entity)
        {
            if(e.getId()==Id.player)
            {
                cam.tick(e);
            }
        }
        
    }
    
    
    public Game()
    {
        //Setting the dimension of the game
        Dimension size = new Dimension(WIDTH * SCALE , HEIGHT  * SCALE );
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        
        
    }
    
    
    public static void main(String[] args)
    {
        //Initialise this puppy
        Game game = new Game();
        JFrame frame = new JFrame(TITLE);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    
        
        
        game.start();
        
        
        
    }

    
    
}
