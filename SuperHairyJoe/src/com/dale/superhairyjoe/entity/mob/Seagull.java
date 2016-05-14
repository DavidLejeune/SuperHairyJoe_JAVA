package com.dale.superhairyjoe.entity.mob;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.entity.Enemy;
import com.dale.superhairyjoe.entity.EnemyFlying;
import com.dale.superhairyjoe.entity.Entity;
import com.dale.superhairyjoe.entity.Entity.Direction;
import com.dale.superhairyjoe.entity.GameObject;
import com.dale.superhairyjoe.entity.Projectile;
import com.dale.superhairyjoe.entity.weapons.BirdPoop;
import com.dale.superhairyjoe.gfx.Sprite;
import com.dale.superhairyjoe.sounds.Sound;
import com.dale.superhairyjoe.tile.Wall;
import java.awt.Graphics;

public class Seagull
  extends EnemyFlying
{
    int time = 0;
    int random = (int)(Math.random()*180);
    
  public Seagull(int x, int y, int width, int height, Handler handler)
  {
    super(x, y, width, height, handler);
    this.speed = 3;
  }
  
  public void render(Graphics g)
  {
    int offset = this.direction == Entity.Direction.RIGHT ? 0 : 4;
    g.drawImage(com.dale.superhairyjoe.Game.seagull[(this.frame + offset)].getBufferedImage(), this.x, this.y, this.width, this.height, null);
  }
  
  public void tick()
  {
    super.tick();
    if (this.velX != 0)
    {
      this.frameDelay += 1;
      if (this.frameDelay >= 10)
      {
        this.frame += 1;
        if (this.frame > 3) {
          this.frame = 0;
        }
        this.frameDelay = 0;
      }
    }
     if (time == random) {
         Sound.play("Squish .wav");
          Game.handler.addGameObject(new BirdPoop(this.getX(),this.getY()+12,24,24,Game.handler,this.direction));
          time = 0;
          random = (int)(Math.random()*280);
      }
    time ++;
    
    
//            for (GameObject gameObject : this.handler.getNearObjects(this))
//        {
//            if (!gameObject.solid) {
//              break;
//            }
//            if ((gameObject instanceof Projectile))
//            {
//              // this enemy just needs to turn when it hits a wall  
//              if ((getBounds().intersects(gameObject.getBounds()))) 
//              {
//                die();
//                gameObject.die();
//
//
//              }
//
//            }
//        }
    
  }
}
