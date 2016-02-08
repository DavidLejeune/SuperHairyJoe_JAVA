/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairjoe.tile;

import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.Id;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Wall extends Tile{

    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }
    
    

    public void render(Graphics g) 
    {
        g.setColor(Color.RED);
        g.fillRect(x,y,width, height);
    }

    public void tick() 
    {
        
    }
    
}
