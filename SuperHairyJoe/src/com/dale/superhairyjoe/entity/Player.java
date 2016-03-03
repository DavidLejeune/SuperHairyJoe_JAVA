/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.entity;

import com.dale.superhairyjoe.tile.Tile;
import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.Id;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Player extends Entity{


    private int frame = 0;
    private int frameDelay = 0;
    
    private boolean animate = false;
    
    
    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
        
        
        
        
        
        
    }
    
   
    public void tick()
    {
        x+=velX;
        y+=velY;
        
        //if (x<=0) x=0; removed to fall faster
        if (y<=0) y=0;
        //if (x + width >=Game.WIDTH * Game.SCALE) x=Game.WIDTH * Game.SCALE - width; removed to fall faster
        if (y + height >=Game.HEIGHT * Game.SCALE) y=Game.HEIGHT  * Game.SCALE - height;
        
        if (velX!=0) animate=true;
        else animate = false;
        
        for(Tile t:handler.tile)
        {
            if(!t.solid) break;
            if(t.getId()==Id.wall)
            {
                if(getBoundsTop().intersects(t.getBounds()))
                {
                    setvelY(0);
                    //y = t.getY() + t.height;
                    
                    if (jumping)
                    {
                        jumping=false;
                        gravity=0.8;
                        falling=true;
                    }
                    
                    
                } 
                if(getBoundsBottom().intersects(t.getBounds()))
                {
                    setvelY(0);
                    //y = t.getY() - t.height;
                    if(falling) falling=false;
                }
                else
                {
                    if(!falling && !jumping)
                    {
                        gravity=0.8;
                        falling=true;
                    }
                }
                
                
                
                
                if(getBoundsLeft().intersects(t.getBounds()))
                { 
                    setvelX(0);
                    x = t.getX() + t.width;
                }
                if(getBoundsRight().intersects(t.getBounds()))
                {
                    setvelX(0);
                    //x = t.getX() - t.width + 32 ;
                    x = t.getX() - t.width  ;
                }
            }
        }
        
        
        for (int i=0 ; i <handler.entity.size() ; i++)
        {
            Entity e = handler.entity.get(i);
            
            if (e.getId()==Id.mushroom)
            {
                //check if we are colliding with the mushroom
                if(getBounds().intersects(e.getBounds()))
                {
                    //to prevent teleportation
                    int tpX = getX();
                    int tpY = getY();
                    
                    
                    width*=2;
                    height*=2;
                    
                    setX(tpX - width);
                    setY(tpY - height);
                    
                    
                    e.die();
                }
            }
            
        }
        
        
        
        
        if (jumping)
           
        {
            gravity-=0.1;
            setvelY((int) -gravity);
            if(gravity<=0.0)
            {
                jumping = false;
                falling=true;
            }
        }
        if (falling)
        {
            gravity+=0.1;
            setvelY((int) gravity);
            
        }
        
        if(animate)
        {
            frameDelay++;
            if (frameDelay >= 3)
            {
                frame++;
                if (frame >= 4)
                {
                    frame=0;
                }
                frameDelay=0;
            }   
        }
        
        
    }
    
     public void render(Graphics g)
    {
        if(facing==0)
        {
      	g.drawImage(Game.player[frame+4].getBufferedImage(), x, y, width, height, null);
    	          
        }
        else if(facing==1)
        {
      	g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height, null);
    	          
        }   

    	 
        
    }
    
    
    
}
