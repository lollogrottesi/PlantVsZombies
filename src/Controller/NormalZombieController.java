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

import View.NormalZombieView;
import View.AbstractZombieView;
import Model.NormalZombieModel;
import Model.AbstractZombieModel;
import Model.GameModel;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import utils.Config;

public class NormalZombieController extends AbstractZombieController implements ActionListener{
    
    private AbstractZombieModel zombieModel = null;
    private AbstractZombieView zombieView = null;
    private AudioInputStream chompSound = null;
    private Clip clip;
    private Timer time = null;
    private AbstractPlantController target = null;
    boolean move;
    boolean deadAnimationComplete = false;
    //int i = 0;
    //int j = 0;
    //int x, y = 0;
    public NormalZombieController(int x, int y/*, int i , int j*/){
        this.move = true;
        /*this.i = i;
        this.j = j;
        this.x = x;
        this.y = y;*/
        this.zombieModel = new NormalZombieModel(x, y);
        this.zombieView = new NormalZombieView(x, y);
        importMedia();
        this.time = new Timer(200, this);
        //this.time.start();
    }//EndBuilder.
    
   /* public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }*/
    
    public boolean getDeadAnimationComplete(){
        return this.deadAnimationComplete;
    }//EndGetDeadAnimationComplete.
    
    public boolean canMove(){
        return this.move;
    }//EndCanMove.
    
    public void setMove(boolean newMove){
        this.move = newMove;
    }//EndSetMove.
    
    public AbstractZombieView getView(){
        return this.zombieView;
    }//EndGetView.
   
    public AbstractZombieModel getModel(){
        return this.zombieModel;
        //return GameModel.getInstance().getZombieModel(i, j);
    }//EndGetModel
    
    public Clip getClip(){
        return this.clip;
    }//EndGetClip.
    
    public void clipStart(){
        this.clip.start();
    }//EndClipStart.
    
    public void moveZombie(){
        
        this.zombieView.setX(this.zombieView.getX() - 10);
        this.zombieModel.setX(this.zombieModel.getX() - 10);
        //GameModel.getInstance().getZombieModel(i, j);
    }//EndMoveLeft.*/
        
    public AbstractPlantController getTarget(){
        return this.target;
    }//EndGetTarget.
    
    public void setTarget(AbstractPlantController newTarget){
        this.target = newTarget;
    }//EndSetTarget.
    
    public void animate(){
        
       if(this.zombieModel.isAlive())
            this.zombieView.setNextImageIndex((this.zombieView.getNextImageIndex() + 1) % 2);
      /* if(GameModel.getInstance().getZombieModel(i, j).isAlive())
            this.zombieView.setNextImageIndex((this.zombieView.getNextImageIndex() + 1) % 2);*/
       else{
            this.zombieView.setNextImageIndex((this.zombieView.getNextImageIndex() + 1) % 8);
            if ( (this.zombieView.getNextImageIndex() != 0) &&  (((this.zombieView.getNextImageIndex()+1) % 8) == 0) ){
                this.deadAnimationComplete = true;
                //System.out.println("DeadAnimationComplete");
            }
       }//EndElse.
       
    }//EndAnimate.
    
    public Rectangle2D.Double getRectView(){
        return this.zombieView.getRect();
    }//EndGetRectView.
    
    public boolean checkCollision(Rectangle2D.Double crashRect){
        if (getRectView().intersects(crashRect))
            return true;
        else 
            return false;
    }//EndCheckCollision.

    @Override
    public void actionPerformed(ActionEvent e) {
        
       if (!this.clip.isRunning())
           this.clip.setMicrosecondPosition(0);
       
        if (this.move){//se non ce nulla muoviti.
            animate();
            moveZombie();
            
        }//EndIf.
        else {//altrimenti attacca se esiste bersaglio o ricomcia a camminare se il bersaglio e morto.
            if ( (this.target != null) && (checkCollision(this.target.getRectView()))){
                this.target.getModel().hit(20);//Hit the target.
                this.clip.start();
                animate();//keep going animation
                
            }//EndAttackIf.
            else{
                this.move = true;
            }
        }//EndElse;
        if (this.target != null){//se esiste un bersaglio cerca collisione ad agni passo.
            if (checkCollision(this.target.getRectView())){
                this.move = false;
            }//endif.
        }//EndIf.
    }//EndActionPerformed
    
    private void importMedia(){
        try{//ImportImage.
            String path = new String();
          
            path = "\\conf\\sounds\\chomp.wav";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                path = "/conf/sounds/chomp.wav";
            }//endif.
        
        this.chompSound = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path))));
        this.clip = AudioSystem.getClip();
        this.clip.open(this.chompSound);
        }//Endtry.
        catch(Exception e){
            
            System.out.println("Error importig zombie media"); 
            
       }//EndImporting.
    }//EndImportMedia.
}//EndNormalZombieController.
