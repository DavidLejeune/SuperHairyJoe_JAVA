/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.entity.powerup;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.Id;
import com.dale.superhairyjoe.entity.Entity;
import com.dale.superhairyjoe.tile.Tile;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author David
 */
public class Mushroom extends Entity{
    
    
    
    private Random random = new Random();

    public Mushroom(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);
        
        int dir = random.nextInt(2);
        
        switch(dir)
                {
                        case 0:
                                setvelX(-3);
                                break;
                        case 1:
                                setvelX(3);
                                break;
                }
        
    }
    
    
    @Override
    public void render(Graphics g)
    {
        g.drawImage(Game.mushroom.getBufferedImage(),x,y,width,height,null);
        
    }
    @Override
    public void tick()
    {
        x+=velX;
        y+=velY;
                
        
        for(Tile t:handler.tile)
        {
            if(!t.solid) break;
            if(t.getId()==Id.wall)
            {
                if(getBoundsBottom().intersects(t.getBounds()))
                {
                    setvelY(0);
                    //y = t.getY() - t.height;
                    if(falling) falling=false;
                }
                else
                {
                    if(!falling)
                    {
                        gravity=0.8;
                        falling=true;
                    }
                }
                
                
                
                
                if(getBoundsLeft().intersects(t.getBounds()))
                { 
                    setvelX(3);
                }
                if(getBoundsRight().intersects(t.getBounds()))
                {
                    setvelX(-3);
                }
            }
        }
        
        
        
        if (falling)
        {
            gravity+=0.1;
            setvelY((int) gravity);
            
        }
        
        
    }
    
    
    
}
