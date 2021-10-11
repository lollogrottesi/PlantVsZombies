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
public class ShovelModel {
    
    private static ShovelModel instance = null;
    
    private boolean drag ;
    private int xPos;
    private int yPos;
    
    private ShovelModel(){
        this.drag = false;
    }//EndBuilder.
    
    public void setShovelDrag(boolean state){
        this.drag = state;
    }//EndSetDrag.
    
    public boolean isShovelDrag(){
        return this.drag;
    }//EndIsDrag.
    
     public void ShovelSetX(int x){
       this.xPos = x;
    }//EndSetX.
   
    public void ShovelSetY(int y){
       this.yPos = y;
    }//EndSetY.
   
     public int ShovelGetX(){
       return this.xPos;
    }//EndGetX.
   
    public int ShovelGetY(){
       return this.yPos;
    }//EndGet.
    
    public static ShovelModel getInstance(){
        if (instance == null)
            instance = new ShovelModel();
        return instance;
    }//EndGetInstance.
}//EndShovelModel.
