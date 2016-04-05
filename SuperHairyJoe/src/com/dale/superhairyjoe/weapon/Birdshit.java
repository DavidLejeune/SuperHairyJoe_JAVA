
package com.dale.superhairyjoe.weapon;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.entity.GameObject;
import com.dale.superhairyjoe.entity.mob.Goomba;
import com.dale.superhairyjoe.entity.mob.Player;
import com.dale.superhairyjoe.entity.powerup.Mushroom;
import com.dale.superhairyjoe.tile.PowerUpBlock;
import com.dale.superhairyjoe.tile.Wall;
import java.awt.*;


/**
 *
 * @author David
 */
public class Birdshit {
    
    private double dx,dy, x,y,rad,speed;
    private int r;
    
    private Color color1;
    
    public Birdshit(double angle, int x, int y)
    {
        this.x =x;
        this.y =y;
        r=2;
        
        speed = 15;
        
        rad = Math.toRadians(angle);
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;
        
        color1 = Color.WHITE;
        
        
    }
    
    
    public boolean update()
    {
        x += dx;
        y += dy;
        
    if (x < -r || x > Game.WIDTH + r || y < -r || y > Game.HEIGHT +r)
    {


          return true;

    }
        return false;
        
    }
    
    public void draw(Graphics g)
    {
        g.setColor(color1);
        g.drawOval((int) (x-r) , (int) (y-r) , 2*r , 2 * r);
    }
    
}
