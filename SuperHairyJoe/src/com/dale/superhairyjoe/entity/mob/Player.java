package com.dale.superhairyjoe.entity.mob;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.entity.Entity;
import com.dale.superhairyjoe.entity.Entity.Direction;
import com.dale.superhairyjoe.entity.GameObject;
import com.dale.superhairyjoe.entity.powerup.Mushroom;
import com.dale.superhairyjoe.gfx.Sprite;
import com.dale.superhairyjoe.tile.Coin;
import com.dale.superhairyjoe.tile.PowerUpBlock;
import com.dale.superhairyjoe.tile.Wall;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.PrintStream;

public class Player
  extends Entity
{
  private PlayerState state;
  
  public static enum PlayerState
  {
    BIG,  SMALL,  DEAD;
    
    private PlayerState() {}
  }
  
  private boolean animate = false;
  
  public Player(int x, int y, int width, int height, Handler handler)
  {
    super(x, y, width, height, handler);
    this.state = PlayerState.SMALL;
    this.direction = Entity.Direction.RIGHT;
    this.speed = 5;
  }
  
  public void tick()
  {
    super.tick();
    if (this.y <= 0) {
      this.y = 0;
    }
//    if (this.y + this.height >= Game.HEIGHT * 1) {
//      this.y = (Game.HEIGHT * 1 - this.height);
//    }
    if (this.velX != 0) {
      this.animate = true;
    } else {
      this.animate = false;
    }
    for (GameObject gameObject : this.handler.getNearObjects(this))
    {
      if (!gameObject.solid) {
        break;
      }
      if ((gameObject instanceof Wall))
      {
        if (getBoundsTop().intersects(gameObject.getBounds()))
        {
          setvelY(0);
          if (this.jumping)
          {
            this.jumping = false;
            this.gravity = 0.8D;
            this.falling = true;
          }
        }
        if (getBoundsBottom().intersects(gameObject.getBounds()))
        {
          setvelY(0);
          if (this.falling) {
            this.falling = false;
          }
        }
        else if ((!this.falling) && (!this.jumping))
        {
          this.gravity = 0.8D;
          this.falling = true;
        }
        if (getBoundsLeft().intersects(gameObject.getBounds()))
        {
          setvelX(0);
          this.x = (gameObject.getX() + gameObject.width);
        }
        if (getBoundsRight().intersects(gameObject.getBounds()))
        {
          setvelX(0);
          
          this.x = (gameObject.getX() - gameObject.width);
        }
      }
      
      
      // Pop the mushroom when the powerupblock is hit from the bottom
      if (((gameObject instanceof PowerUpBlock)) && 
        (getBoundsTop().intersects(((PowerUpBlock)gameObject).getBounds())))
      {
        System.out.println("hit Powerup ");
        ((PowerUpBlock)gameObject).activated = true;
      }
      
      
      
      // Making the powerupblock solid (like a normal wall)
      if ((gameObject instanceof PowerUpBlock))
      {
        if (getBoundsTop().intersects(gameObject.getBounds()))
        {
          setvelY(0);
          if (this.jumping)
          {
            this.jumping = false;
            this.gravity = 0.8D;
            this.falling = true;
          }
        }
        if (getBoundsBottom().intersects(gameObject.getBounds()))
        {
          setvelY(0);
          if (this.falling) {
            this.falling = false;
          }
        }
        else if ((!this.falling) && (!this.jumping))
        {
          this.gravity = 0.8D;
          this.falling = true;
        }
        if (getBoundsLeft().intersects(gameObject.getBounds()))
        {
          setvelX(0);
          this.x = (gameObject.getX() + gameObject.width);
        }
        if (getBoundsRight().intersects(gameObject.getBounds()))
        {
          setvelX(0);
          
          this.x = (gameObject.getX() - gameObject.width);
        }
      }  
      
      // Check if we are hitting a coin , if so score 'coins' + 1
      if ((gameObject instanceof Coin))
      {
        if (getBounds().intersects(gameObject.getBounds()))
        {
          Game.coins ++ ;
          gameObject.die();
        }
      }
      
      
      if ((gameObject instanceof Mushroom))
      {
        if (getBounds().intersects(gameObject.getBounds()))
        {
          int tpX = getX();
          int tpY = getY();
          
          this.width *= 1.1;
          this.height *= 1.1;
          
          setX(tpX - this.width);
          setY(tpY - this.height);
          if (this.state == PlayerState.SMALL) {
            this.state = PlayerState.BIG;
          }
          gameObject.die();
        }
      }
      else if ((gameObject instanceof Goomba)) {
        if (getBoundsBottom().intersects(((Goomba)gameObject).getBoundsTop())) {
          gameObject.die();
        } else if (getBounds().intersects(gameObject.getBounds())) {
          if (this.state == PlayerState.BIG)
          {
            this.state = PlayerState.SMALL;
            this.width /= 2;
            this.height /= 2;
            this.x += this.width;
            this.y += this.height;
            //remove the goomba after hit, else it always double hits
            //the player and player dies
            gameObject.die();
          }
          else if (this.state == PlayerState.SMALL)
          {
            die();
          }
        }
      }
    }
    if (this.jumping)
    {
      this.gravity -= 0.1D;
      setvelY((int)-this.gravity);
      if (this.gravity <= 0.0D)
      {
        this.jumping = false;
        this.falling = true;
      }
    }
    if (this.falling)
    {
      this.gravity += 0.1D;
      setvelY((int)this.gravity);
    }
    if (this.animate)
    {
      this.frameDelay += 1;
      if (this.frameDelay >= 10)
      {
        this.frame += 1;
        if (this.frame >= 4) {
          this.frame = 0;
        }
        this.frameDelay = 0;
      }
    }
  }
  
  public void render(Graphics g)
  {
    int offset = this.direction == Entity.Direction.RIGHT ? 0 : 4;
    g.drawImage(Game.player[(this.frame + offset)].getBufferedImage(), this.x, this.y, this.width, this.height, null);
  }
  
  protected void startWalking() {}
}
