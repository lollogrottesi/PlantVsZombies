/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BulletModel;
import View.BulletView;

/**
 *
 * @author lorenzo
 */
import Model.BeetBulletModel;
import View.BeetBulletView;
import Model.IBulletModel;
import View.IBulletView;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class BeetBulletController extends BulletController{
    
    private IBulletModel bulletModel = null;
    private IBulletView bulletView = null;
    private Timer time = null;
    
    public BeetBulletController(int x, int y , AbstractZombieController newTarget){
        super(x, y, newTarget);
        this.bulletModel = new BeetBulletModel(x, y, newTarget.getModel());
        this.bulletView = new BeetBulletView(x, y);
    }//EndBuilder.
    
    public IBulletModel getModel(){
        return this.bulletModel;
    }//EndGetModel.
    
    public IBulletView getView(){
        return this.bulletView;
    }//EndGetView
    
    public void moveRight(){
        this.bulletModel.setX(this.bulletModel.getX() + 9);
        this.bulletView.setX(this.bulletView.getX() + 9);
    }//EndMoveLeft.
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if ((super.getTarget() != null)  && (this.bulletView.getRect().intersects(super.getTarget().getView().getRect())) && !super.getStrike() ){
            super.getTarget().getModel().hit(this.bulletModel.getDamege());
            //System.out.println("" + this.target.getModel().getLife());
            super.setStrike(true);
        }//EndClass.
        else
            moveRight();
        
    }//EndActionPerformed.
}//EndBeetBulletController.
