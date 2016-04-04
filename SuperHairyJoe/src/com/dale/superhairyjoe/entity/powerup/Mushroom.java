package com.dale.superhairyjoe.entity.powerup;

import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.entity.Enemy;

public class Mushroom
  extends Enemy
{
  public Mushroom(int x, int y, int width, int height, Handler handler)
  {
    super(x, y, width, height, handler);
    this.speed = 3;
  }
}
