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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static java.time.Clock.system;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import utils.Config;
import Model.GameModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;//maybe to avoid<--------------
import utils.ImportWizard;

public class GamePanel extends JPanel{
    
    private static GamePanel instance = null;
    
    //private GameModel modelHandler;
    private Grid plantGrid;
    private BufferedImage background = null;
    private BufferedImage gameOver = null;
    private BufferedImage counter = null;
    private BufferedImage[] activePlants;
    private BufferedImage[] inactivePlants;
    private ArrayList<AbstractZombieView>[] zombieList;
    private AbstractPlantView[][] plantMap;
    private AbstractPlantView dragPlant;
    private ArrayList<IBulletView>[] bulletList;
    private ArrayList<ISunView> sunList;
    private ILawnMoverView[] lawnMoverMap;
    private ShovelView shovelView = null;
    private int plantMapSize = 0;
    private Timer tick;
    private int timerCnt;
    private Line2D.Double gameOverLine;
    private int scoreTmp;
    private int moneyTmp;
    //private viewListHandler list.
    
  
    private GamePanel(){
        
        super();
        this.shovelView = ShovelView.getInstance();
        this.timerCnt = 0;   
        this.plantGrid = new Grid(285 ,35 ,87 ,107);
        this.zombieList = new ArrayList[Model.GameModel.getInstance().getNumRowsOfBoard()];
        this.bulletList = new ArrayList [Model.GameModel.getInstance().getNumRowsOfBoard()];
        this.plantMap = new AbstractPlantView[Model.GameModel.getInstance().getNumRowsOfBoard()][Model.GameModel.getInstance().getNumColumnsOfBoard()];
        this.gameOverLine = new Line2D.Double(200, 30, 180, 550);
        this.scoreTmp = Model.GameModel.getInstance().getScore();
        this.moneyTmp = Model.GameModel.getInstance().getMoney();
        this.dragPlant = null;
        this.activePlants = new BufferedImage[4];
        this.inactivePlants = new BufferedImage[4];
        this.sunList = new ArrayList<ISunView>();
        this.lawnMoverMap = new LawnMoverView[Model.GameModel.getInstance().getNumRowsOfBoard()];
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
            this.zombieList[i] = new ArrayList<AbstractZombieView>();
            this.bulletList[i] = new ArrayList<IBulletView>();
        }//EndFor.
        importMedia();
    }//EndBuilder.
    
    public Rectangle2D.Double getCell(int i, int j){
        return this.plantGrid.getCell(i, j);
    }//EndGetCell.
    
    public void dragAnimationPlant(AbstractPlantView newPlant, int x , int y){
        if (newPlant != null){
            newPlant.setX(x);
            newPlant.setY(y);
            this.dragPlant = newPlant;
        }else
           this.dragPlant = null;
       
    }//EndDragAnimationPlant.
    
    public void setShovel(ShovelView newShovel){
       this.shovelView = newShovel;
    }//EndSetShovel.
    
    public void dragAnimationShovel(int x, int y){
        this.shovelView.ShovelsetX(x);
        this.shovelView.ShovelsetY(y);
    }//EndDragAnimationShovel.
    
    public void addZombieView(AbstractZombieView newZombie){
        //System.out.println(""+Model.GameModel.getInstance().getRowIndex(newZombie.getY() + 50));
        this.zombieList[Model.GameModel.getInstance().getRowIndex(newZombie.getY() + 50)].add(newZombie);
    }//EndAdd.Zombie.
    
    public void removeZombieView(int i, int j){
        this.zombieList[i].remove(j);
    }//EndRemoveZombieView.
    
    public void addPlantView(AbstractPlantView newPlant, int x, int y){
        
        int i = Model.GameModel.getInstance().getRowIndex(y);
        int j = Model.GameModel.getInstance().getColumnIndex(x);
        Rectangle2D.Double tmpRect = this.plantGrid.getCell(i, j);
        newPlant.setX((int) tmpRect.getX());
        newPlant.setY((int) tmpRect.getY());
        this.plantMap[i][j] = newPlant;
        this.plantMapSize++;
        
    }//EndAddPlant.
    
    public void removePlantView(int i, int j){
        this.plantMap[i][j] = null;
    }//EndRemovePlantView.
    
    public void addLawnMoverView(ILawnMoverView newLawnMover,int index){
        this.lawnMoverMap[index] = newLawnMover;
    }//EndAddLawnMover.
    
    public void removeLawnMoverView(int index){
        this.lawnMoverMap[index] = null;
    }//EndRemoveLawnMover.
    
    public void addBulletView(int index, IBulletView newBullet){
        this.bulletList[index].add(newBullet);
    }//EndAddBullet.
    
    public void removeBulletView(int i, int j){
        this.bulletList[i].remove(j);
    }//EndRemoveBulletView.
    
    public IBulletView getFirstBullet(int index){
        if (this.bulletList[index].size() == 0)
            return null;
        else
            return this.bulletList[index].get(0);
    }//EndGetFirsBulet.
    
    public void addSunView(ISunView newSun){
        this.sunList.add(newSun);
    }//EndAddSunView.
    
    public void removeSunView(ISunView oldSun){
        this.sunList.remove(oldSun);
    }//EndSunView.
    
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //if (!Model.GameModel.getInstance().isGameOver()){//if is not game over.
            
        //Generate the grid under the background.
            this.plantGrid.createGrid(g2d);
            this.plantGrid.createCells(g2d);
        
        //Paint background.
            drawBackGroundScreen(g2d);//private.
            
        //PaintShovel.
            this.shovelView.drawShovel(g2d);
        //PaintLawnMovers.
            drawLawnMovers(g2d);
        
        //Paint drag plant.
            drawDrag(g2d);//private.
        
        //Paint all zombies
            drawZombieList(g2d);//private.
        
        //Paint all plants.
            drawPlantList(g2d);//private.
        
        //Paint all suns.
            drawSun(g2d);
        
        //paint all bullets.
            drawBullets(g2d);
            
            
            //g2d.draw(new Line2D.Double(200, 30, 180, 550));
        //}//EndIf.
        /*else 
            drawGameOver(g2d);*/
    }//EndPaintComponent.
   
//Private methods---------------------------------------------------------------
    private void drawBackGroundScreen(Graphics2D g2d){
        
        int money = Model.GameModel.getInstance().getMoney();
        if (this.scoreTmp < Model.GameModel.getInstance().getScore())
            this.scoreTmp++;
        if (this.moneyTmp < Model.GameModel.getInstance().getMoney())
            this.moneyTmp++;
        if (this.moneyTmp > Model.GameModel.getInstance().getMoney())
            this.moneyTmp--;
        
        g2d.drawImage(background, 0, 0, null);
        g2d.drawImage(counter, 5, 60, null); 
        g2d.drawImage(counter, 5, 560, null);
        g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,25));
        
        
        /*g2d.drawString("" + money, 15, 582);
        g2d.drawString("" + this.modelHandler.getScore(), 15, 82);*/
        g2d.drawString("" + this.moneyTmp, 15, 582);
        g2d.drawString("" + this.scoreTmp, 15, 82);
        if (money >=100)
            g2d.drawImage(activePlants[0], 0, 86, null);
        else
            g2d.drawImage(inactivePlants[0], 0, 86, null);
        if (money >= 50)
            g2d.drawImage(activePlants[1], 0, 181, null);
        else
            g2d.drawImage(inactivePlants[1], 0, 181, null);
        if (money >= 25)
            g2d.drawImage(activePlants[2], 0, 275, null);
        else
             g2d.drawImage(inactivePlants[2], 0, 275, null);
        if (money >= 125)
            g2d.drawImage(activePlants[3], 0, 371, null);
        else
            g2d.drawImage(inactivePlants[3], 0, 371, null);
    }//EndDrawBackGroundScreen.
    
    private void drawDrag(Graphics2D g2d){
        /*if (this.dragPlant.getX()> 0 || this.dragPlant.getX()> 0)
            this.dragPlant.drawPlant(g2d);*/
        if (this.dragPlant != null)
            this.dragPlant.drawPlant(g2d);
        
    }//EndDrawDrag.
    
    private void drawPlantList(Graphics2D g2d){
        
        if (this.plantMapSize > 0){
            for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
                for (int j=0;j<Model.GameModel.getInstance().getNumColumnsOfBoard();j++){
                    if (this.plantMap[i][j] != null)
                        this.plantMap[i][j].drawPlant(g2d);
                }//EndjFor.
            }//EndiFor.
        }//EndIf. 
        
    }//EndDrawPlantList.
    
    private void drawZombieList(Graphics2D g2d){
        
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
            if (zombieList[i].size() > 0){
                for (int j=0;j<zombieList[i].size();j++)
                    zombieList[i].get(j).drawZombie(g2d);
            }//EndIf.   
        }//EndFor.
        
    }//EndDrawZombieList.
    
    private void drawSun(Graphics2D g2d){
        if (this.sunList.size() != 0 ){
            for (int i=0;i<this.sunList.size();i++)
                this.sunList.get(i).drawSun(g2d);
        }//EndIf.
    }//EndDrawSun.
    
    private void drawBullets(Graphics2D g2d){
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
            if (bulletList[i].size() > 0)
                for (int j=0;j<this.bulletList[i].size();j++)
                    bulletList[i].get(j).drawBullet(g2d);
        }//EndFor.
    }//EndDrawBullets.
    
    private void drawLawnMovers(Graphics2D g2d){
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
            if (this.lawnMoverMap[i] != null)
                this.lawnMoverMap[i].drawLanwnMover(g2d);
        }
    }//EndDrawLawnMovers.
    
    /*private void drawGameOver(Graphics2D g2d){
         g2d.drawImage(this.gameOver, 0, 0,null);
         g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
         g2d.setColor(Color.WHITE);
         g2d.drawString("" + Model.GameModel.getInstance().getScore(), 570, 566);
    }//EndDrawGameOver.*/
    
    public Line2D.Double getGameOverLine(){
        return this.gameOverLine;
    }//EndGetGameOverLine.
    
//------------------------------------------------------------------------------
    /*public GameModel getModel(){
        return Model.GameModel.getInstance();
    }//EndGetModel.*/
    
    private void importMedia(){
        for (int i=0;i<4;i++)
            this.activePlants[i] = ImportWizard.getInstance().importGamePanelMedia()[i];
        for (int i=4;i<8;i++)
            this.inactivePlants[i-4] = ImportWizard.getInstance().importGamePanelMedia()[i]; 
        //this.background = ImageIO.read(new File(Config.getInstance().getBackGround()));
        this.counter = ImportWizard.getInstance().importGamePanelMedia()[8];
        this.background = ImportWizard.getInstance().importGamePanelMedia()[9];
        //this.gameOver = ImportWizard.getInstance().importGamePanelMedia()[9];
       /* try{
            this.background = ImageIO.read(new File(Config.getInstance().getBackGround()));
        }catch(Exception e){
            e.printStackTrace();
        }*/
        /*try{
            
            String path[] = new String[10];
            path[0] = "\\conf\\images\\active_peashooter.png";
            path[1] = "\\conf\\images\\active_sunflower.png";
            path[2] = "\\conf\\images\\active_walnut.png";
            path[3] = "\\conf\\images\\active_beetroot.png";
            path[4] = "\\conf\\images\\inactive_peashooter.png";
            path[5] = "\\conf\\images\\inactive_sunflower.png";
            path[6] = "\\conf\\images\\inactive_walnut.png";
            path[7] = "\\conf\\images\\inactive_beetroot.png"; 
            path[8] = "\\conf\\images\\Counter.png";
            path[9] = "\\conf\\images\\gameOver.jpg";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                path[0] = "/conf/images/active_peashooter.png";
                path[1] = "/conf/images/active_sunflower.png";
                path[2] = "/conf/images/active_walnut.png";
                path[3] = "/conf/images/active_beetroot.png";
                path[4] = "/conf/images/inactive_peashooter.png";
                path[5] = "/conf/images/inactive_sunflower.png";
                path[6] = "/conf/images/inactive_walnut.png";
                path[7] = "/conf/images/inactive_beetroot.png";
                path[8] = "/conf/images/Counter.png";
                path[9] = "/conf/images/gameOver.jpg";
            }//endif.
            
            this.activePlants[0] = ImageIO.read(new File(Config.getInstance().getPath() + path[0])); 
            this.activePlants[1] = ImageIO.read(new File(Config.getInstance().getPath() + path[1]));
            this.activePlants[2] = ImageIO.read(new File(Config.getInstance().getPath() + path[2]));
            this.activePlants[3] = ImageIO.read(new File(Config.getInstance().getPath() + path[3]));
            this.inactivePlants[0] = ImageIO.read(new File(Config.getInstance().getPath() + path[4])); 
            this.inactivePlants[1] = ImageIO.read(new File(Config.getInstance().getPath() + path[5]));
            this.inactivePlants[2] = ImageIO.read(new File(Config.getInstance().getPath() + path[6]));
            this.inactivePlants[3] = ImageIO.read(new File(Config.getInstance().getPath() + path[7]));  
            this.counter = ImageIO.read(new File(Config.getInstance().getPath() + path[8]));
            this.gameOver = ImageIO.read(new File(Config.getInstance().getPath() + path[9]));
            this.background = ImageIO.read(new File(Config.getInstance().getBackGround()));
            
        }catch(Exception e){
            System.out.println("Error loading background");
        }//EndCatch.*/
    }//EndImportMedia.
    
    public void addPanelListener(MouseInputListener listener){
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }//EndAddPanelListener.
    
    public static GamePanel getInstance(){
        if (instance == null)
            instance = new GamePanel();
        return instance;
    }//EndGetInstance.
}//EndGamePanel.
