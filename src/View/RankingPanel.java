/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import utils.Config;
import utils.ImportWizard;
import utils.RankingHandler;

/**
 *
 * @author lorenzo
 */
public class RankingPanel extends javax.swing.JPanel {

    /**
     * Creates new form RankingPanel
     */
    private LinkedList<String[]> ranking = null;
    
    private BufferedImage backGround = null;
    public RankingPanel() {
        initComponents();
        importMedia();
        this.ranking = RankingHandler.getInstance().getRanking();
        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backGround, 0, 0, null);
        g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Name", 275, 437);
        g2d.drawString("Score", 380, 437);
        String[][] str = new String[10][2];

        for (int i=0;i<this.ranking.size();i++){
            str[i] = this.ranking.get(i);
            if(str[i][0] != null){
                g2d.drawString(str[i][0], 260, 477 + i*20);
                g2d.drawString(str[i][1], 400, 477 + i*20);
            }//EndIf.
        }//EndFor.

    }//EndPaintComponent.
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void importMedia(){
        backGround = ImportWizard.getInstance().importRankingMedia();
    }//EndImportMedia.

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}//EndClass.