package com.dale.superhairyjoe.entity;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.gfx.Sprite;
import com.dale.superhairyjoe.tile.Wall;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy
  extends Entity
{
  public Enemy(int x, int y, int width, int height, Handler handler)
  {
    super(x, y, width, height, handler);
    this.speed = 3;
  }
  
  public void render(Graphics g)
  {
    g.drawImage(Game.mushroom.getBufferedImage(), this.x, this.y, this.width, this.height, null);
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
        if (getBoundsBottom().intersects(gameObject.getBounds()))
        {
          setvelY(0);
          if (this.falling) {
            this.falling = false;
          }
        }
        else if (!this.falling)
        {
          this.gravity = 0.8D;
          this.falling = true;
        }
        if ((getBoundsLeft().intersects(gameObject.getBounds())) || (getBoundsRight().intersects(gameObject.getBounds()))) {
          turn();
        }
      }
    }
    if (this.falling)
    {
      this.gravity += 0.1D;
      setvelY((int)this.gravity);
    }
  }
}
