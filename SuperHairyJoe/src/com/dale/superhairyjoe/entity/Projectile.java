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
public class Projectile extends Entity {

    public Projectile(int x, int y, int width, int height, Handler handler, Direction direction) {
        super(x, y, width, height, handler);

        }

    @Override
    public void render(Graphics g) {
    }

    public void tick() {

        super.tick();
<<<<<<< HEAD
        
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
          gameObject.die();
=======

        for (GameObject gameObject : this.handler.getNearObjects(this)) {
            if (!gameObject.solid) {
                break;
            }
            if ((gameObject instanceof Wall)) {

                if ((getBoundsLeft().intersects(gameObject.getBounds())) || (getBoundsRight().intersects(gameObject.getBounds()))) {
                    die();
                }
            }
>>>>>>> 857dedc761f9a46f230dfaf99f80f64530e882c5
        }

    }

}
