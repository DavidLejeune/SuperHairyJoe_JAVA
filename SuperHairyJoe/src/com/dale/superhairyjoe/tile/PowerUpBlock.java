/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.tile;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.Id;
import com.dale.superhairyjoe.entity.powerup.Mushroom;
import com.dale.superhairyjoe.gfx.Sprite;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class PowerUpBlock extends Tile {

    
    private Sprite powerUp;
    private boolean poppedUp = false;
    
    private int spriteY = getY();
    
    public PowerUpBlock(int x, int y, int width, int height, boolean solid, Id id, Handler handler, Sprite powerUp) {
        super(x, y, width, height, solid, id, handler);
    
        this.powerUp = powerUp;
    
    }
    
    @Override
    public void render(Graphics g)
    {
        
        
        if(!poppedUp)
        {
            g.drawImage(powerUp.getBufferedImage(),x,spriteY,width,height,null);
        }
        
        if(!activated)
        {
            g.drawImage(Game.powerUp.getBufferedImage(),x,y,width,height,null);
        }
        else
        {
            g.drawImage(Game.usedPowerUp.getBufferedImage(),x,y,width,height,null);
        }
    }
    
    @Override
    public void tick()
    {
        if(activated && !poppedUp)
        {
            spriteY--;
            if(spriteY<=y-height)
            {
                handler.addEntity(new Mushroom(x,spriteY,width,height,Id.mushroom,handler));
                poppedUp = true;
            }
        }
        
    }
    
}
