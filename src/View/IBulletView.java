/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author lorenzo
 */
public interface IBulletView {
    
    public void setX(int x);
    
    public void setY(int y);
    
    public int getX();
    
    public int getY();
    
    public Rectangle2D.Double getRect();
    
    public void drawBullet(Graphics2D g2d);
}//EndIBulletView.
