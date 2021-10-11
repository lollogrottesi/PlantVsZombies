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
import Model.AbstractZombieModel;
import View.AbstractZombieView;
import View.NormalZombieView;
import java.awt.geom.Rectangle2D;
import javax.sound.sampled.Clip;

public  abstract class AbstractZombieController {
    
    private AbstractZombieModel zombieModel;
    private AbstractZombieView zombieView;
    private AbstractPlantController target;
    private boolean move;
    
     public abstract AbstractZombieView getView();
     
     public abstract AbstractZombieModel getModel();
     
     public abstract void moveZombie();
     
     public abstract void animate();
     
     public abstract Rectangle2D.Double getRectView();
      
     public abstract boolean checkCollision(Rectangle2D.Double crashRect);
     
     public abstract boolean canMove();
     
     public abstract void setMove(boolean newMove);
     
     public abstract void setTarget(AbstractPlantController newTarget);
     
     public abstract AbstractPlantController getTarget();
     
     public abstract boolean getDeadAnimationComplete();
     
     public abstract Clip getClip();
     
     public abstract void clipStart();
     
     /*public abstract int getX();
     
     public abstract int getY();*/
}//EndAbstractZombieController.
