/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author lorenzo
 */
public interface IController{
    
    	public void openMainGui();

	public void closeMainGui();
        
        public void openInsertPlayerName();
        
        public void closeInsertPlayerName();
        
        public void startGame(String player);
        
        public void closeGame();
        
        public void openGameOverWindow();
        
        public void closeGameOverWindow();
        
}//EndInterface.
