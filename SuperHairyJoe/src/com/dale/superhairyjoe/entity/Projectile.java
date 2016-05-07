/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.entity;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.tile.Wall;
import java.awt.Graphics;

/**
 *
 * @author Milan
 */
public class Projectile extends Entity{

    public Projectile(int x, int y, int width, int height, Handler handler, Direction direction) {
        super(x, y, width, height, handler);
        
        switch(direction){
        
            case LEFT: setvelX(-8);
                break;
            case RIGHT: setvelX(8);
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.coin.getBufferedImage(), this.x, this.y, this.width, this.height, null);
    }
    
    public void tick() {
    
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
          die();
        }
//        if ((getBoundsBottom().intersects(gameObject.getBounds()))){
//            jumping = true;
//            falling = false;
//            gravity = 4.0;
//        } else if(!falling&&!jumping){
//            falling = true;
//            gravity = 1.0;
//        }
      }
    }
        
    }
    
}
