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
import Model.WalnutModel;
import View.WalnutView;
import View.AbstractPlantView;
import Model.AbstractPlantModel;
import java.awt.geom.Rectangle2D;

public class WalnutController extends AbstractPlantController {
    
    private AbstractPlantModel walnutModel = null;
    private AbstractPlantView  walnutView = null;
    private boolean deadAnimationComplete = false;
    private long lastGeneration = 0;
    
    public WalnutController(int x, int y){
        this.walnutModel = new WalnutModel(x, y);
        this.walnutView = new WalnutView();
        
    }//EndBuilder.
    
     public boolean getDeadAnimationComplete(){
        return this.deadAnimationComplete;
    }//EndGtDeadAnimationComplete.
     
    public AbstractPlantView getView(){
        return this.walnutView;
    }//EndGetView.
    
    public AbstractPlantModel getModel(){
        return this.walnutModel;
    }//EndPeaShooterModel.
    
     public void animate(){
        
        if ( (this.walnutModel.isAlive()) && (this.walnutModel.getLife() > 400) )
            this.walnutView.setNextImageIndex((this.walnutView.getNextImageIndex() + 1) % 3);
        
        else if ( (this.walnutModel.isAlive()) && (this.walnutModel.getLife() < 400) )
            this.walnutView.setNextImageIndex( ((this.walnutView.getNextImageIndex() + 1) % 3) + 3);
        
        if (!this.walnutModel.isAlive()){
                this.walnutView.setNextImageIndex( ((this.walnutView.getNextImageIndex() + 1) % 3) + 6); 
                if ( (this.walnutView.getNextImageIndex() != 0) &&  ((((this.walnutView.getNextImageIndex() + 1) % 3) + 6) == 8) )
                    this.deadAnimationComplete = true;
        }//EndIf.
    }//EndAnimate.
     public Rectangle2D.Double getRectView(){
         return this.walnutView.getRect();
     }//EndGetRectView.
     
     public boolean canShoot(){
         return false;
     }//EndCanShoot.
     
      public BulletController shoot(AbstractZombieController target){
          return null;
      }//EndShoot.
      
      public SunController generateSun(){
         return null;
     }//EndGenerateSun.
      
     public long getLastGenerationTime(){
         return 0;
     }//EndGetLastGenerationTime.
}//EndWalnutController.
