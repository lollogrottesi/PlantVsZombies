/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author lorenzo
 */
public class BulletModel implements IBulletModel{
    int xPos;
    int yPos;
    int damege;
    AbstractZombieModel target = null;
     public BulletModel(int x, int y, AbstractZombieModel target){
         this.xPos = x;
         this.yPos = y;
         this.target = target;
         this.damege = 50;
     }//EndBuilder.
     
    public void setTarget(AbstractZombieModel newTarget){
        this.target = newTarget;
    }//EndsetTarget.
    
    public AbstractZombieModel getTarget(){
        return this.target;
    }//EndGetTarget.
    
    public int getTargetLife(){
        return this.target.getLife();
    }//ENDgetTargetLife.
    
    public void setX(int newX){
        this.xPos = newX;
    }//EndSetX.
   
    public int getX(){
        return this.xPos;
    }//EndGetX.
    
    public void setY(int newY){
        this.yPos = newY;
    }//EndSetY.
    
    public int getY(){
        return this.yPos;
    }//EndGetY.
    
    public int getDamege(){
        return this.damege;
    }//EndGetDamege.
}//EndBulletModel.
