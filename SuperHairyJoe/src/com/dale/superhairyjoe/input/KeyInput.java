package com.dale.superhairyjoe.input;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Handler;
import com.dale.superhairyjoe.entity.GameObject;
import com.dale.superhairyjoe.entity.mob.Player;
import com.dale.superhairyjoe.sounds.Sound;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput
  implements KeyListener
{
  public void keyPressed(KeyEvent e)
  {
    int key = e.getKeyCode();
    
    
    if(Game.gameStatus==1)
    {
        if(key==40)
        {

            Game.menuMoveDown = true;
        }
        if(key==38)
        {

            Game.menuMoveUp = true;

        }
        if(key==KeyEvent.VK_ENTER)
        {
            System.out.println("enter");
            Game.menuChoose = true;

        }
    }    
    
    
    
    if(Game.gameStatus==38)
    {
        if(key==KeyEvent.VK_ESCAPE)
        {
            Game.gameStatus = 1;

        }
    }
    
    for (GameObject gameObject : Game.handler.gameObjects) {
      if ((gameObject instanceof Player))
      {
        Player player = (Player)gameObject;
        switch (key)
        {
        case 37: 
          player.turnLeft();
          break;
        case 39: 
          player.turnRight();
          break;
        case 38: 
          if (!player.jumping)
          {
            player.jumping = true;
            player.gravity = 10.0D;
            
            Sound.play("Jump.wav"); 
          }
          break;
        case 40: 
          player.setvelY(5);
            
            
        }
      }
    }
  }
  
  public void keyReleased(KeyEvent e)
  {
    int key = e.getKeyCode();
    
      if(Game.gameStatus==1)
    {
        if(key==40)
        {
            Game.menuMoveDown = false;
        }
        if(key==38)
        {
            Game.menuMoveUp = false;
        }  
        if(key==KeyEvent.VK_ENTER)
        {
            System.out.println("enter");
            Game.menuChoose = false;
        }
    }
    
       if(Game.gameStatus==38)
    {
        if(key==KeyEvent.VK_ESCAPE)
        {
            Game.gameStatus = 1;

        }
    }
       
       
    for (GameObject gameObject : Game.handler.gameObjects) {
      if ((gameObject instanceof Player))
      {
        Player player = (Player)gameObject;
        switch (key)
        {
        case 37: 
          player.setvelX(0);
          break;
        case 39: 
          player.setvelX(0);
          break;
        case 38: 
          player.setvelY(0);
          break;
        case 40: 
          player.setvelY(0);
        }
      }
    }
  }
  
  public void keyTyped(KeyEvent e)
  {
    int key = e.getKeyCode();
    for (GameObject gameObject : Game.handler.gameObjects)
    {
      Player player = (Player)gameObject;
      switch (key)
      {
      case 37: 
        player.setvelX(-5);
        break;
      case 39: 
        player.setvelX(5);
        break;
      case 38: 
        player.setvelY(-5);
        break;
      case 40: 
        player.setvelY(5);
      }
    }
  }
}
