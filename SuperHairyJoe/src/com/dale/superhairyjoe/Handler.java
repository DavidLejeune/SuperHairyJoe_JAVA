package com.dale.superhairyjoe;

import com.dale.superhairyjoe.entity.GameObject;
import com.dale.superhairyjoe.entity.mob.Goomba;
import com.dale.superhairyjoe.entity.mob.Player;
import com.dale.superhairyjoe.entity.mob.Seagull;
import com.dale.superhairyjoe.entity.powerup.Mushroom;
import com.dale.superhairyjoe.tile.PowerUpBlock;
import com.dale.superhairyjoe.tile.Wall;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler
{
  protected Camera camera;
  public LinkedList<GameObject> gameObjects = new LinkedList();
  
  public Handler(Camera camera)
  {
    this.camera = camera;
  }
  
  public void render(Graphics g)
  {
    for (GameObject gameObject : getVisibleObjects(this.camera)) {
      gameObject.render(g);
    }
  }
  
  public void tick()
  {
    for (GameObject gameObject : getVisibleObjects(this.camera)) {
      gameObject.tick();
    }
  }
  
  public void addGameObject(GameObject gameObject)
  {
    this.gameObjects.add(gameObject);
  }
  
  public void removeGameObject(GameObject gameObject)
  {
    this.gameObjects.remove(gameObject);
  }
  
  public void createLevel(BufferedImage level)
  {
    int width = level.getWidth();
    int height = level.getHeight();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++)
      {
        int pixel = level.getRGB(x, y);
        
        int red = pixel >> 16 & 0xFF;
        int green = pixel >> 8 & 0xFF;
        int blue = pixel & 0xFF;
        if ((red == 0) && (green == 0) && (blue == 0)) {
          addGameObject(new Wall(x * 64, y * 64, 64, 64, this));
        }
        if ((red == 0) && (green == 0) && (blue == 255)) {
          addGameObject(new Player(x * 64, y * 64, 64, 64, this));
        }
        if ((red == 255) && (green == 0) && (blue == 0)) {
          addGameObject(new Mushroom(x * 64, y * 64, 64, 64, this));
        }
        if ((red == 100) && (green == 100) && (blue == 100)) {
          addGameObject(new Goomba(x * 64, y * 64, 64, 64, this));
        }
        if ((red == 75) && (green == 75) && (blue == 75)) {
          addGameObject(new PowerUpBlock(x * 64, y * 64, 64, 64, this, Game.mushroom));
        }
        if ((red == 25) && (green == 25) && (blue == 25)) {
          addGameObject(new Seagull(x * 64, y * 64, 64, 64, this));
        }
      }
    }
  }
  
  public LinkedList<GameObject> getNearObjects(GameObject gameObject)
  {
    int distance = 100;
    LinkedList<GameObject> nearObjects = new LinkedList();
    Rectangle broadPhase = gameObject.getBounds();
    broadPhase.grow(distance, distance);
    for (GameObject currentObject : this.gameObjects) {
      if ((broadPhase.intersects(currentObject.getBounds())) && (!currentObject.equals(gameObject))) {
        nearObjects.add(currentObject);
      }
    }
    return nearObjects;
  }
  
  public LinkedList<GameObject> getVisibleObjects(Camera camera)
  {
    int distance = Game.WIDTH;
    LinkedList<GameObject> nearObjects = new LinkedList();
    Rectangle broadPhase = new Rectangle(-camera.getX(), -camera.getY(), 10, 10);
    broadPhase.grow(distance, distance);
    for (GameObject currentObject : this.gameObjects) {
      if (broadPhase.intersects(currentObject.getBounds())) {
        nearObjects.add(currentObject);
      }
    }
    return nearObjects;
  }
}
