/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.entity;

import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.Id;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Player extends Entity{

    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }
    
    public void render(Graphics g)
    {
        
        
        g.setColor(Color.GRAY);
        g.drawRect(x , y  , width ,  height);
        
        g.setColor(Color.RED);
        g.fillRect(x + width /4 , y + height / 3 - 5 , width - 40, height - 40);
 
        g.setColor(Color.BLUE);
        g.fillRect(x + width /4 + 1, y + height / 3 + height - 40- 5, 10, 16);
        g.fillRect(x + width /4 + 13, y + height / 3 + height - 40- 5, 10, 16);
              
        g.setColor(Color.PINK);
        g.fillRect(x + width /4 + 1, y + height / 3 + height - 40 + 16- 5, 11, 6);
        g.fillRect(x + width /4 + 13, y + height / 3 + height - 40 + 16- 5, 11, 6);
              
           
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 6));
        g.drawString("VIVES", x + width /4 + 2 , y + height / 3 +6);
        
        
        g.setColor(Color.RED);
        g.fillRect(x + width /4 - 4 , y + height / 3 + 2 - 5, 5,  20);
        g.fillRect(x + width /4 - 4 + width - 40, y + height / 3 + 2 - 5, 5,  20);
        g.setColor(Color.ORANGE);
        g.drawRect(x + width /4 - 4 , y + height / 3 + 2 - 5, 5,  20);
        g.drawRect(x + width /4 - 4 + width - 40, y + height / 3 + 2 - 5, 5,  20);
        
        
        
        g.setColor(Color.WHITE);
        g.fillOval(x + width / 4 + 1, y  + 1,  width - 50, width - 50);
        g.setColor(Color.BLACK);
        g.fillOval(x + width / 4 + 6, y+5,  4, 4);
        g.fillOval(x + width / 4 + 12, y+5,  4, 4);
        g.setColor(Color.WHITE);
        g.fillOval(x + width / 4 + 8, y+6,  1, 1);
        g.fillOval(x + width / 4 + 14, y+6,  1, 1);
        
        
        g.setColor(Color.ORANGE);
        
        
        g.setColor(Color.WHITE);
        g.fillOval(x + width /4 - 5  , y + height / 3 + 2 +20 - 5, 7,  7);
        g.fillOval(x + width /4 - 5 + width - 40  , y + height / 3 + 2 +20 - 5, 7,  7);
        
        
             
        
    }
    
    public void tick()
    {
        
    }
    
    
}
