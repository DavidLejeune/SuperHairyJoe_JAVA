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
import com.dale.superhairyjoe.entity.mob.Goomba;
import com.dale.superhairyjoe.entity.mob.Seagull;
import com.dale.superhairyjoe.sounds.Sound;
import com.dale.superhairyjoe.tile.Wall;
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
            if ((gameObject instanceof Wall))
            {
              if ((getBounds().intersects(gameObject.getBounds()))) 
              {
                die();
                gameObject.die();


              }

            }
            if ((gameObject instanceof Goomba) )
            {
              if ((getBounds().intersects(gameObject.getBounds()))) 
              {
                die();
                gameObject.die();

                Sound.play("ow.wav"); 

              }

            }
             if ((gameObject instanceof Seagull) )
            {
              if ((getBounds().intersects(gameObject.getBounds()))) 
              {
                Game.countSeagullShot++;
                  System.out.println("" + Game.countSeagullShot);
                die();
                gameObject.die();
                if (Game.countSeagullShot % 2==0)
                {
                    
                    Sound.play("someoneshot.wav");
                }
                    
                else
                {
                    Sound.play("dontshootfood.wav");
                    
                }
                    


              }

            }           
        }
        }
    
}
