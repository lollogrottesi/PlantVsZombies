/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;

/**
 *
 * @author lorenzo
 */


public class Grid {
    
    private Rectangle2D.Double[][] rect;
    private Line2D.Double line;
    private final static int NUM_ROWS = 5; // number of rows of the matrix
    private final static int NUM_COLUMNS = 9; // number of columns of the matrix
    private  int X_MARGIN ;
    private  int Y_MARGIN ;
    private  int uX ;
    private  int uY ;
    private Color colo = new Color(0, 255, 0, 0);
    
    public Grid(int xMargin, int yMargin, int ux, int uy){
        
        this.X_MARGIN = xMargin;
        this.Y_MARGIN = yMargin;
        this.uX = ux;
        this.uY = uy;
        this.line = new Line2D.Double();
        this.rect = new Rectangle2D.Double[NUM_ROWS][NUM_COLUMNS];
        
        //Rectangles init.
        for (int i=0;i<NUM_ROWS;i++){
            for (int j=0;j<NUM_COLUMNS;j++){
                this.rect[i][j] = new Rectangle2D.Double();
            }//EndjFor.
        }//EndiFor.
        
    }//EndBuilder.
    
    public void createGrid(Graphics2D g2d){
        
        for (int i = 0; i <= NUM_ROWS; i++) {
            this.line.setLine(X_MARGIN, Y_MARGIN + i * this.uY,
                              X_MARGIN + NUM_COLUMNS * this.uX, Y_MARGIN + i * this.uY);
            g2d.draw(this.line);
        }//EndFor.
        
        for (int j = 0; j <= NUM_COLUMNS; j++) {
            this.line.setLine(X_MARGIN + j * this.uX, Y_MARGIN,
                              X_MARGIN + j * this.uX, Y_MARGIN + NUM_ROWS * this.uY);
            g2d.draw(this.line);
        }//EndFor.
        
    }//EndCreateGrid.

   public void createCells(Graphics2D g2d) {
        
           for (int i=0;i<NUM_ROWS;i++){
               for (int j=0;j<NUM_COLUMNS;j++){
                   this.rect[i][j].setRect(X_MARGIN + j*this.uX, Y_MARGIN + i* this.uY, this.uX, this.uY);
                   g2d.setColor(this.colo);
                   g2d.fill(this.rect[i][j]);
                   g2d.setColor(Color.black);
                   g2d.draw(this.rect[i][j]);
               }//EndjFor.
           }//EndiFor.
           
    }//EndCreateCells.
    
   public Rectangle2D.Double[][] getCellMatrix(){
       return this.rect;
   }//EndGetCEll.
   
   public Rectangle2D.Double getCell (int i, int j){
       
        return this.rect[i][j];

   }//EndGetCell.
}//EndGrid.
