/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.entity.mob;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.Id;
import com.dale.superhairyjoe.entity.Entity;
import com.dale.superhairyjoe.tile.Tile;
import java.awt.Graphics;
import com.dale.superhairyjoe.entity.mob.Player;
import java.util.Random;

/**
 *
 * @author David
 */
public class Goomba extends Entity{
    
    
    
    private Random random = new Random();

    public Goomba(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);
        
                int dir = random.nextInt(2);
        
        switch(dir)
                {
                        case 0:
                                setvelX(-3);
                                facing = 0;
                                break;
                        case 1:
                                setvelX(3);
                                facing = 1;
                                break;
                }
        
        
    }
    
    
    

    
    @Override
      public void render(Graphics g)
    {
        if(facing==0)
        {
      	g.drawImage(Game.goomba[frame+4].getBufferedImage(), x, y, width, height, null);
    	          
        }
        else if(facing==1)
        {
      	g.drawImage(Game.goomba[frame].getBufferedImage(), x, y, width, height, null);
    	          
        }   
        
        
        
    }
    //@Override
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
                    facing = 1;
                }
                if(getBoundsRight().intersects(t.getBounds()))
                {
                    setvelX(-3);
                    facing = 0;
                }
            }
        }
        
        
        
        if (falling)
        {
            gravity+=0.1;
            setvelY((int) gravity);
            
        }
        
        if(velX!=0)
        {
            frameDelay++;
            if(frameDelay>=10)
            {
                frame++;
                if(frame>3)
                {
                    frame=0;
                }
                frameDelay=0;
            }
        }
        
        
    }
}
