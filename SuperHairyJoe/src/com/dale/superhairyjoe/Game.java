/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author David
 * @about this is the Main class for the game
 * 
 */


public class Game extends Canvas implements Runnable 
{
    
    public static final int WIDTH = 270;
    public static final int HEIGHT = WIDTH / 14 * 10;
    public static final int SCALE = 4;
    public static final String TITLE = "Super Hairy Joe";
    
    private Thread thread;
    public boolean running = false;
    
    public synchronized void start()
    {
        System.out.println("Game started");
    }
    
    public synchronized void stop()
    {
        
    }
    
    
    public void run() 
    {
   
    }
    
    
    
    public Game()
    {
        //Setting the dimension of the game
        Dimension size = new Dimension(WIDTH * SCALE , HEIGHT * SCALE);
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
