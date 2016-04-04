package com.dale.superhairyjoe;

import com.dale.superhairyjoe.entity.GameObject;

public class Camera
{
  public int x;
  public int y;
  
  public void tick(GameObject player)
  {
    setX(-player.getX() + Game.WIDTH / 2);
    setY(-player.getY() + Game.HEIGHT / 2);
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
}
