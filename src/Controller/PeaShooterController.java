/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author lorenzo
 */

import Model.AbstractPlantModel;
import View.AbstractPlantView;
import Model.PeaShooterModel;
import View.PeaShooterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import utils.Config;

public class PeaShooterController extends AbstractPlantController /*implements ActionListener*/{
    
    private AbstractPlantView shooterView = null;
    private AbstractPlantModel shooterModel = null;
    private Timer time = null;
    private boolean deadAnimationComplete = false;
    //private AudioStream shootSound = null;
   // private AbstractZombieController target = null;
    
    public PeaShooterController(int x, int y){
        this.shooterModel = new PeaShooterModel(x, y);
        this.shooterView = new PeaShooterView();
        //importMedia();
       
    }//EndBuilder.
    
    public boolean getDeadAnimationComplete(){
        return this.deadAnimationComplete;
    }//EndGtDeadAnimationComplete.
     
    public AbstractPlantView getView(){
        return this.shooterView;
    }//EndGetView.
    
    public AbstractPlantModel getModel(){
        return this.shooterModel;
    }//EndPeaShooterModel.
    
    public void animate(){
        
        if (this.shooterModel.isAlive())
            this.shooterView.setNextImageIndex((this.shooterView.getNextImageIndex() + 1) % 2);
        else{
            this.shooterView.setNextImageIndex((this.shooterView.getNextImageIndex() + 1) % 5);
            if ( (this.shooterView.getNextImageIndex() != 0) &&  (((this.shooterView.getNextImageIndex()+1) % 5) == 0) )
                this.deadAnimationComplete = true;
        }//EndElse.
    }//EndAnimate.
    
     public Rectangle2D.Double getRectView(){
         return this.shooterView.getRect();
     }//EndGetRectView.
     
     public boolean canShoot(){
         return true;
     }//EndCanShoot.
     
     /*public void sound(){
         AudioPlayer.player.start(this.shootSound);
     }//EndSoundShoot.*/
     
     public BulletController shoot(AbstractZombieController target){
            //sound();
           
            return new BulletController(this.shooterView.getX() + 55, this.shooterView.getY() + 20, target);
     }//EndShoot.
     
     public SunController generateSun(){
         return null;
     }//EndGenerateSun.
     
     public long getLastGenerationTime(){
         return 0;
     }//EndGetLastGenerationTime.
     
     /*private void importMedia(){
         try{//ImportImage.
            
            String pathSound = new String();
            pathSound = "\\conf\\sounds\\gun4.wav";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                pathSound = "/conf/sounds/gun4.wav";
            }//endif.
            
            this.shootSound =  new AudioStream(new FileInputStream(new File(Config.getInstance().getPath() + pathSound)));
            
        }//Endtry.
        catch(Exception e){
            
            System.out.println("Error importig PeaShoter image media"); 
            
       }//EndImporting.
    
     }//EndImportMedia.*/
}//EndPeaShooterController.
