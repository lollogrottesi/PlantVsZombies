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
public interface ILawnMoverModel {
    
    public int getX();
    
    public int getY();
    
    public void setX(int x);
    
    public void setY(int y);
    
    public boolean isActive();
    
    public void setActive(boolean state);
            
}//EndILawnMoverModel.
