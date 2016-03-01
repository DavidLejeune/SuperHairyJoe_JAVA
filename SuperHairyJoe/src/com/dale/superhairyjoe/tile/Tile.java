/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.tile;

import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.Id;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author David
 */
public abstract class Tile {
    
    public int x, y;
    public int width, height;
    
    public boolean solid;
    
    public Id id;
    
    public int velX, velY;
    
    public Handler handler;
    
    public Tile(int x, int y, int width, int height, boolean solid,Id id , Handler handler)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;    
        this.handler = handler;     
        
    }
    
    public void die()
    {
        handler.removeTile(this);
    }
    
    public abstract void render(Graphics g);
    public abstract void tick();
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    } 
   
     
  
    public void setvelX(int velX)
    {
        this.velX = velX;
    }    
    
    public void setvelY(int velY)
    {
        this.velY = velY;
    } 
    
    
     public boolean isSolid()
    {
        return solid;
    }
    
    public void setSolid(boolean solid)
    {
        this.solid = solid;
    }
      
    public Id getId()
    {
        return id;
    }  
    
        public Rectangle getBounds()
    {
        return new Rectangle(getX(),getY(),width, height);
    }
    
}
