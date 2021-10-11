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
import Model.IBulletModel;
import Model.BulletModel;
import Model.AbstractZombieModel;
import View.IBulletView;
import View.BulletView;
import View.AbstractZombieView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class BulletController implements ActionListener{
    
    private IBulletModel bulletModel = null;
    private IBulletView bulletView = null;
    private AbstractZombieController target = null;
    private boolean targetStrike;
    private Timer time = null;
    
    public BulletController(int x, int y, AbstractZombieController newTarget){
        this.bulletModel = new BulletModel(x, y, newTarget.getModel());
        this.bulletView = new BulletView(x, y);
        this.target = newTarget;
        this.targetStrike = false;
        this.time = new Timer(10, this);
        //this.time.start();
    }//EndBulletController.
    
    public IBulletModel getModel(){
        return this.bulletModel;
    }//EndGetModel.
    
    public IBulletView getView(){
        return this.bulletView;
    }//EndGetView.
    
    public void moveRight(){
        this.bulletModel.setX(this.bulletModel.getX() + 6);//4
        this.bulletView.setX(this.bulletView.getX() + 6);//4
    }//EndMoveLeft.

    public AbstractZombieController getTarget(){
        return this.target;
    }//EndGetTarget.
    
    public void setTarget(AbstractZombieController newTarget){
        this.target = newTarget;
    }//EndSetTarget.
    
    public boolean getStrike(){
        return this.targetStrike;
    }//EndGetStrike.
    
    public void setStrike(boolean state){
        this.targetStrike = state;
    }//EndSetStrike.
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if ((this.target != null)  && (this.bulletView.getRect().intersects(this.target.getView().getRect())) && !this.targetStrike ){
            this.target.getModel().hit(this.bulletModel.getDamege());
            //System.out.println("" + this.target.getModel().getLife());
            this.targetStrike = true;
        }//EndClass.
        else
            moveRight();
        
    }//EndActionPerformed.
    
}//EndBulletController.
