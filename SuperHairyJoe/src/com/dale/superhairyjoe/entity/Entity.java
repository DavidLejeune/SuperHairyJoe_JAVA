/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.entity;

import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Entity {
    
    public int x, y;
    public int width, height;
    
    public boolean solid;
    
    public int velX, velY;
    
    public Entity(int x, int y, int width, int height, boolean solid)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        
    }
    
    public void render(Graphics g)
    {
        
    }
    
    public void tick()
    {
        x += velX;
        y += velY;
    }
    
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
    
}
