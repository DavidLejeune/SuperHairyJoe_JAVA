package com.dale.superhairyjoe.tile;

import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.entity.GameObject;
import java.awt.Graphics;

public abstract class Tile
  extends GameObject
{
  public boolean activated = false;
  
  public Tile(int x, int y, int width, int height, boolean solid, Handler handler)
  {
    super(x, y, width, height, solid, handler);
  }
  
  public abstract void render(Graphics paramGraphics);
  
  public abstract void tick();
}
