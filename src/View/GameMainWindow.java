/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JFrame;

/**
 *
 * @author lorenzo
 */
public class GameMainWindow extends JFrame{
    
    private MainGui mainGui = null;
    
    public GameMainWindow(){
        //mainGui = new MainGui();
        mainGui = MainGui.getInstance();
        setTitle("JPlantVsZombieGarden");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(700,434));
        getContentPane().add(this.mainGui);
    }//EndBuilder.
    
}//EndGameGuiWindow.
