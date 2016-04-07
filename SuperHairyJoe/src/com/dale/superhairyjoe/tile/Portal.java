package com.dale.superhairyjoe.tile;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.gfx.Sprite;
import java.awt.Graphics;

public class Portal
  extends Tile
{
  public Portal(int x, int y, int width, int height, Handler handler)
  {
    super(x, y, width, height, true, handler);
  }
  
  public void render(Graphics g)
  {
    g.drawImage(Game.portal.getBufferedImage(), this.x, this.y, this.width, this.height, null);
  }
  
  public void tick() {}
}
