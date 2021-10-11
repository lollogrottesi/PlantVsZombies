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
import View.GamePanel;
import View.GameWindow;
import View.MainGui;
import javax.swing.JFrame;
import View.*;
import utils.Config;
import utils.ImportWizard;
import utils.RankingHandler;
public class Main {

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        // TODO code application logic here
        new GameController().startGame();
    }*/
    public static void main(String[] args) {
       //System.out.println("path2:");
       //System.out.println(""+RankingHandler.getInstance().getRanking());
       Controller.getInstance().openMainGui();
      //Controller.getInstance().openGameOverWindow();
       //System.out.println("Hello");
       //System.out.println(""+Config.getInstance().getPath());
       //View.getInstance().openGameOverWindow();
       //Controller.getInstance().openGameOverWindow();
    }//EndMain.
    
}//EndClass.
