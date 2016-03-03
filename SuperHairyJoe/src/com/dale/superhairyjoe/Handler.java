/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe;

import com.dale.superhairyjoe.tile.Tile;
import com.dale.superhairyjoe.tile.Wall;
import com.dale.superhairyjoe.entity.Entity;
import com.dale.superhairyjoe.entity.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author David
 */
public class Handler {
    
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Tile> tile = new LinkedList<Tile>();
    
//    public void render(Graphics g)
//    {
//        for (int i = 0 ;i<entity.size() ; i++)
//        {
//            Entity en = entity.get(i);
//            en.render(g);
//        }
//        for (int i = 0 ;i<tile.size() ; i++)
//        {
//           Tile ti = tile.get(i);
//            ti.render(g);
//        }
//    }
//    
//    
//    
//    
//    public void tick()
//    {
//        for (int i = 0 ;i<entity.size() ; i++)
//        {
//            Entity en = entity.get(i);
//            en.tick();
//        }
//        for (int i = 0 ;i<tile.size() ; i++)
//        {
//           Tile ti = tile.get(i);
//            ti.tick();
//        }
//        
//    }
    
    
    
    
    

  
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
    
    public void createLevel(BufferedImage level)
    {
//OLD METHOD (before level creation automation"        
//        for(int i =0;i<Game.WIDTH * Game.SCALE /64 + 1;i++)
//        {
//            addTile(new Wall(i*64,Game.HEIGHT * Game.SCALE -64,64,64,true,Id.wall,this));
//            //platform
//            if(i!=0&&i!=1&&i!=15&&1!=30&i!=31)
//            {
//                addTile(new Wall(i*64,Game.HEIGHT / 2 ,64,64,true,Id.wall,this));
//
//            }
//        
//        }
        
        int width = level.getWidth();
        int height = level.getHeight();
        
        for (int y =0 ; y< height ; y++)
        {
            for (int x = 0 ; x < width ; x++)
            {
                int pixel = level.getRGB(x,y);
                
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = (pixel) & 0xFF;
                
                //walls = black
                if (red==0 && green==0 && blue==0) addTile(new Wall(x*64,y*64,64,64,true,Id.wall,this));
                
               //player= blue
                if (red==0 && green==0 && blue==255) addEntity(new Player(x*64,y*64,64,64,false,Id.player,this));
                
                
                
            }
        }
        
        
        
        
        
    }
    
}
