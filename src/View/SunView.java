/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import utils.Config;
import utils.ImportWizard;

/**
 *
 * @author lorenzo
 */
public class SunView implements ISunView{
    
    private BufferedImage[] sunImg;
    
    private int xPos;
    private int yPos;
    private int NextImageIndex = 0;
    private Rectangle2D.Double shape;
    
    public SunView(int x, int y){
        
        this.xPos = x;
        this.yPos = y;
        this.shape = new Rectangle2D.Double();
        this.shape.setRect(x, y , 50 , 50);
        this.sunImg = new BufferedImage[3];
        importMedia();
        
    }//EndSunView.
    
    public void setX(int x){
        this.xPos = x;
        this.shape.setRect(x, getY(), 50 , 50);//for better gaming.
    }//EndSetX.
    
    public void setY(int y){
        this.yPos = y;
        this.shape.setRect(getX(), y , 50 , 50);//for better gaming.
    }//EndSetY.
    
    public int getX(){
        return this.xPos;
    }//EndGetX.
    
    public int getY(){
        return this.yPos;
    }//EndGetY.
    
    public int getNextImageIndex(){
        return this.NextImageIndex;
    }//EndNextImageIndex.
    
    public void setNextImageIndex(int newIndex){
        this.NextImageIndex = newIndex;
    }//EndSetImageIndex.
    
    public Rectangle2D.Double getRect(){
        return this.shape;
    }//EndGetRect.
    
    public void drawSun(Graphics2D g2d){
        
        g2d.drawImage(this.sunImg[NextImageIndex], getX(), getY(), null);
        //g2d.draw(this.shape);
        
    }//EndDrawZombie.
    
 //Private methods.
    private void importMedia(){
        for (int i=0;i<3;i++)
            this.sunImg[i] = ImportWizard.getInstance().importSunMedia()[i];
    }//EndImportImage.

    
    
    
}//EndSuView.
