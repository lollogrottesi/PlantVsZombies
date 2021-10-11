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
public class NormalZombieModel extends AbstractZombieModel{
    
    private int life; 
    private int xPos;
    private int yPos;
    private int speed;
    private boolean alive;
    
    public NormalZombieModel(int startX,int startY){
        this.life = 200;
        this.xPos = startX;
        this.yPos = startY;
        this.speed = 0;
        this.alive = true;
    }//EndNormalZombieModel.
    
    public void setX(int x){
        this.xPos = x;
    }//EndSetX.
    
    public void setY(int y){
        this.xPos = y;
    }//EndSetY.;
    
    public int getX(){
        return this.xPos;
    }//EndGetX;
    
    public int getY(){
        return this.yPos;
    }//EndGetY;
    
    public int getLife(){
        return this.life;
    }//EndGetLife.
    
    public void setAlive(boolean alive){
        this.alive = alive;
    }//EndSetAlive.
    
    public  boolean isAlive(){
        return this.alive;
    }//EndIsAlive.
    
    public void hit(int damege){
        this.life = getLife() - damege;
    }//EndHit.
}//EndNormalZombieModel.
