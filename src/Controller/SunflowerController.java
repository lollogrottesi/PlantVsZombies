/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AbstractPlantModel;
import Model.SunflowerModel;
import View.AbstractPlantView;
import View.SunflowerView;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

/**
 *
 * @author lorenzo
 */
public class SunflowerController extends AbstractPlantController{
    
    private AbstractPlantView sunflowerView = null;
    private AbstractPlantModel sunflowerModel = null;
    private boolean deadAnimationComplete = false;
    private long lastGeneration = 0;
    
    
    public SunflowerController(int x, int y){
        this.sunflowerModel = new SunflowerModel(x, y);
        this.sunflowerView = new SunflowerView();
        this.lastGeneration = System.currentTimeMillis();
    }//EndBuilder.
    
    public boolean getDeadAnimationComplete(){
        return this.deadAnimationComplete;
    }//EndGtDeadAnimationComplete.
     
    public AbstractPlantView getView(){
        return this.sunflowerView;
    }//EndGetView.
    
    public AbstractPlantModel getModel(){
        return this.sunflowerModel;
    }//EndPeaShooterModel.
    
    public void animate(){
        
        if (this.sunflowerModel.isAlive())
            this.sunflowerView.setNextImageIndex((this.sunflowerView.getNextImageIndex() + 1) % 2);
        else{
            this.sunflowerView.setNextImageIndex((this.sunflowerView.getNextImageIndex() + 1) % 5);
            if ( (this.sunflowerView.getNextImageIndex() != 0) &&  (((this.sunflowerView.getNextImageIndex()+1) % 5) == 0) )
                this.deadAnimationComplete = true;
        }//EndElse.
    }//EndAnimate.
    
     public Rectangle2D.Double getRectView(){
         return this.sunflowerView.getRect();
     }//EndGetRectView.
     
     public boolean canShoot(){
         return false;
     }//EndCanShoot.
     
      public BulletController shoot(AbstractZombieController target){
          return null;
      }//EndShoot.
      
      public SunController generateSun(){
         this.lastGeneration = System.currentTimeMillis();
         return new SunController(this.sunflowerModel.getX() + 10, this.sunflowerModel.getY() + 10);
     }//EndGenerateSun.
      
     public long getLastGenerationTime(){
         return this.lastGeneration;
     }//EndGetLastGenerationTime.
}//EndSunFlowerController.
