/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe;

import java.awt.Canvas;
import java.awt.Dimension;

/**
 *
 * @author David
 * @about this is the Main class for the game
 * 
 */


public class Game extends Canvas
{
    
    public static final int WIDTH = 270;
    public static final int HEIGHT = WIDTH / 14 * 10;
    public static final int SCALE = 4;
    
    public Game()
    {
        //Setting the dimension of the game
        Dimension size = new Dimension(WIDTH * SCALE , HEIGHT * SCALE);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        
        
    }
    
}
