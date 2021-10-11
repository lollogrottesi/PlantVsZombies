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
import Model.GameModel;
import View.View;

public class Controller implements IController{
    
    private static Controller instance = null;
    
    
    private Controller(){
        
    }//EndController.
   
    
    public static IController getInstance(){
        if (instance == null)
            instance = new Controller();
	return instance;
    }//EndGetInstance.
    
    @Override
    public void openMainGui(){
        View.getInstance().openMainWindow();
    }//EndStartMainGui.
    
    @Override
    public void openInsertPlayerName(){
        View.getInstance().openInsertPlayerWindow();
    }//EndOpenInsertName.
    
    @Override
    public void closeInsertPlayerName(){
        View.getInstance().closeInsertPlayerWindow();
    }//EndCloseInsertPlayerName.
    
    @Override
    public void closeMainGui(){
        //MainWindow.getInstance().setVisible(false);
        View.getInstance().closeMainWindow();
    } //EndCloseMainGui.
    
    @Override
    public void openGameOverWindow(){
        //MainWindow.getInstance().setVisible(false);
        View.getInstance().openGameOverWindow();
    } //EndCloseMainGui.
    
    @Override
    public void closeGameOverWindow(){
        //MainWindow.getInstance().setVisible(false);
        View.getInstance().closeGameOverWindow();
    } //EndCloseMainGui.
    
    @Override
    public void startGame(String player){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                Model.GameModel.getInstance().setPlayerName(player);
                GameController.getInstance().start();
                //new GameWindow(GamePanel.getInstance()).setVisible(true);//Maybe implement pattern Singleton
            	View.getInstance().openGameWindow();
                //GameView.setVisible(true);
            }//EndRunThread.
        });
    }//EndStartGame.
    
    @Override
    public void closeGame(){
        GameController.getInstance().stop();
        View.getInstance().closeGameWindow();
    }//EndCloseGame.
}//EndController.
