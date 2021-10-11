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
public class SunModel implements ISunModel{
    
    private int xPos;
    private int yPos;
    private boolean clicked;
    
    public SunModel(int x, int y){
        this.xPos = x;
        this.yPos = y;
        this.clicked = false;
    }//EndBuilder.
    
    public int getX(){
        return this.xPos;
    }//EndGetX.
    
    public int getY(){
        return this.yPos;
    }//EndGetY.
    
    public void setClicked(boolean state){
        this.clicked = state;
    }//EndSetClicked.
    
    public boolean isClicked(){
        return this.clicked;
    }//EndIsClicked.
    
}//EndSunModel
