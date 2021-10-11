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
import Model.BeetRootModel;
import View.AbstractPlantView;
import View.BeetRootView;
import java.awt.geom.Rectangle2D;

public class BeetRootController extends AbstractPlantController{
    
    private AbstractPlantModel plantModel = null;
    private AbstractPlantView plantView = null;
    private boolean deadAnimationComplete = false;
    
    public BeetRootController(int x, int y){
        this.plantModel = new BeetRootModel(x, y);
        this.plantView = new BeetRootView();
    }//EndBeetRootController.
    
     public boolean getDeadAnimationComplete(){
        return this.deadAnimationComplete;
    }//EndGtDeadAnimationComplete.
     
    public AbstractPlantView getView(){
        return this.plantView;
    }//EndGetView.
    
    public AbstractPlantModel getModel(){
        return this.plantModel;
    }//EndPeaShooterModel.
    
    public void animate(){
        
        if (this.plantModel.isAlive())
            this.plantView.setNextImageIndex((this.plantView.getNextImageIndex() + 1) % 2);
        else{
            this.plantView.setNextImageIndex((this.plantView.getNextImageIndex() + 1) % 5);
            if ( (this.plantView.getNextImageIndex() != 0) &&  (((this.plantView.getNextImageIndex()+1) % 5) == 0) )
                this.deadAnimationComplete = true;
        }//EndElse.
    }//EndAnimate.
    
     public Rectangle2D.Double getRectView(){
         return this.plantView.getRect();
     }//EndGetRectView.
     
     public boolean canShoot(){
         return true;
     }//EndCanShoot.
     
     public BulletController shoot(AbstractZombieController target){
         
            return new BeetBulletController(this.plantView.getX() + 55, this.plantView.getY() + 20, target);
         
     }//EndShoot.
     
     public SunController generateSun(){
         return null;
     }//EndGenerateSun.
     
     public long getLastGenerationTime(){
         return 0;
     }//EndGetLastGenerationTime.
     
}//EndBeetRootController.
