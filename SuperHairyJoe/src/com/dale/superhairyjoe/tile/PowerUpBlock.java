package com.dale.superhairyjoe.tile;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.entity.powerup.Mushroom;
import com.dale.superhairyjoe.gfx.Sprite;
import java.awt.Graphics;

public class PowerUpBlock
  extends Tile
{
  private Sprite powerUp;
  private boolean poppedUp = false;
  private int spriteY = getY();
  
  public PowerUpBlock(int x, int y, int width, int height, Handler handler, Sprite powerUp)
  {
    super(x, y, width, height, true, handler);
    this.powerUp = powerUp;
  }
  
  public void render(Graphics g)
  {
    if (!this.poppedUp) {
      g.drawImage(this.powerUp.getBufferedImage(), this.x, this.spriteY, this.width, this.height, null);
    }
    if (!this.activated) {
      g.drawImage(Game.powerUp.getBufferedImage(), this.x, this.y, this.width, this.height, null);
    } else {
      g.drawImage(Game.usedPowerUp.getBufferedImage(), this.x, this.y, this.width, this.height, null);
    }
  }
  
  public void tick()
  {
    if ((this.activated) && (!this.poppedUp))
    {
      this.spriteY -= 1;
      if (this.spriteY <= this.y - this.height)
      {
        this.handler.addGameObject(new Mushroom(this.x, this.spriteY, this.width, this.height, this.handler));
        this.poppedUp = true;
      }
    }
  }
}
