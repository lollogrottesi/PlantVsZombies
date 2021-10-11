/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import utils.Config;
import utils.ImportWizard;

/**
 *
 * @author lorenzo
 */
public class BeetBulletView extends BulletView{
    
    private BufferedImage bulletImage = null;
    
    public BeetBulletView(int x, int y){
        super(x, y);
        
        importImages();
    }//EndBuilder.
       
    public void drawBullet(Graphics2D g2d){
        g2d.drawImage(bulletImage, super.getX(), super.getY(),  null);
        //g2d.draw(super.getRect());
    }//EndDrawBullet.
    
    private void importImages(){
        this.bulletImage = ImportWizard.getInstance().importBeetBulletMedia();
    }//EndImportImage.
    
}//EndBulletView.
