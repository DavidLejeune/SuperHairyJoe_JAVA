/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.tile;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Coin
  extends Tile
{
  public Coin(int x, int y, int width, int height, Handler handler)
  {
    super(x, y, width, height, true, handler);
  }
  
  public void render(Graphics g)
  {
    g.drawImage(Game.coin.getBufferedImage(), this.x, this.y, this.width, this.height, null);
  }
  
  public void tick() {}
}
