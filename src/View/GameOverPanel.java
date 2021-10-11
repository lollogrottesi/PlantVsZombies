/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import utils.ImportWizard;

/**
 *
 * @author lorenzo
 */
public class GameOverPanel extends JPanel implements MouseInputListener{
    
    private static GameOverPanel instance = null;
    
    private BufferedImage background = null;
    private BufferedImage replayButton = null;
    private Rectangle2D.Double rect = null;
    private Clip[] clip ;
    
    private GameOverPanel(){
        super();
        importMedia();
        this.rect = new Rectangle2D.Double();
        this.rect.setRect(800, 530, 245, 40);
        addMouseListener(this);
        addMouseMotionListener(this);
    }//EndBuilder.
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.background, 0, 0, null);
        g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        g2d.setColor(Color.WHITE);
        g2d.drawString("" + Model.GameModel.getInstance().getScore(), 570, 566);
        g2d.drawString("Back to menu", 800, 566);
        g2d.draw(rect);
        //g2d.draw(this.rect[0]);
        //g2d.draw(this.rect[1]);
    }//EndPaintComponent.
    
    private void importMedia(){
        this.clip = new Clip[2];
        this.background = (BufferedImage)ImportWizard.getInstance().importGameOverPanelMedia()[0];
        this.clip[0] = (Clip)ImportWizard.getInstance().importGameOverPanelMedia()[1];
        this.clip[1] = (Clip)ImportWizard.getInstance().importGameOverPanelMedia()[2];
        this.clip[0].start();
        this.clip[1].loop(Clip.LOOP_CONTINUOUSLY);
    }//EndImportMedia.

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Rectangle2D.Double mouseRect = new Rectangle2D.Double();
        mouseRect.setRect(e.getX(), e.getY(), 5, 5);
        if (this.rect.intersects(mouseRect)){
            
            Controller.getInstance().closeGameOverWindow();
            this.clip[0].stop();
            this.clip[1].stop();
            Controller.getInstance().openMainGui();
        }//EndIf.
    }//EndMousePressed.

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static GameOverPanel getInstance(){
        if (instance == null)
            instance = new GameOverPanel();
        return instance;
    }//EndGetInstance.
}//EndGameOverPanel
