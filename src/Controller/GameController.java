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
import Model.IModel;
import View.PeaShooterView;
import View.AbstractPlantView;
import View.AbstractZombieView;
import View.BulletView;
import View.View;
import View.NormalZombieView;
import View.WalnutView;
import View.BeetRootView;
import View.SunflowerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;
import utils.Config;
import utils.ImportWizard;
import utils.RankingHandler;

public class GameController implements ActionListener, MouseInputListener {
    
    private static GameController instance = null;
    
    private ArrayList<AbstractZombieController>[] zombieList;
    private AbstractPlantController [][] plantMap;
    private AbstractPlantView dragPlant = null;
    private AbstractPlantController[] targetPlants;
    private AbstractZombieController[] targetZombie;
    private ArrayList<BulletController>[] bulletList;
    private ArrayList<SunController> sunList;
    private Rectangle2D.Double[] dragContain;
    private LawnMoverController[] lawnMoverBuffer;
    private Clip[] sounds;
    private boolean drag = false;
    private boolean dragShovel = false;
    //private boolean dragShovel = false;
    private boolean flag = true;
    private long animateTime = 0;
    private long moveZombie = 0;
    private long crowdZombieGen = 0;
    private long zombieGen = 0;
    private long shootTime = 0;
    private long bulletMove = 0;
    private long sunGen = 0;
    private int tmp = 5;
    Timer tic = null;
    
    private GameController(){
        
        
        View.getInstance().addPanelListener(this);//correggere con singleton.
        this.zombieList = new ArrayList[Model.GameModel.getInstance().getNumRowsOfBoard()];
        this.plantMap = new AbstractPlantController[Model.GameModel.getInstance().getNumRowsOfBoard()][Model.GameModel.getInstance().getNumColumnsOfBoard()];
        this.targetPlants = new AbstractPlantController[Model.GameModel.getInstance().getNumRowsOfBoard()];
        this.targetZombie = new AbstractZombieController[Model.GameModel.getInstance().getNumRowsOfBoard()];
        this.bulletList = new ArrayList[Model.GameModel.getInstance().getNumRowsOfBoard()];
        this.sunList = new ArrayList<SunController>();
        this.lawnMoverBuffer = new LawnMoverController[Model.GameModel.getInstance().getNumRowsOfBoard()];
        this.sounds = new Clip[6];
        importMedia();
        //---------------------to avoid inizialize with a private method--------
        this.dragContain = new  Rectangle2D.Double[4];
        for (int i=0;i<4;i++)
            this.dragContain[i] = new Rectangle2D.Double();
        this.dragContain[0].setRect(0, 86, 96, 96);
        this.dragContain[1].setRect(0, 181, 96, 96);
        this.dragContain[2].setRect(0, 275, 96, 96);
        this.dragContain[3].setRect(0, 371, 96, 96);
        //----------------------------------------------------------------------
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
            this.zombieList[i] = new ArrayList<AbstractZombieController>();
            this.bulletList[i] = new ArrayList<BulletController>();
        }//EndFor.
        //----------------------------------------------------------------------
        lawnMoversInit();
       
        //----------------------------------------------------------------------
        tic = new Timer(1, this);//Lo start viene fatto nel momento di avvio del gioco.
        //tic.start();
        /*if ((System.currentTimeMillis() % 6) == 0)
               addZombie(new NormalZombieController(1000 ,10, 0, this.zombieList[0].size()), 0);
           if ((System.currentTimeMillis() % 6) == 1)
               addZombie(new NormalZombieController(1000, 120, 1, this.zombieList[1].size()), 1);
           if ((System.currentTimeMillis() % 6) == 2)
               addZombie(new NormalZombieController(1000, 240, 2, this.zombieList[2].size()), 2);
           if ((System.currentTimeMillis() % 6) == 3)
               addZombie(new NormalZombieController(1000, 330,3, this.zombieList[3].size()), 3);
           if ((System.currentTimeMillis() % 6) == 4)
               addZombie(new NormalZombieController(1000, 440,4 , this.zombieList[4].size()), 4);*/
              /*addZombie(new NormalZombieController(1000, 120, 1, this.zombieList[1].size()), 1);
              addZombie(new NormalZombieController(1000, 120, 1, this.zombieList[1].size()), 1);*/
    }//EndGameController.
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {//Timer handl erevery ms.
        
       if (!Model.GameModel.getInstance().isGameOver()){ //NotGameOverDo...
           
            if(!this.sounds[0].isRunning())
                this.sounds[0].setMicrosecondPosition(0);
            this.sounds[0].start();
       
            updateTargetForZombies(); //CheckGamePlants.
            
            updateTargetForBullets();//CheckGameZombies.
        
            generateSunFromSunFlower();//CheckForSunFlowerGeneration.
       
            checkForLawnMoversCrash();//CheckForLawnMoversActivation.
       
            CheckGameOver();
            
            if (this.animateTime == 0){//Set the plant animation time.
                this.animateTime = System.currentTimeMillis() + 220;
            }//EndIf.
    
            if(this.moveZombie == 0){//Move and animate all zombies.
                this.moveZombie = System.currentTimeMillis() + 120;
            }
            
            if(this.bulletMove == 0){//<------------------------------bullet.
                this.bulletMove = System.currentTimeMillis() ;
            }//EndIf.
            
            if (this.zombieGen == 0){//Set the zombie generation time(Ransom).
                this.zombieGen = System.currentTimeMillis() + ((int) Math.floor(Math.random() * 10000));
            }//EndIf
            
            if (this.crowdZombieGen == 0){//Time for generate a crowd of zombie.
                this.crowdZombieGen = System.currentTimeMillis() + 60000;
            }//EndIf.
            
            if (this.shootTime == 0){//Set the shoot time.
                this.shootTime = System.currentTimeMillis() + 1500;
            }//EndIf
       
            if (this.sunGen == 0){//Set the sun generation time.
                this.sunGen = System.currentTimeMillis() + 25000;
            }//EndIf.
       
            if (this.animateTime <= System.currentTimeMillis()){//if is time to animete plant.
                this.animateTime = 0;
                animatePlants();
                animateSun();
            }//EndIf.
            
            if (this.moveZombie <= System.currentTimeMillis()){
                this.moveZombie = 0;
                moveZombie();
            }//EndIf.
            if (this.shootTime <= System.currentTimeMillis()){//Time to shoot.
                this.shootTime = 0; 
                shootBullets();
            }//EndIf.
             
            if (this.zombieGen <= System.currentTimeMillis()){//RandomZombieGeneration.
                this.zombieGen = 0;
                generateZombie();
            }//EndIf.
            
            if (this.crowdZombieGen <= System.currentTimeMillis()){
                this.crowdZombieGen = System.currentTimeMillis() + 5000 + ((int) Math.floor(Math.random() * 15000));
                generateZombieCrowd();
                //System.out.println("Entered");
            }//EndIf.
            
            if (this.sunGen <= System.currentTimeMillis()){
                this.sunGen = 0;
                generateSun();
            }//EndIf.
            
            if (this.bulletMove <= System.currentTimeMillis()){//<-----------------movimento proiettili con timer principale.
                this.bulletMove = 0;
                moveBullets();//Decommentare per muovere i proiettili tramite timer principale.
            }//EndIf.
            
            
       }//EndIsGameOverIf.
       else{
           
           if (flag){
                RankingHandler.getInstance().addPlayer(Model.GameModel.getInstance().getPlayerName(), ""+Model.GameModel.getInstance().getScore());
                flag = false;
           }//EndIfFlag.
           this.sounds[0].stop();
           /*this.sounds[2].start();
           if (!this.sounds[2].isRunning())
               this.sounds[3].start();*/
           Controller.getInstance().openGameOverWindow();
           Controller.getInstance().closeGame();
       }//EndIsGameOverElse.
           
       
       View.getInstance().repaint();//repaint every ms.
    }//ENDactionPerformed.
    

    @Override
    public void mouseClicked(MouseEvent e) {
             
       
    }//EndMouseClicked.

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Rectangle2D.Double mouseRect = new Rectangle2D.Double();
        mouseRect.setRect(e.getX(), e.getY(), 30, 30);
        //Click sun handler.
           for (int i=0;i<this.sunList.size();i++){
               if (this.sunList.get(i).checkCollision(mouseRect)){//If sun clicked...
                    Model.GameModel.getInstance().setMoney(Model.GameModel.getInstance().getMoney() + 25);//increment money.
                    this.sounds[5].setMicrosecondPosition(0);
                    this.sounds[5].start();
                    removeSun(this.sunList.get(i));//Remove the clicked sun.
               }//EndIf.
           }//EndFor.
           
        if (this.drag == true) {//PlantReleaseHandler.
            if (tmp == 0){//PeaShooter.
                try{
                    if (this.plantMap[Model.GameModel.getInstance().getRowIndex(e.getY())][Model.GameModel.getInstance().getColumnIndex(e.getX())] == null){
                        addPlant(new PeaShooterController(e.getX(), e.getY()), e.getX(), e.getY());//Piazza pianta selezionata.
                        Model.GameModel.getInstance().setMoney(Model.GameModel.getInstance().getMoney() - 100);//Aggiorna i soldi.
                        this.sounds[4].setMicrosecondPosition(0);
                        this.sounds[4].start();
                    }//EndIf.
                }catch(ArrayIndexOutOfBoundsException out){
                    System.out.println("Please make the plant on the garden");
                }finally{
                    View.getInstance().dragAnimationPlant(null, 0, 0);//EliminaEffettoDrag.
                }//EndFinally.
            }//EndTmp0.
            else if (tmp == 1){//Sunflower.
                try{
                    if (this.plantMap[Model.GameModel.getInstance().getRowIndex(e.getY())][Model.GameModel.getInstance().getColumnIndex(e.getX())] == null){
                        addPlant(new SunflowerController(e.getX(), e.getY()), e.getX(), e.getY());//Piazza pianta selezionata.
                        Model.GameModel.getInstance().setMoney(Model.GameModel.getInstance().getMoney() - 50);//Aggiorna i soldi.
                        this.sounds[4].setMicrosecondPosition(0);
                        this.sounds[4].start();
                    }//EndIf.
                }catch(ArrayIndexOutOfBoundsException out){
                    
                }finally{
                    View.getInstance().dragAnimationPlant(null, 0, 0);//EliminaEffettoDrag.
                }//EndFinally.
            }//EndTmp1.
            else if (tmp == 2){//Walnut.
                try{
                    if (this.plantMap[Model.GameModel.getInstance().getRowIndex(e.getY())][Model.GameModel.getInstance().getColumnIndex(e.getX())] == null){
                        addPlant(new WalnutController(e.getX(), e.getY()), e.getX(), e.getY());//Piazza pianta selezionata.
                        Model.GameModel.getInstance().setMoney(Model.GameModel.getInstance().getMoney() - 25);//Aggiorna i soldi.
                        this.sounds[4].setMicrosecondPosition(0);
                        this.sounds[4].start();
                    }//EndIf.
                }catch(ArrayIndexOutOfBoundsException out){
                    System.out.println("Please make the plant on the garden");
                }finally{
                    View.getInstance().dragAnimationPlant(null, 0, 0);//EliminaEffettoDrag.
                }//EndFinally.
                
                
            }//EndTmp2.
            else if (tmp == 3){//BeetRoot.
                try{
                    if (this.plantMap[Model.GameModel.getInstance().getRowIndex(e.getY())][Model.GameModel.getInstance().getColumnIndex(e.getX())] == null){
                        addPlant(new BeetRootController(e.getX(), e.getY()), e.getX(), e.getY());//Piazza pianta selezionata.
                        Model.GameModel.getInstance().setMoney(Model.GameModel.getInstance().getMoney() - 125);//Aggiorna i soldi.
                        this.sounds[4].setMicrosecondPosition(0);
                        this.sounds[4].start();
                    }//EndIf.
                }catch(ArrayIndexOutOfBoundsException out){
                    System.out.println("Please make the plant on the garden");
                }finally{
                    View.getInstance().dragAnimationPlant(null, 0, 0);//EliminaEffettoDrag.
                }//EndFinally.
            }//EndTmp3.
            
            this.drag = false;//elimina ogni effetto drag.
        }//EndIf.
        
        //ShovelReleaseHandler.
        if( (this.dragShovel == true) && (mouseRect.intersects(View.getInstance().getShovelRect())) ){
            try{
                removePlant(Model.GameModel.getInstance().getRowIndex(e.getY()), Model.GameModel.getInstance().getColumnIndex(e.getX()));
                //this.shovel.soundShovel();
                View.getInstance().soundShovel();
            }catch(ArrayIndexOutOfBoundsException out){
                System.out.println("No plants in here...");
            }finally{
                View.getInstance().dragAnimationShovel(1020, 10);
                this.dragShovel = false;
            }//EndFinally.
        }//EndIf.
        
    }//EnDMouseRelased.

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        Rectangle2D.Double mouseRect = new Rectangle2D.Double();
        mouseRect.setRect(e.getX(), e.getY(), 5, 5);
        for (int i=0;i<4;i++){
            
            if( (this.dragContain[i].intersects(mouseRect)) && (!this.drag) ){
                if ( (i == 0) &&  (Model.GameModel.getInstance().getMoney() >= 100) ){//DragPeaShooter.
                        this.drag = true;
                        dragPlant = new PeaShooterView();
                        this.tmp = 0;
                        //System.out.println("if entered 0.");
                        View.getInstance().dragAnimationPlant(dragPlant, e.getX() - 25, e.getY() - 25);
                }//EndMoney0.
                else if ((i == 1) &&  (Model.GameModel.getInstance().getMoney() >= 50)){//DragSunflower.
                        this.drag = true;
                        dragPlant = new SunflowerView();
                        this.tmp = 1;
                        //System.out.println("if entered 1.");
                        View.getInstance().dragAnimationPlant(dragPlant, e.getX() - 25, e.getY() - 25);
                }//EndMoney1.
                else if ((i == 2) &&  (Model.GameModel.getInstance().getMoney() >= 25)){//DragWalnut.
                        this.drag = true;
                        dragPlant = new WalnutView();
                        this.tmp = 2;
                        View.getInstance().dragAnimationPlant(dragPlant, e.getX() - 25, e.getY() - 25);
                }//EndMoney2.
                else if ((i == 3) &&  (Model.GameModel.getInstance().getMoney() >= 125)){//DragBeetRoot.
                        this.drag = true;
                        dragPlant = new BeetRootView();
                        this.tmp = 3;
                        View.getInstance().dragAnimationPlant(dragPlant, e.getX() - 25, e.getY() - 25);
                }//EndMoney3.
            }//EndIf.
            else if ( !(this.dragContain[i].intersects(mouseRect)) && (this.drag) ){//Una volta uscito da intersect.
                //System.out.println("Elese entered.");
                View.getInstance().dragAnimationPlant(dragPlant, e.getX() - 25, e.getY() - 25);
            }//EndElse
            
        }//EndFor.
        
        if ( (mouseRect.intersects(View.getInstance().getShovelRect())) && (!this.dragShovel) ){
            this.dragShovel = true;
            //System.out.println("drag true.");
            View.getInstance().dragAnimationShovel(e.getX() - 25, e.getY() - 25);
        }//EndIf.
        else if (this.dragShovel)
            View.getInstance().dragAnimationShovel(e.getX() - 25, e.getY() - 25);
    }//EndMouseDragged.

    @Override
    public void mouseMoved(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//Private-methods---------------------------------------------------------------
    
    private void addPlant(AbstractPlantController newPlant, int x, int y){
       View.getInstance().addPlantView(newPlant.getView(), x, y);
        Model.GameModel.getInstance().addPlantModel(newPlant.getModel(),Model.GameModel.getInstance().getRowIndex(y) ,Model.GameModel.getInstance().getColumnIndex(x));
        this.plantMap[Model.GameModel.getInstance().getRowIndex(y)][Model.GameModel.getInstance().getColumnIndex(x)] = newPlant;
    }//EndAddPlant.
    
    private void addZombie(AbstractZombieController newZombie, int index){
        View.getInstance().addZombieView(newZombie.getView());
        //Model.GameModel.getInstance().addZombieModel(index, newZombie.getX(), newZombie.getY());
        Model.GameModel.getInstance().addZombieModel(index, newZombie.getModel());
        this.zombieList[index].add(newZombie);
    }//EndAddZombie.
    
    private void removeZombie(int i, int j){
        this.zombieList[i].get(j).setTarget(null);//ForPlants.
        this.zombieList[i].remove(j);
        Model.GameModel.getInstance().removeZombieModel(i, j);
        View.getInstance().removeZombieView(i, j);
    }//EndRemoveZombie.
    
    private void addLawnMover(LawnMoverController newLawnMover , int index){
        Model.GameModel.getInstance().addLawnMoverModel(newLawnMover.getModel(), index);
        View.getInstance().addLawnMoverView(newLawnMover.getView(), index);
        this.lawnMoverBuffer[index] = newLawnMover;
    }//EndAddLawnMover.
    
    private void removeLawnMover(int index){
        Model.GameModel.getInstance().removeLawnMoverModel(index);
        View.getInstance().removeLawnMoverView(index);
        this.lawnMoverBuffer[index] = null;
    }//EndRemoveLawnMover.
    
    private void removePlant(int i, int j){
        View.getInstance().removePlantView(i, j);
        Model.GameModel.getInstance().removePlantModel(i, j);
        this.plantMap[i][j] = null; 
    }//EndRemovePlant.
    
    private void addBullet(BulletController newBullet, int index){
        Model.GameModel.getInstance().addBulletModel(index, newBullet.getModel());
        View.getInstance().addBulletView(index, newBullet.getView());
        this.bulletList[index].add(newBullet);
        
    }//EndAddBullet.
    
    private void removeBullet(int i, int j){
        Model.GameModel.getInstance().removeBulletModel(i, j);
        View.getInstance().removeBulletView(i, j);
        this.bulletList[i].get(j).setTarget(null);
        this.bulletList[i].remove(j);
    }//EndRemoveBullet.
    
    private void addSun(SunController newSun){
        this.sunList.add(newSun);
        View.getInstance().addSunView(newSun.getView());
    }//EndAddSun.
    
    private void removeSun(SunController oldSun){
        this.sunList.remove(oldSun);
        View.getInstance().removeSunView(oldSun.getView());
        oldSun = null;
    }//EndRemoveSun.
    
    private void animatePlants(){
        
          //addZombie(new NormalZombieController(800 ,10), 1);
          //this.gameView.addZombieView(new NormalZombieView(800,10));
           for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//animate plant.
               for (int j=0;j<Model.GameModel.getInstance().getNumColumnsOfBoard();j++){
                   if (this.plantMap[i][j] != null){
                       this.plantMap[i][j].animate();
                   }//EndifCheckForNull.
               }//EndJFor.
           }//EndIFor.
    }//EndAnimate.
    
    private void animateSun(){
        for (int i=0;i<this.sunList.size();i++ )
            this.sunList.get(i).animate();
    }//EndAnimateSun.
    
    private void moveBullets(){
        
         for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
             for (int j=0;j<this.bulletList[i].size();j++){
                if ((this.bulletList[i].get(j).getTarget() != null)  && (this.bulletList[i].get(j).getView().getRect().intersects(this.bulletList[i].get(j).getTarget().getView().getRect())) && !this.bulletList[i].get(j).getStrike() ){
                    this.bulletList[i].get(j).getTarget().getModel().hit(this.bulletList[i].get(j).getModel().getDamege());
                    //System.out.println("" + this.target.getModel().getLife());
                    this.bulletList[i].get(j).setStrike(true);
                }//EndClass.
                else
                    this.bulletList[i].get(j).moveRight();
             }//EndJFor.
         }//EndIFor.
         
    }//EndMoveBullets.
    
    private void moveZombie(){
         
       for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//UpdateTargetBuffer.
           for (int j=0;j<this.zombieList[i].size();j++){
               if (!this.zombieList[i].get(j).getClip().isRunning())
                    this.zombieList[i].get(j).getClip().setMicrosecondPosition(0);
               if (this.zombieList[i].get(j).canMove()){//se non ce nulla muoviti.
                    this.zombieList[i].get(j).animate();
                    this.zombieList[i].get(j).moveZombie();
               }//EndIf.
               else {//altrimenti attacca se esiste bersaglio o ricomcia a camminare se il bersaglio e morto.
                    if ( (this.zombieList[i].get(j).getTarget() != null) && ((this.zombieList[i].get(j).checkCollision((this.zombieList[i].get(j).getTarget().getRectView())))) ){
                        this.zombieList[i].get(j).getTarget().getModel().hit(20);//Hit the target.
                        this.zombieList[i].get(j).getClip().start();
                        this.zombieList[i].get(j).animate();//keep going animation
                
                    }//EndAttackIf.
                    else{
                        this.zombieList[i].get(j).setMove(true);
                    }
               }//EndElse;
               if (this.zombieList[i].get(j).getTarget() != null){//se esiste un bersaglio cerca collisione ad agni passo.
                    if (this.zombieList[i].get(j).checkCollision(this.zombieList[i].get(j).getTarget().getRectView())){
                       this.zombieList[i].get(j).setMove(false);
                    }//endif.
                }//EndIf.
           }//EndJFor.
       }//EndIFor.
        
    }//EndMoveZombie.
    
    private void generateSun(){
        int i = ((int) Math.floor(Math.random() * 5));
        int j = ((int) Math.floor(Math.random() * 9));
        addSun(new SunController((int)View.getInstance().getCell(i,j).getX(), (int)View.getInstance().getCell(i,j).getY()));
    }//EndgenerateSun.
    
    
    private void generateZombie(){
        
           int cnt=0;
           for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
               if (this.zombieList[i].size() == 0)
                   cnt++;
           }//EndFor.
           if (cnt == Model.GameModel.getInstance().getNumRowsOfBoard())
               this.sounds[1].start();
           if ((System.currentTimeMillis() % 6) == 0)
               addZombie(new NormalZombieController(1000 ,10), 0);
           if ((System.currentTimeMillis() % 6) == 1)
               addZombie(new NormalZombieController(1000, 120), 1);
           if ((System.currentTimeMillis() % 6) == 2)
               addZombie(new NormalZombieController(1000, 240), 2);
           if ((System.currentTimeMillis() % 6) == 3)
               addZombie(new NormalZombieController(1000, 330), 3);
           if ((System.currentTimeMillis() % 6) == 4)
               addZombie(new NormalZombieController(1000, 440), 4);
           /*if ((System.currentTimeMillis() % 6) == 0)
               addZombie(new NormalZombieController(1000 ,10, 0, this.zombieList[0].size()), 0);
           if ((System.currentTimeMillis() % 6) == 1)
               addZombie(new NormalZombieController(1000, 120, 1, this.zombieList[1].size()), 1);
           if ((System.currentTimeMillis() % 6) == 2)
               addZombie(new NormalZombieController(1000, 240, 2, this.zombieList[2].size()), 2);
           if ((System.currentTimeMillis() % 6) == 3)
               addZombie(new NormalZombieController(1000, 330,3, this.zombieList[3].size()), 3);
           if ((System.currentTimeMillis() % 6) == 4)
               addZombie(new NormalZombieController(1000, 440,4 , this.zombieList[4].size()), 4);*/
           /*if ((System.currentTimeMillis() % 6) == 5){
                addZombie(new NormalZombieController(1100 ,10), 0);
                addZombie(new NormalZombieController(1100, 120), 1);
                addZombie(new NormalZombieController(1100, 240), 2);
                addZombie(new NormalZombieController(1100, 330), 3);
                addZombie(new NormalZombieController(1100, 440), 4);
           }//Endif.*/
        
    }//EndGenerateZombie.
    
    private void generateZombieCrowd(){//GenerateACrowdOfZombie.
        if ((System.currentTimeMillis() % 2) == 0){
            addZombie(new NormalZombieController(1100 ,10), 0);
            addZombie(new NormalZombieController(1100, 120), 1);
            addZombie(new NormalZombieController(1100, 240), 2);
            addZombie(new NormalZombieController(1100, 330), 3);
            addZombie(new NormalZombieController(1100, 440), 4);
        }//EndIf.
        if ((System.currentTimeMillis() % 2) == 1){
            addZombie(new NormalZombieController(1100 ,10), 0);
            addZombie(new NormalZombieController(1100, 120), 1);
            addZombie(new NormalZombieController(1100, 240), 2);
            addZombie(new NormalZombieController(1100, 330), 3);
            addZombie(new NormalZombieController(1100, 440), 4);
            //Two times.
            addZombie(new NormalZombieController(1100 ,10), 0);
            addZombie(new NormalZombieController(1100, 120), 1);
            addZombie(new NormalZombieController(1100, 240), 2);
            addZombie(new NormalZombieController(1100, 330), 3);
            addZombie(new NormalZombieController(1100, 440), 4);
        }//EndIf.
    }//EndGenerateZombieCrowd.
    
    private void updateTargetForZombies(){
        
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//UpdateTargetBuffer.
            for (int j=0;j<Model.GameModel.getInstance().getNumColumnsOfBoard() - 1;j++){
                if (this.plantMap[i][j] != null){
                    if (this.plantMap[i][j+1] != null){
                        if (this.plantMap[i][j].getModel().getX()> this.plantMap[i][j+1].getModel().getX())
                            this.targetPlants[i] = this.plantMap[i][j];
                        else
                            this.targetPlants[i] = this.plantMap[i][j+1];
                    }//EndIf.
                    else{
                        this.targetPlants[i] = this.plantMap[i][j];
                    }//EndElse.
                }//EndIf.
            }//EndJFor.
            
            if (this.plantMap[i][Model.GameModel.getInstance().getNumColumnsOfBoard() - 1] != null)//Caso delle ultime colonne.
                this.targetPlants[i] = this.plantMap[i][Model.GameModel.getInstance().getNumColumnsOfBoard() - 1];
        }//EndIFor.
        
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//SetTargetToAllZombies.
            for (int j=0;j<this.zombieList[i].size();j++){
                this.zombieList[i].get(j).setTarget(this.targetPlants[i]);
            }//EndFor.
        }//EndFor.
        
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//CheckForDeadPlantsAnimation.
            for (int j=0;j<Model.GameModel.getInstance().getNumColumnsOfBoard();j++){
                if (this.plantMap[i][j] != null){
                    if (this.plantMap[i][j].getModel().getLife()<= 0){
                        this.plantMap[i][j].getModel().setAlive(false);
                        //this.targetPlants[i] = null;//UpdateTargetBuffer
                    }//EndIf
                }//EndNullFor.
            }//EndJFor.
        }//EndiFor.
        
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//CheckDeadPlants.
            for (int j=0;j<Model.GameModel.getInstance().getNumColumnsOfBoard();j++){
                if (this.plantMap[i][j] != null){
                    if (this.plantMap[i][j].getDeadAnimationComplete()){
                        removePlant(i, j);//RemovePlant.
                        this.targetPlants[i] = null;//UpdateTargetBuffer
                    }//EndIf
                }//EndNullFor.
            }//EndJFor.
        }//EndiFor.
        
        
        
    }//EndUpdateTargetForZombies.
    
    private void updateTargetForBullets() {
        
       for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//UpdateTargetBuffer.
          if(this.zombieList[i].size() != 0)
              this.targetZombie[i] = this.zombieList[i].get(0);
          else 
              this.targetZombie[i] = null;
       }//EndIf.
       
       
       for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//SetTargetToAllBullets.
           for (int j=0;j<this.bulletList[i].size();j++){
               this.bulletList[i].get(j).setTarget(this.targetZombie[i]);
           }//EndJFor.
       }//EndIFor.
       
       
       for(int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//CheckForStrikeBullets.
           for (int j=0;j<this.bulletList[i].size();j++){
               if (this.bulletList[i].get(j).getStrike() == true)
                   removeBullet(i, j);
           }//EndJFor.
       }//EndIFor.
        
       for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//CheckForGhoastBullets.
           for (int j=0;j<this.bulletList[i].size();j++){
              if(this.bulletList[i].get(j).getModel().getX() >= 1100) 
                  removeBullet(i, j);
           }//EndJFor
       }//EndIFor.
       
//Remove animate and remove dead zombies.

    
       for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//CheckForDeadZombiesAnimation.
            for (int j=0;j<this.zombieList[i].size();j++){
                if (this.zombieList[i].get(j).getModel().getLife() <= 0)
                    this.zombieList[i].get(j).getModel().setAlive(false);
            }//EndFor.
        }//EndiFor.


        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//RemoveZombiesCompletedDeadAnimation.
            for (int j=0;j<this.zombieList[i].size();j++){
                if (this.zombieList[i].get(j).getDeadAnimationComplete()){
                    removeZombie(i, j);
                    Model.GameModel.getInstance().incrementScore(Model.GameModel.getInstance().getScore() + 5);
                }//EndIf.
            }//EndFor.
        }//EndiFor.
       
        
    }//EndUpdateTargetForBullets.
    
    private void shootBullets(){
        
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
            for (int j=0;j<Model.GameModel.getInstance().getNumColumnsOfBoard();j++){
                if ( (this.plantMap[i][j] != null) && ( this.plantMap[i][j].canShoot() ) && (this.zombieList[i].size() != 0) ){//verifica se zombie nella riga di un peashooter.
                    addBullet(this.plantMap[i][j].shoot(this.zombieList[i].get(0)), i);
                }//EndIf.
            }//EndJFor.
        }//EndIFor.
        
    }//EndShootBullets
    
    private void generateSunFromSunFlower(){
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
            for (int j=0;j<Model.GameModel.getInstance().getNumColumnsOfBoard();j++){
                if ((this.plantMap[i][j] != null) && (this.plantMap[i][j].getLastGenerationTime() != 0) && ((this.plantMap[i][j].getLastGenerationTime() + 10000) <= System.currentTimeMillis()) )
                    addSun(this.plantMap[i][j].generateSun());
            }//EndJFor.
       }//EndIFor.
    }//EndGenerateSunFromSunFlower.
    
    private void lawnMoversInit(){
       addLawnMover(new LawnMoverController(200, 45), 0);
       addLawnMover(new LawnMoverController(195, 150), 1);
       addLawnMover(new LawnMoverController(190, 250), 2);
       addLawnMover(new LawnMoverController(180, 370), 3);
       addLawnMover(new LawnMoverController(180, 475), 4);
    }//EndLawMoversInit.
    
    private void checkForLawnMoversCrash(){
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//CheckForZombieContact.
            for (int j=0;j<this.zombieList[i].size();j++){
                if( (this.lawnMoverBuffer[i] != null) && (this.lawnMoverBuffer[i].checkCollision(this.zombieList[i].get(j).getView().getRect())) )
                    this.lawnMoverBuffer[i].getModel().setActive(false);
                if ( (this.lawnMoverBuffer[i] != null) && (this.lawnMoverBuffer[i].checkCollision(this.zombieList[i].get(j).getView().getRect())) && (!this.lawnMoverBuffer[i].getModel().isActive()) )
                    this.zombieList[i].get(j).getModel().setAlive(false);
            }//EndJFor.
        }//EndiFor.
        
         for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){//MoveIfLawnMoverContacted.
             if  ( (this.lawnMoverBuffer[i] != null) && (!this.lawnMoverBuffer[i].getModel().isActive()) ){
                 this.lawnMoverBuffer[i].AnimateAndMove();
                 this.lawnMoverBuffer[i].getView().soundLawnMover();
                 if (this.lawnMoverBuffer[i].getView().getX() > 1100)
                     removeLawnMover(i);
             }//EndIf
         }//EndiFor.
    
         
         /*for(int i=0;i<this.gameModel.getNumRowsOfBoard();i++){
             
         }//EndIFor.*/
    }//EndCheckForLawnMoversCrash()
    
    
    private void CheckGameOver(){
        for (int i=0;i<Model.GameModel.getInstance().getNumRowsOfBoard();i++){
                for (int j=0;j<this.zombieList[i].size();j++){
                    if  (View.getInstance().getGameOverLine().intersects(this.zombieList[i].get(j).getView().getRect()) ) 
                        Model.GameModel.getInstance().setGameOver(true);
                }//EndJFor.
            }//EndIFor
    }//EndCheckGameOver.
    
    private void importMedia(){
       
        this.sounds = ImportWizard.getInstance().importGameSound();
        /*try{//ImportImage.
            String path[] = new String[6];
          
            path[0] = "\\conf\\sounds\\background.wav";
            path[1] = "\\conf\\sounds\\zombies_coming.wav";
            path[2] = "\\conf\\sounds\\atebrains.wav";
            path[3] = "\\conf\\sounds\\game_end.wav";
            path[4] = "\\conf\\sounds\\click.wav";
            path[5] = "\\conf\\sounds\\sun.wav";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                path[0] = "/conf/sounds/background.wav";
                path[1] = "/conf/sounds/zombies_coming.wav";
                path[2] = "/conf/sounds/atebrains.wav";
                path[3] = "/conf/sounds/game_end.wav";
                path[4] = "/conf/sounds/click.wav";
                path[5] = "/conf/sounds/sun.wav";
            }//endif.
        
            AudioInputStream stream1 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[0]))));
            AudioInputStream stream2 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[1]))));
            AudioInputStream stream3 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[2]))));
            AudioInputStream stream4 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[3]))));
            AudioInputStream stream5 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[4]))));
            AudioInputStream stream6 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[5]))));
            this.sounds[0] = AudioSystem.getClip();
            this.sounds[1] = AudioSystem.getClip();
            this.sounds[2] = AudioSystem.getClip();
            this.sounds[3] = AudioSystem.getClip();
            this.sounds[4] = AudioSystem.getClip();
            this.sounds[5] = AudioSystem.getClip();
            this.sounds[0].open(stream1);
            this.sounds[1].open(stream2);
            this.sounds[2].open(stream3);
            this.sounds[3].open(stream4);
            this.sounds[4].open(stream5);
            this.sounds[5].open(stream6);
        }//Endtry.
        catch(Exception e){
            
            System.out.println("Error importig game media"); 
            
       }//EndImporting.*/
    }//EndImportMedia.
    
//Public methods----------------------------------------------------------------
    public static GameController getInstance(){
        if (instance == null)
            instance = new GameController();
	return instance;
    }//EndGetInstace.
    
    
    /*public void startGame(String player){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                Model.GameModel.getInstance().setPlayerName(player);
                tic.start();
                //new GameWindow(GamePanel.getInstance()).setVisible(true);//Maybe implement pattern Singleton
            	View.getInstance().openGameWindow();
                //GameView.setVisible(true);
            }//EndRunThread.
        });
    }//EndStartGame.*/
    
    public void start(){
        tic.start();
    }//EndStartTimer.
    
    public void stop(){
        tic.stop();
    }//EndStopTimer.
    
    /*
    public void openMainGui(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                //MainWindow.getInstance().setVisible(true);
                View.getInstance().openMainWindow();
            }//EndRunThread.
        });
    }//EndStartMainGui.
    
    public void openInsertPlayerName(){
        View.getInstance().openInsertPlayerWindow();
    }//EndOpenInsertName.
    
    public void closeInsertPlayerName(){
        View.getInstance().closeInsertPlayerWindow();
    }//EndCloseInsertPlayerName.
    
    public void closeMainGui(){
        //MainWindow.getInstance().setVisible(false);
        View.getInstance().closeMainWindow();
    } //EndCloseMainGui.
    */
    
    
}//EndGameController.
