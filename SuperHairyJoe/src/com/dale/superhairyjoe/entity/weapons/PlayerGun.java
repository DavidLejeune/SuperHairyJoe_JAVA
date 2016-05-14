/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.entity.weapons;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.entity.GameObject;
import com.dale.superhairyjoe.entity.Projectile;
import com.dale.superhairyjoe.entity.mob.Seagull;
import java.awt.Graphics;

/**
 *
 * @author Milan
 */
public class PlayerGun
extends Projectile{
    
    public PlayerGun(int x, int y, int width, int height, Handler handler, Direction direction) {
        super(x, y, width, height, handler, direction);
        
        switch (direction) {

            case LEFT:
                setvelX(-8);
                break;
            case RIGHT:
                setvelX(8);
                break;
        }
    }
    
    public void render(Graphics g) {
        g.drawImage(Game.coin.getBufferedImage(), this.x, this.y, this.width, this.height, null);
    }
    
      public void tick()
        {
          super.tick();

              
            for (GameObject gameObject : this.handler.getNearObjects(this))
        {
            if (!gameObject.solid) {
              break;
            }
            if ((gameObject instanceof Seagull))
            {
              // this enemy just needs to turn when it hits a wall  
              if ((getBounds().intersects(gameObject.getBounds()))) 
              {
                die();
                gameObject.die();


              }

            }
        }
        }
    
}
