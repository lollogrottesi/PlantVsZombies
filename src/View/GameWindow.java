/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author lorenzo
 */
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class GameWindow extends JFrame {

    private Container contPane;
    //private GamePanel game;

    public GameWindow(GamePanel game) {
        setTitle("JPlantVsZombieGarden");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
       // setSize(new java.awt.Dimension(1200,700));
       setSize(new java.awt.Dimension(1116,630));
        //this.game = new GamePanel();
        this.contPane = getContentPane();
        this.contPane.add(game);
        //this.contPane.setLayout(null);
    }//EndBuilder.

    /*public void update(){
        this.game.repaint();
    }//EndUpdate.*/
    
    /*public void addZombie(NormalZombieView newZombie){
        this.game.addZombie(newZombie);
    }//EndAddZombie.*/
}//EndGameWindow.
