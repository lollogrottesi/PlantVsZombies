/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author lorenzo
 */
public abstract class AbstractPlantView {
    
    private Rectangle2D.Double shape;
    private BufferedImage[] plantImg;
    private int NextImageIndex;
    private int xPos;
    private int yPos;
    
    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract int getX();
    public abstract int getY();
    public abstract void setNextImageIndex(int newIndex);
    public abstract int getNextImageIndex();
    public abstract void drawPlant(Graphics2D g2d);
    //public abstract boolean canShoot();
    public abstract Rectangle2D.Double getRect();
}//EndAbstract.
