/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author lorenzo
 */
public interface IModel {
        
        public int getMoney(); 
        
        public void setMoney(int newMoney);
        
    	public int getNumColumnsOfBoard();

	public int getNumRowsOfBoard();
        
        public int getRowIndex(int y);
         
        public int getColumnIndex(int x);
        
        public String getPlayerName();

	public void setPlayerName(String playerName);

	public int getScore();

	public void incrementScore(int increment);
        
        public boolean isGameOver();
        
        public void setGameOver(boolean gameOverState);
        
        public  ArrayList<AbstractZombieModel> getRowZombieList(int index);
        
        public void addZombieModel(int index, AbstractZombieModel newZombie);
        
        //public void addZombieModel(int index, int x, int y);
        
        public void removeZombieModel(int i, int j);
        
        public AbstractPlantModel getPlant(int i, int j);
        
        public void addLawnMoverModel(ILawnMoverModel newLawnMover, int index);
        
        public void removeLawnMoverModel(int index);
        
        public void addPlantModel(AbstractPlantModel newPlant, int i, int j);
        
        public void removePlantModel(int i, int j);
        
        public void addBulletModel(int index, IBulletModel newBullet);
        
        public void removeBulletModel(int i, int j);
        
        //Shovel method.
        
        public void setShovelDrag(boolean state);
        
        public boolean isShovelDrag();
        
        public void ShovelSetX(int x);
        
        public void ShovelSetY(int y);
        
        public int ShovelGetX();
        
        public int ShovelGetY();
        
}//EndInerface.
