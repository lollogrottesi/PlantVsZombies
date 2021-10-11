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
import Model.LawnMoverModel;
import View.LawnMoverView;
import Model.ILawnMoverModel;
import View.ILawnMoverView;
import java.awt.geom.Rectangle2D;

public class LawnMoverController {
    
    private ILawnMoverModel lawnModel = null;
    private ILawnMoverView lawnView = null;
    
    public LawnMoverController(int x, int y){
        this.lawnModel = new LawnMoverModel(x, y);
        this.lawnView = new LawnMoverView(x, y);
    }//EndLawnMoverController.
    
    public ILawnMoverModel getModel(){
        return this.lawnModel;
    }//EndGetModel.
    
    public ILawnMoverView getView(){
        return this.lawnView;
    }//EndGetView.
    
    public void AnimateAndMove(){
        this.lawnView.setNextImageIndex((this.lawnView.getNextImageIndex() + 1) % 2);
        this.lawnView.setX(this.lawnView.getX() + 10);
        this.lawnModel.setX(this.lawnModel.getX() + 10);
        
    }//EndMoveLeft.*/
    
    public boolean checkCollision(Rectangle2D.Double crashRect){
        return this.lawnView.getRect().intersects(crashRect);
    }//EndCheckCollision.
}//EndLawnMoverController.
