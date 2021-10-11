/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author lorenzo
 */
public interface IView {
    
    	public void openMainWindow();

	public void closeMainWindow();

        public void openInsertPlayerWindow();
        
        public void closeInsertPlayerWindow();
        
        public void openRankingWindow();
        
        public void closeRankingWindow();
        
        public void openGameWindow();
         
        public void closeGameWindow();
        
        public void openGameOverWindow();
        
        public void closeGameOverWindow();
	
        public Rectangle2D.Double getCell(int i, int j);
        
        public void dragAnimationPlant(AbstractPlantView newPlant, int x , int y);
        
        public void setShovel(ShovelView newShovel);
        
        public void dragAnimationShovel(int x, int y);
        
        public void addZombieView(AbstractZombieView newZombie);
        
        public void removeZombieView(int i, int j);
        
        public void addPlantView(AbstractPlantView newPlant, int x, int y);
                
        public void removePlantView(int i, int j);
        
        public void addLawnMoverView(ILawnMoverView newLawnMover,int index);
        
        public void removeLawnMoverView(int index);
        
        public void addBulletView(int index, IBulletView newBullet);
        
        public void removeBulletView(int i, int j);
        
        public IBulletView getFirstBullet(int index);
        
        public void addSunView(ISunView newSun);
        
        public void removeSunView(ISunView oldSun);
        
        public Line2D.Double getGameOverLine();
        
        public void addPanelListener(MouseInputListener listener);
        
        public void repaint();
        
        //shovelMethod
        public void ShovelsetX(int x);
        
        public void ShovelsetY(int y);
        
        public int ShovelgetX();
        
        public int ShovelgetY();
        
        public Rectangle2D.Double getShovelRect();
        
        public void drawShovel(Graphics2D g2d);
        
        public void soundShovel();
}//EndInerface.
