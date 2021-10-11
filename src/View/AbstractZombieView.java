/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author lorenzo
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class AbstractZombieView {
    
    private Rectangle2D.Double shape;
    private BufferedImage[] zombiealive;
    private BufferedImage[] zombiedead;
    private int NextImageIndex;
    
    /*public abstract BufferedImage[] getAliveImage();
    public abstract BufferedImage[] getDeadImage(); */
    public abstract void drawZombie(Graphics2D g2d);
    public abstract int getY();
    public abstract int getX();
    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract int getNextImageIndex();
    public abstract void setNextImageIndex(int newIndex);
    public abstract Rectangle2D.Double getRect();
}//EndAbstractZombieView.
