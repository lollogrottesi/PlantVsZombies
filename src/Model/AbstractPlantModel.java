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
public abstract class AbstractPlantModel {
    
    private int life; 
    private int xPos;
    private int yPos;
    private int speed;
    private boolean alive;
    
    public abstract void setX(int x);
    
    public abstract void setY(int y);
    
    public abstract int getX();
    
    public abstract int getY();
    
    public abstract int getLife();
    
    public abstract void setAlive(boolean alive);
    
    public abstract boolean isAlive();
    
    public abstract void hit(int damege);
}//EndAbstract.
