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
public class BeetBulletModel extends BulletModel{
    
    private int damege;
    
    public BeetBulletModel(int x, int y , AbstractZombieModel target){
        super(x, y ,target);
        this.damege = 100;
    }//EndBuilder.
    
    public int getDamege(){
        return this.damege;
    }//EndGetDamenge.
}//EndBeetBulletModel.
