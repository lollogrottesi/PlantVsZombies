/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.GameModel;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author lorenzo
 */
public class View implements IView{
    
    private static View instance = null;
    
    private JFrame frame = null;
    private JFrame playerNameFrame = null;
    private JFrame rankingFrame = null; 
    private JFrame gameWindow = null;
    private JFrame gameOverWindow = null;
    private View(){
        
        this.gameWindow = new JFrame();
        this.gameWindow.setTitle("JPlantVsZombieGarden");
        this.gameWindow.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.gameWindow.setResizable(false);
        this.gameWindow.setSize(new java.awt.Dimension(1116,630));
       
       
        this.frame = new JFrame();
        this.frame.setTitle("JPlantVsZombieGarden");
        this.frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setSize(new java.awt.Dimension(700,434));
        this.frame.setLocationRelativeTo(null);
        
        this.playerNameFrame = new JFrame();
        this.playerNameFrame.setTitle("JPlantVsZombieGarden");
        this.playerNameFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.playerNameFrame.setResizable(false);
        this.playerNameFrame.setSize(new java.awt.Dimension(500,355));
        this.playerNameFrame.setLocationRelativeTo(null);
        
        this.rankingFrame = new JFrame();
        this.rankingFrame.setTitle("JPlantVsZombieGarden");
        this.rankingFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.rankingFrame.setResizable(false);
        this.rankingFrame.setSize(new java.awt.Dimension(700,700));
        this.rankingFrame.setLocationRelativeTo(null);
        
        this.gameOverWindow = new JFrame();
        this.gameOverWindow.setTitle("JPlantVsZombieGarden");
        this.gameOverWindow.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.gameOverWindow.setResizable(false);
        this.gameOverWindow.setSize(new java.awt.Dimension(1116,630));
    }//Endview.
    
    
//Public methods----------------------------------------------------------------
    
    @Override
    public void openMainWindow(){
        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
          //  public void run () {
                //MainGui mainGui = new MainGui();
                //frame.getContentPane().add(mainGui);
                frame.getContentPane().add(MainGui.getInstance());
                frame.setVisible(true);
           // }//EndRunThread.
        //});
    }//EndOpenMainWindow.
    
    @Override
    public void closeMainWindow(){
        frame.setVisible(false);
    }//EndCloseMainWindow.
    
    @Override
    public void openInsertPlayerWindow(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run () {
               InsertNamePanel name = new InsertNamePanel();
               playerNameFrame.getContentPane().add(name);
               playerNameFrame.setVisible(true);
            }//EndRunThread.
        });
    }//EndOpenInsertPlayerWindow.
    
    @Override
    public void closeInsertPlayerWindow(){
         playerNameFrame.setVisible(false);
    }//EndCloseInsertPlayerWindow.
    
    @Override
    public void openRankingWindow(){
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run () {
               RankingPanel ranking = new RankingPanel();
               rankingFrame.getContentPane().add(ranking);
               rankingFrame.setVisible(true);
            }//EndRunThread.
        });
    }//EndOpenRankingWinow.
    
    @Override
    public void closeRankingWindow(){
         rankingFrame.setVisible(false);
    }//EndCloseRankingWindow.
    
    @Override
    public void openGameWindow(){
        gameWindow.getContentPane().add(GamePanel.getInstance());
        gameWindow.setVisible(true);
    }//EndOpenGameWindow.
    
    @Override
    public void closeGameWindow(){
         gameWindow.setVisible(false);
    }//EndCloseGameWindow.
    
    @Override
    public void openGameOverWindow(){
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                gameOverWindow.getContentPane().add(GameOverPanel.getInstance());
                gameOverWindow.setVisible(true);
            }//EndRunThread.
        });
    }//EndOpenMainWindow.
     
    @Override
    public void closeGameOverWindow(){
        gameOverWindow.setVisible(false);
    }//EndCloseGameOverWindow.
    
    @Override
    public Rectangle2D.Double getCell(int i, int j) {
        return GamePanel.getInstance().getCell(i, j);
    }

    @Override
    public void dragAnimationPlant(AbstractPlantView newPlant, int x, int y) {
        GamePanel.getInstance().dragAnimationPlant(newPlant, x, y);
    }

    @Override
    public void setShovel(ShovelView newShovel) {
        GamePanel.getInstance().setShovel(newShovel);
    }

    @Override
    public void dragAnimationShovel(int x, int y) {
        GamePanel.getInstance().dragAnimationShovel(x, y);
    }

    @Override
    public void addZombieView(AbstractZombieView newZombie) {
        GamePanel.getInstance().addZombieView(newZombie);
    }

    @Override
    public void removeZombieView(int i, int j) {
        GamePanel.getInstance().removeZombieView(i, j);
    }

    @Override
    public void addPlantView(AbstractPlantView newPlant, int x, int y) {
        GamePanel.getInstance().addPlantView(newPlant, x, y);
    }

    @Override
    public void removePlantView(int i, int j) {
        GamePanel.getInstance().removePlantView(i,j);
    }

    @Override
    public void addLawnMoverView(ILawnMoverView newLawnMover, int index) {
        GamePanel.getInstance().addLawnMoverView(newLawnMover, index);
    }

    @Override
    public void removeLawnMoverView(int index) {
        GamePanel.getInstance().removeLawnMoverView(index);
    }

    @Override
    public void addBulletView(int index, IBulletView newBullet) {
        GamePanel.getInstance().addBulletView(index, newBullet);
    }

    @Override
    public void removeBulletView(int i, int j) {
        GamePanel.getInstance().removeBulletView(i, j);
    }

    @Override
    public IBulletView getFirstBullet(int index) {
        return GamePanel.getInstance().getFirstBullet(index);
    }

    @Override
    public void addSunView(ISunView newSun) {
        GamePanel.getInstance().addSunView(newSun);
    }

    @Override
    public void removeSunView(ISunView oldSun) {
        GamePanel.getInstance().removeSunView(oldSun);
    }

    @Override
    public Line2D.Double getGameOverLine() {
        return GamePanel.getInstance().getGameOverLine();
    }

    @Override
    public void addPanelListener(MouseInputListener listener) {
        GamePanel.getInstance().addPanelListener(listener);
    }
    
    @Override
    public void repaint(){
        GamePanel.getInstance().repaint();
    }
    
    public static IView getInstance(){
        if (instance == null)
            instance = new View();
        return instance;
    }//EndGetInstance.

    @Override
    public void ShovelsetX(int x) {
       ShovelView.getInstance().ShovelsetX(x);
    }

    @Override
    public void ShovelsetY(int y) {
        ShovelView.getInstance().ShovelsetY(y);
    }

    @Override
    public int ShovelgetX() {
        return ShovelView.getInstance().ShovelgetX();
    }

    @Override
    public int ShovelgetY() {
        return ShovelView.getInstance().ShovelgetY();
    }

    @Override
    public Rectangle2D.Double getShovelRect() {
        return ShovelView.getInstance().getShovelRect();
    }

    @Override
     public void drawShovel(Graphics2D g2d){
        ShovelView.getInstance().drawShovel(g2d);
    }
    
    @Override
    public void soundShovel(){
        ShovelView.getInstance().soundShovel();
    }//EndSoundShovel.
    
}//EndView.
