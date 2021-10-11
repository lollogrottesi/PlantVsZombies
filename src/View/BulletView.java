/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import utils.Config;
import utils.ImportWizard;

/**
 *
 * @author lorenzo
 */
public class BulletView implements IBulletView{
    
    private Rectangle2D.Double shape;
    private int xPos;
    private int yPos;
    private BufferedImage bulletImage;
    
    public BulletView(int x, int y){
        
        this.shape = new Rectangle2D.Double();
        this.shape.setRect(x, y, 20, 20);
        this.xPos = x;
        this.yPos = y;
        importImages();
       
    }//EndBulletView.

    
    public void setX(int x){
        this.xPos = x;
        this.shape.setRect(x, getY(), 20 , 20);//for better gaming.
    }//EndSetX.
    
    public void setY(int y){
        this.yPos = y;
        this.shape.setRect(getX(), y , 20 , 20);//for better gaming.
    }//EndSetY.
    
    public int getX(){
        return this.xPos;
    }//EndGetX
    
    public int getY(){
        return this.yPos;
    }//EndGetY.
    
    public Rectangle2D.Double getRect(){
        return this.shape;
    }//EndGetRect.
    
    public void drawBullet(Graphics2D g2d){
        g2d.drawImage(bulletImage, xPos, yPos,  null);
        //g2d.draw(shape);
    }//EndDrawBullet.
    
    private void importImages(){
        this.bulletImage = ImportWizard.getInstance().importBulletMedia();
        /*try{//ImportImage.
            
            String Path = "\\conf\\images\\bullet.png";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                Path = "/conf/images/bullet.png";
            }//endif.
        this.bulletImage = ImageIO.read(new File(Config.getInstance().getPath() + Path));
        
        }//Endtry.
        catch(Exception e){
            
            System.out.println("Error importig bullet image media"); 
            
       }//EndImporting.*/
    }//EndImportImage.

}//EndBullet.
