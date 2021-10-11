/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AbstractPlantModel;
import View.AbstractPlantView;
import java.awt.geom.Rectangle2D;
/**
 *
 * @author lorenzo
 */
public abstract class AbstractPlantController {
    
    private AbstractPlantModel plantModel;
    private AbstractPlantView plantView;
    private boolean deadAnimationComplete;
    //private int timeMillis;
    
    public abstract AbstractPlantModel getModel();
    
    public abstract AbstractPlantView getView();
    
    public abstract void animate();
    
    public abstract Rectangle2D.Double getRectView();
    
    public abstract boolean canShoot();
    
    public abstract BulletController shoot(AbstractZombieController target);
    
    public abstract SunController generateSun();
    
    public abstract boolean getDeadAnimationComplete();
    
    public abstract long getLastGenerationTime();
    
    //public abstract void sound();
}//EndPlantController
