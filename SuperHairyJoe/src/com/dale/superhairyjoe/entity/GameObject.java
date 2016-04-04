package com.dale.superhairyjoe.entity;

import com.dale.superhairyjoe.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject
{
  public int x;
  public int y;
  public int width;
  public int height;
  public boolean solid;
  public Handler handler;
  public boolean activated = false;
  
  public GameObject(int x, int y, int width, int height, boolean solid, Handler handler)
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.solid = solid;
    this.handler = handler;
  }
  
  public void die()
  {
    this.handler.gameObjects.remove(this);
  }
  
  public abstract void render(Graphics paramGraphics);
  
  public abstract void tick();
  
  public int getX()
  {
    return this.x;
  }
  
  public void setX(int x)
  {
    this.x = x;
  }
  
  public int getY()
  {
    return this.y;
  }
  
  public void setY(int y)
  {
    this.y = y;
  }
  
  public boolean isSolid()
  {
    return this.solid;
  }
  
  public void setSolid(boolean solid)
  {
    this.solid = solid;
  }
  
  public Rectangle getBounds()
  {
    return new Rectangle(getX(), getY(), this.width, this.height);
  }
}
