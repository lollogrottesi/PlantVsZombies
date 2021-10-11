/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import utils.Config;
import utils.ImportWizard;

/**
 *
 * @author lorenzo
 */
public class PeaShooterView extends AbstractPlantView{
    
    /*private BufferedImage[] plantAliveAnimation;
    private BufferedImage[] plantDeadAnimation;*/
    private BufferedImage[] plantImg;
    private Rectangle2D.Double shape;
    private int NextImageIndex;
    private int xPos;
    private int yPos;
    
    //private boolean canShoot; 
    public PeaShooterView(/*int startX, int startY*/){
        
       // this.canShoot = true;
        this.xPos = 0;
        this.yPos = 0;
        this.NextImageIndex = 0;
        this.shape = new Rectangle2D.Double();
        //this.plantAliveAnimation = new BufferedImage[2];
        //this.plantDeadAnimation = new BufferedImage[3];
        this.plantImg = new BufferedImage[5];
        importMedia();
       
    }//EndPeaShooter.
    
    public void setX(int x){
        this.xPos = x;
        if (getX() != 0)
            this.shape.setRect(getX(), getY(), 70 , 70);
    }//EndSetX.
    
    public void setY(int y){
        this.yPos = y;
        if (getY() != 0)
            this.shape.setRect(getX(), getY(), 70 , 70);
    }//EndSetY.;
    
    public int getX(){
        return this.xPos;
    }//EndGetX;
    
    public int getY(){
        return this.yPos;
    }//EndGetY.
    
    public int getNextImageIndex(){
        return this.NextImageIndex;
    }//EndNextImageIndex.
    
    public void setNextImageIndex(int newIndex){
        this.NextImageIndex = newIndex;
    }//EndSetImageIndex.
    
    public void drawPlant(Graphics2D g2d){
        
        //g2d.drawImage(this.plantAliveAnimation[NextImageIndex], getX(), getY(), null);
        g2d.drawImage(this.plantImg[NextImageIndex], getX(), getY(), null);
        //g2d.draw(this.shape);
        
    }//EndDrawPlant.
    
    
    public Rectangle2D.Double getRect(){
        return this.shape;
    }//EndGetRectangle.
    
    private void importMedia(){
        for(int i=0;i<5;i++)
                this.plantImg[i] = ImportWizard.getInstance().importPeaShooterMedia()[i];
    }//EndImportImage.
    
}//EndPeaShooterView.
