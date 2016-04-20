package com.dale.superhairyjoe.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheetBig
{
  private BufferedImage sheet;
  
  public SpriteSheetBig(String path)
  {
    try
    {
      this.sheet = ImageIO.read(getClass().getResource(path));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public BufferedImage getSprite(int x, int y)
  {
    return this.sheet.getSubimage(x * 128 - 128, y * 128 - 128, 128, 128);
  }
}
