/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import utils.Config;
import utils.ImportWizard;
import utils.RankingHandler;



/**
 *
 * @author lorenzo
 */
public class MainGui extends JPanel implements MouseInputListener{
    
    private static MainGui instance = null;
    
    private BufferedImage[] mainBackground = null;
    private int backgroundSelector = 0;
    private Rectangle2D.Double[] rect;
    private Clip[] clip;
    private boolean soundMove = true ;
    
    private MainGui(){
        super();
        importMedia();
        addMouseListener(this);
        addMouseMotionListener(this);
        this.rect = new Rectangle2D.Double[2];
        this.rect[0] = new Rectangle2D.Double();
        this.rect[1] = new Rectangle2D.Double();
        this.rect[0].setRect(267, 218, 160, 50);
        this.rect[1].setRect(268, 318, 160, 50);
        
        this.clip[2].start();
    }//EndBuilder.
    
    
    public void stopMenuClip(){
       this.clip[2].stop();
    }//EndStopMenuClip.
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.mainBackground[this.backgroundSelector], 0, 0, null);
        //g2d.draw(this.rect[0]);
        //g2d.draw(this.rect[1]);
    }//EndPaintComponent.
    
   

    @Override
    public void mouseClicked(MouseEvent e) {
        /*
        Rectangle2D.Double mouseRect = new Rectangle2D.Double();
        mouseRect.setRect(e.getX(), e.getY(), 5, 5);
        if(this.rect[0].intersects(mouseRect)){
            this.clip[3].setMicrosecondPosition(0);
            this.clip[3].start();
            Controller.getInstance().closeMainGui();
            View.getInstance().openInsertPlayerWindow();
        }//EndIf.
        
        if(this.rect[1].intersects(mouseRect)){
            this.clip[3].setMicrosecondPosition(0);
            this.clip[3].start();
            View.getInstance().openRankingWindow();
        }//EndIf.*/
    }//EndMouseClicked.

    @Override
    public void mousePressed(MouseEvent e) {
        Rectangle2D.Double mouseRect = new Rectangle2D.Double();
        mouseRect.setRect(e.getX(), e.getY(), 5, 5);
        if(this.rect[0].intersects(mouseRect)){
            this.clip[3].setMicrosecondPosition(0);
            this.clip[3].start();
            Controller.getInstance().closeMainGui();
            View.getInstance().openInsertPlayerWindow();
        }//EndIf.
        
        if(this.rect[1].intersects(mouseRect)){
            this.clip[3].setMicrosecondPosition(0);
            this.clip[3].start();
            View.getInstance().openRankingWindow();
        }//EndIf.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Rectangle2D.Double mouseRect = new Rectangle2D.Double();
        mouseRect.setRect(e.getX(), e.getY(), 5, 5);
        //System.out.println ("X: " +e.getX()+ " Y: "+e.getY()); 
        
       if (!this.clip[0].isRunning())
            this.clip[0].setMicrosecondPosition(0);
       if (!this.clip[1].isRunning())
            this.clip[1].setMicrosecondPosition(0);
        
        if (this.rect[0].intersects(mouseRect)){
            
            this.backgroundSelector = 1;
            
            if (this.soundMove){
                this.clip[0].setMicrosecondPosition(0);
                this.clip[0].start();
                this.soundMove = false;
            }//EndIf.
        }//EndIf. 
        else if (this.rect[1].intersects(mouseRect)){
            this.backgroundSelector = 2;
            
            if (this.soundMove){
                this.clip[1].setMicrosecondPosition(0);
                this.clip[1].start();
                this.soundMove = false;
            }//EndIf.
            
        }//EndIf.
        else{ 
            this.backgroundSelector = 0;
            this.soundMove = true;
        }//EndElse.
        
        repaint();
    }//EndmouseMoved.
    
    private void importMedia(){
        this.mainBackground = new BufferedImage[3];
        this.mainBackground = new BufferedImage[3];
        this.clip = new Clip[4];
        for (int i=0;i<3;i++)
            this.mainBackground[i] = (BufferedImage)ImportWizard.getInstance().importMainGuiMedia()[i];
        for (int i=3;i<7;i++)
            this.clip[i-3] = (Clip)ImportWizard.getInstance().importMainGuiMedia()[i];
    //Loop menu sound.
        this.clip[2].loop(Clip.LOOP_CONTINUOUSLY);
        
    }//EndIMportMedia.
    
    
    public static MainGui getInstance(){
        if (instance == null)
            instance = new MainGui();
        return instance;
    }//EndGetInstance.
}//EndMainGui.
