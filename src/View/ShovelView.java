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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import utils.Config;
import utils.ImportWizard;

/**
 *
 * @author lorenzo
 */
public class ShovelView {
    
   private static ShovelView instance = null;
   
   private int xPos;
   private int yPos;
    private Clip shovelSound = null;
   private BufferedImage img = null;
   private Rectangle2D.Double shape = null;
   
   private ShovelView(int x, int y){
       this.xPos = x;
       this.yPos = y;
       importMedia();
       this.shape = new Rectangle2D.Double();
       this.shape.setRect(x, y, 70, 75);
   }//EndShovelView.
   
   public void ShovelsetX(int x){
       this.xPos = x;
       this.shape.setRect(x, ShovelgetY(), 70, 75);
   }//EndSetX.
   
   public void ShovelsetY(int y){
       this.yPos = y;
       this.shape.setRect(ShovelgetX(), y, 70, 75);
   }//EndSetY.
   
   public int ShovelgetX(){
       return this.xPos;
   }//EndGetX.
   
   public int ShovelgetY(){
       return this.yPos;
   }//EndGet.
   
   public Rectangle2D.Double getShovelRect(){
       return this.shape;
   }//EndGetRect.
   
   public void drawShovel(Graphics2D g2d){
       g2d.drawImage(this.img, this.xPos, this.yPos, null); 
       //g2d.draw(this.shape);
   }//EndDrawShovel.
   
    public void soundShovel(){
        this.shovelSound.setMicrosecondPosition(0);
        this.shovelSound.start();
    }//EndSoundShovel.
    
   private void importMedia(){
       
        this.img = (BufferedImage)ImportWizard.getInstance().importShovelMedia()[0];
        this.shovelSound = (Clip)ImportWizard.getInstance().importShovelMedia()[1];
        
   }//EndImportMedia.
   
   public static ShovelView getInstance(){
       if(instance == null)
           instance = new ShovelView(1020, 10);
       return instance;
   }//EndGetInstance.
}//EndShovelView.
