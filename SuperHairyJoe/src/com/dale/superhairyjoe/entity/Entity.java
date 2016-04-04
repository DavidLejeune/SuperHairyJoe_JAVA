package com.dale.superhairyjoe.entity;

import com.dale.superhairyjoe.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public abstract class Entity
  extends GameObject
{
  public static enum Direction
  {
    LEFT,  RIGHT;
    
    private Direction() {}
  }
  
  public boolean jumping = false;
  public boolean falling = true;
  protected Direction direction;
  protected int speed = 1;
  public int frame = 0;
  public int frameDelay = 0;
  public int velX;
  public int velY;
  public double gravity = 0.0D;
  
  public Entity(int x, int y, int width, int height, Handler handler)
  {
    super(x, y, width, height, true, handler);
    startWalking();
  }
  
  public void die()
  {
    this.handler.removeGameObject(this);
  }
  
  public abstract void render(Graphics paramGraphics);
  
  public void tick()
  {
    move();
  }
  
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
  
  public void setvelX(int velX)
  {
    this.velX = velX;
  }
  
  public void setvelY(int velY)
  {
    this.velY = velY;
  }
  
  public Rectangle getBounds()
  {
    return new Rectangle(getX(), getY(), this.width, this.height);
  }
  
  public Rectangle getBoundsTop()
  {
    return new Rectangle(getX() + 10, getY(), this.width - 20, 5);
  }
  
  public Rectangle getBoundsBottom()
  {
    return new Rectangle(getX() + 10, getY() + this.height - 5, this.width - 20, 5);
  }
  
  public Rectangle getBoundsLeft()
  {
    return new Rectangle(getX(), getY() + 10, 5, this.height - 20);
  }
  
  public Rectangle getBoundsRight()
  {
    return new Rectangle(getX() + this.width - 5, getY() + 10, 5, this.height - 20);
  }
  
  public void turnLeft()
  {
    setvelX(-this.speed);
    this.direction = Direction.LEFT;
  }
  
  public void turnRight()
  {
    setvelX(this.speed);
    this.direction = Direction.RIGHT;
  }
  
  public void turn()
  {
    this.velX = (-this.velX);
    this.direction = (this.direction == Direction.LEFT ? Direction.RIGHT : Direction.LEFT);
  }
  
  protected void startWalking()
  {
    Random random = new Random();
    this.direction = Direction.values()[((int)(Math.random() * Direction.values().length))];
    if (this.direction == Direction.LEFT) {
      turnLeft();
    } else {
      turnRight();
    }
  }
  
  protected void move()
  {
    this.x += this.velX;
    this.y += this.velY;
  }
}
