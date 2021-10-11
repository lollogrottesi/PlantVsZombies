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
public class LawnMoverModel implements ILawnMoverModel{
    
    private int xPos;
    private int yPos;
    private boolean active;
    
    public LawnMoverModel(int x, int y){
        this.xPos = x;
        this.yPos = y;
        this.active = true; 
    }//EndBuilder.
    
    public int getX(){
        return this.xPos;
    }//EndGetX.
    
    public int getY(){
        return this.yPos;
    }//EndGety.
    
    public void setX(int x){
        this.xPos = x;
    }//EndSetX.
    
    public void setY(int y){
        this.yPos = y;
    }//EndSety.
    
    public boolean isActive(){
        return this.active;
    }//EndIsActiove.
    
    public void setActive(boolean state){
        this.active = state;
    }//EndsetActive.
    
}//EndLawMover.
