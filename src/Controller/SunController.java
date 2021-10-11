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

import Model.ISunModel;
import View.ISunView;
import Model.SunModel;
import View.SunView;
import java.awt.geom.Rectangle2D;

public class SunController {
    
    private ISunModel sunModel;
    private ISunView sunView;
    
    public SunController(int x, int y){
        
        this.sunModel = new SunModel(x, y);
        this.sunView = new SunView(x, y);
        
    }//EndBuilder.
    
    public ISunView getView(){
        return this.sunView;
    }//EndGetView.
    
    public ISunModel getModel(){
        return this.sunModel;
    }//EndGetModel.
    
    public void animate(){
        this.sunView.setNextImageIndex((this.sunView.getNextImageIndex() + 1) % 3);
    }//EndAnimate.
    
    public boolean checkCollision(Rectangle2D.Double rect){
       return this.getView().getRect().intersects(rect);
    }//EndCheckCollision.
    
    
}//EndSunController.
