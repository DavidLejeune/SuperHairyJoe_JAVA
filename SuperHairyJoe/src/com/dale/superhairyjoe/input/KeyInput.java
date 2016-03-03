/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.superhairyjoe.input;

import com.dale.superhairyjoe.Game;
import com.dale.superhairyjoe.Id;
import com.dale.superhairyjoe.entity.Entity;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author David
 */
public class KeyInput implements KeyListener{



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(Entity en: Game.handler.entity)
        {
            if (en.getId()==Id.player)
            {
                
                    switch(key)
                   {
                       case KeyEvent.VK_LEFT:
                           en.setvelX(-5);
                           en.facing=0;
                           break;
                       case KeyEvent.VK_RIGHT:
                           en.setvelX(5);
                           en.facing=1;
                           break;
                       case KeyEvent.VK_UP:
                           //en.setvelY(-5);
                           if(!en.jumping) 
                           {
                               en.jumping = true;
                               en.gravity = 10.0;
                           }
                           break;
                       case KeyEvent.VK_DOWN:
                           en.setvelY(5);
                           break;

                   }           
            }
        }

        
    }

 
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(Entity en: Game.handler.entity)
        {
            if (en.getId()==Id.player)
            {
                
                    switch(key)
                   {
                       case KeyEvent.VK_LEFT:
                           en.setvelX(0);
                           break;
                       case KeyEvent.VK_RIGHT:
                           en.setvelX(0);
                           break;
                       case KeyEvent.VK_UP:
                           en.setvelY(0);
                           break;
                       case KeyEvent.VK_DOWN:
                           en.setvelY(0);
                           break;

                   }    
            }
        }
        
    }

    
    
    
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        int key = e.getKeyCode();
        for(Entity en: Game.handler.entity)
        {
            switch(key)
           {
               case KeyEvent.VK_LEFT:
                   en.setvelX(-5);
                   break;
               case KeyEvent.VK_RIGHT:
                   en.setvelX(5);
                   break;
               case KeyEvent.VK_UP:
                   en.setvelY(-5);
                   break;
               case KeyEvent.VK_DOWN:
                   en.setvelY(5);
                   break;

           }           
        }
        
        
        
    }
    
}
