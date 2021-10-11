/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics;
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
public class NormalZombieView extends AbstractZombieView{
    
    //private BufferedImage[] zombiealive;
    //private BufferedImage[] zombiedead;
    private BufferedImage[] zombieImg;
    private Rectangle2D.Double shape;
    private int xPos;
    private int yPos;
    private int NextImageIndex = 0;
    
    public NormalZombieView(int startX, int startY){
        
       this.xPos = startX;
       this.yPos = startY;
       this.shape = new Rectangle2D.Double();
       this.shape.setRect(startX + 15, startY + 5, 60 , 120);//for better gaming.
      //this.zombiealive = new BufferedImage[2]; 
       //this.zombiedead = new BufferedImage[6];
       this.zombieImg = new BufferedImage[8];
       
       importMedia();

    }//EndNormalZombieView;
    
    public void setX(int x){
        this.xPos = x;
        this.shape.setRect(x + 15, getY() + 5, 60 , 120);//for better gaming.
    }//EndSetX.
    
    public void setY(int y){
        this.xPos = y;
        this.shape.setRect(getX() + 15, y + 5, 60 , 115);//for better gaming.
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
    
    public void drawZombie(Graphics2D g2d){
        
        //g2d.drawImage(this.zombiealive[NextImageIndex], getX(), getY(), null);
        g2d.drawImage(this.zombieImg[NextImageIndex], getX(), getY(), null);
        //g2d.draw(this.shape);
        
    }//EndDrawZombie.
       
    public Rectangle2D.Double getRect(){
        return this.shape;
    }//EndGetRect.
    
    private void importMedia(){
        for (int i=0;i<8;i++)
            this.zombieImg[i] = ImportWizard.getInstance().importNormalZombieMedia()[i];
    }//EndImportImage.
    
}//EndNormalZombieView
