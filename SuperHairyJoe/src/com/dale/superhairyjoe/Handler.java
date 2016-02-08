/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe;

import com.dale.superhairjoe.tile.Tile;
import com.dale.superhairjoe.tile.Wall;
import com.dale.superhairyjoe.entity.Entity;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author David
 */
public class Handler {
    
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Tile> tile = new LinkedList<Tile>();
    
    public Handler()
    {
        createLevel();
    }
    
    public void render(Graphics g)
    {
        for(Entity en:entity)
        {
            en.render(g);
        }
        for(Tile ti:tile)
        {
            ti.render(g);
        }
    }
    
    public void tick()
    {
        for(Entity en:entity)
        {
            en.tick();
        }
        for(Tile ti:tile)
        {
            ti.tick();
        }
        
    }
    
    public void addEntity(Entity en)
    {
        entity.add(en);
        
    }  
    
    public void removeEntity(Entity en)
    {
        entity.remove(en);
        
    }
        
    public void addTile(Tile ti)
    {
        tile.add(ti);
        
    }  
    
    public void removeTile(Tile ti)
    {
        tile.remove(ti);
        
    }
    
    public void createLevel()
    {
        for(int i =0;i<Game.WIDTH * Game.SCALE /64 + 1;i++)
        {
            addTile(new Wall(i*64,Game.HEIGHT * Game.SCALE -64,64,64,true,Id.wall,this));
            //platform
            if(i!=0&&i!=1&&1!=30&i!=31)
            {
                addTile(new Wall(i*64,Game.HEIGHT / 2 ,64,64,true,Id.wall,this));

            }
        
        }
    }
    
}
