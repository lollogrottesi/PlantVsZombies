/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import utils.Config;
import utils.ImportWizard;
//lastAdded.
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author lorenzo
 */
public class LawnMoverView implements ILawnMoverView{
    
    private BufferedImage[] lawnMoverImg;
    //private AudioStream moveSound = null;
    //da rivedere:-------------
    //private AudioInputStream moveSound = null; 
   // private SourceDataLine line = null;
    private Clip clip; 
    private boolean flag = true;
    //------------------------
    private int xPos;
    private int yPos;
    private Rectangle2D.Double shape;
    private int NextImageIndex;
    //private int soundLeght;
   // private byte[] soundMove;
    
    public LawnMoverView(int x, int y){
        
        this.xPos = x;
        this.yPos = y;
        this.NextImageIndex = 0;
        this.shape = new Rectangle2D.Double();
        this.shape.setRect(x, y, 80, 70);
        this.lawnMoverImg = new BufferedImage[2];
        importMedia();
        
    }//EndBuilder.
    
    public void setX(int x){
        this.xPos = x;
        if (getX() != 0)
            this.shape.setRect(getX(), getY(), 80 , 70);
    }//EndSetX.
    
    public void setY(int y){
        this.yPos = y;
        if (getY() != 0)
            this.shape.setRect(getX(), getY(), 80 , 70);
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
    
    public Rectangle2D.Double getRect(){
        return this.shape;
    }//EndGetRectangle.
    
    public void soundLawnMover(){
        
        try{
            if (flag){
                this.clip.start();
                 flag = false;
            }  
        }catch(Exception e){
            e.printStackTrace();
        }//EndCatch.
        
         
    }//EndSound*/
    
    public void  drawLanwnMover(Graphics2D g2d){
        g2d.drawImage(this.lawnMoverImg[NextImageIndex], getX(), getY(), null);
        //g2d.draw(this.shape);
    }//EndDrawLawnMover.
    
    private void importMedia(){
        this.lawnMoverImg[0] = (BufferedImage)ImportWizard.getInstance().importLawnMoverMedia()[0];
        this.lawnMoverImg[1] = (BufferedImage)ImportWizard.getInstance().importLawnMoverMedia()[1];
        this.clip = (Clip)ImportWizard.getInstance().importLawnMoverMedia()[2];
    }//EndImportMedia.
}//EndLawnMover.
