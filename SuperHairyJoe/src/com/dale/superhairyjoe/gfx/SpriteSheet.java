/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class SpriteSheet {
    
    private BufferedImage sheet;
    
    public SpriteSheet(String path)
    {
        try
        {
        sheet = ImageIO.read(getClass().getResource(path));
                
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }        
    }
    
    
    public BufferedImage getSprite(int x, int y)
    {
        return sheet.getSubimage(x*32-32, y*32-32, 32,32);
    }
    
}
