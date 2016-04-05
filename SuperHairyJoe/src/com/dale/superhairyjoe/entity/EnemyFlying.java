package com.dale.superhairyjoe.entity;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.gfx.Sprite;
import com.dale.superhairyjoe.tile.Wall;
import java.awt.Graphics;
import java.awt.Rectangle;

// Enemy that can fly , same as normal enemy but no gravity



public class EnemyFlying
  extends Entity
{
  public EnemyFlying(int x, int y, int width, int height, Handler handler)
  {
    super(x, y, width, height, handler);
    this.speed = 3;
  }
  
  public void render(Graphics g)
  {
  }
  
  public void tick()
  {
    super.tick();
    for (GameObject gameObject : this.handler.getNearObjects(this))
    {
      if (!gameObject.solid) {
        break;
      }
      if ((gameObject instanceof Wall))
      {
        // this enemy just needs to turn when it hits a wall  
        if ((getBoundsLeft().intersects(gameObject.getBounds())) || (getBoundsRight().intersects(gameObject.getBounds()))) {
          turn();
        }
      }
    }
  }
}
