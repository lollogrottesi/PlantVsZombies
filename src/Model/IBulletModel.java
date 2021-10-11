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
public interface IBulletModel {
    
    public void setTarget(AbstractZombieModel newTarget);
    
    public AbstractZombieModel getTarget();
    
    public int getTargetLife();
    
    public void setX(int newX);
    
    public int getX();
    
    public void setY(int newY);
   
    public int getY();
    
    public int getDamege();
}//EndIBulletModel.
