/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author lorenzo
 */
public class GameModel implements IModel{
    
    private static GameModel instance = null;
    
    private int NUM_ROWS = 5; // number of rows of the matrix
    private int NUM_COLUMNS = 9; // number of columns of the matrix
    private int NUM_CELLS;
    private int X_MARGIN;
    private int Y_MARGIN;
    private int uX;
    private int uY;
    private String PlayerName;
    private int score;
    private int money;
    private ArrayList<AbstractZombieModel>[] ZombieList;
    private ArrayList<IBulletModel>[] bulletList;
    private AbstractPlantModel[][] gamePlant;
    private ILawnMoverModel[] lawnMoverMap;
    private boolean GameOver = false;
    
    private GameModel(){
        
        //setPlayerName(newPlayer);
        this.money = 400;
        this.score = 0;
        this.NUM_CELLS = NUM_ROWS * NUM_COLUMNS; 
        this.X_MARGIN = 285;
        this.Y_MARGIN = 35;
        this.uX = 87;
        this.uY = 107;
        this.ZombieList = new ArrayList [NUM_ROWS];
        this.bulletList = new ArrayList [NUM_ROWS];
        this.gamePlant = new AbstractPlantModel[NUM_ROWS][NUM_COLUMNS];
        this.lawnMoverMap = new LawnMoverModel[NUM_ROWS];
        for(int i=0;i<NUM_ROWS;i++){
            this.ZombieList[i] = new ArrayList<AbstractZombieModel>();
            this.bulletList[i] = new ArrayList<IBulletModel>();
        }//EndFor.
       
    }//Endbuilder.
    
    
    //Liste e array per la gestione delle piante--------------------------------
    public  ArrayList<AbstractZombieModel> getRowZombieList(int index){
        if ((index >0 ) && (index <NUM_ROWS)) 
                return this.ZombieList[index];
        else 
                return null;
    }//EndGetRowZombieList.
    
    public void addZombieModel(int index, /*int x, int y,*/ AbstractZombieModel newZombie){
        if ((index >= 0) && (index < NUM_ROWS))
            //this.ZombieList[index].add(new NormalZombieModel(x, y));
            this.ZombieList[index].add(newZombie);
    }//EndAddNewZombie.
     
    public void removeZombieModel(int i, int j){
        this.ZombieList[i].remove(j);
    }//EndRemoveZombieModel.
    
    public AbstractPlantModel getPlant(int i, int j){
       return this.gamePlant[i][j];
    }//EndGetPlant.
    
    public void addLawnMoverModel(ILawnMoverModel newLawnMover, int index){
        this.lawnMoverMap[index] = newLawnMover;
    }//EndAddLawnMoverModel.
    
    public void removeLawnMoverModel(int index){
        this.lawnMoverMap[index] = null;
    }//EndRemoveLawnMoverModel
    
    public void addPlantModel(AbstractPlantModel newPlant, int i, int j){
        this.gamePlant[i][j] = newPlant;
    }//EndSetPlant.
    
    public void removePlantModel(int i, int j){
        this.gamePlant[i][j] = null;
    }//EndRemovePlantModel.
    
    public void addBulletModel(int index, IBulletModel newBullet){
        this.bulletList[index].add(newBullet);//controllare se 1;
    }//EndAddBullet.
    
    public void removeBulletModel(int i, int j){
        this.bulletList[i].remove(j);
    }//EndRemoveBullerModel.
    //--------------------------------------------------------------------------
    public int getNumRowsOfBoard(){
        return this.NUM_ROWS;
    }//EndGetNumRows.
    
    public int getNumColumnsOfBoard(){
        return this.NUM_COLUMNS;

    }//EndGetCols.
    
    public int getXMargin(){
        return this.X_MARGIN;
    }//EndGetXMrgin.
    
    public int getYMargin(){
        return this.Y_MARGIN;
    }//EndGetYMrgin.
    
    public int getUx(){
        return this.uX;
    }//EndGetUX.
    
    public int getUy(){
        return this.uY;
    }//EndGetUY.
    
    public void setMoney(int newMoney){
        this.money = newMoney;
    }//EndSetMoney.
    
    public int getMoney(){
        return this.money;
    }//EndSetMoney.
    
    public void setPlayerName(String newPlayerName){
        this.PlayerName = newPlayerName;
    }//EndSetPlayerName
    
    public String getPlayerName(){
        return this.PlayerName;
    }//EndPlayerName.
    
    public int getScore(){
        return this.score;
    }//GetScore.
    
    public void incrementScore(int increment){
        this.score = increment;
    }//EndIncrementScore.
    
    public int getRowIndex(int y) {
       int i = -1;
       i = (int)((double)(y - Y_MARGIN) / this.uY);
       return i;
    }//EndGetRowIndex
    
    public int getColumnIndex(int x) {
        int j = -1;
        j = (int)((double)(x - X_MARGIN) / this.uX);
        return j;
    }//EndColumnIndex.
  
    public boolean isGameOver(){
        return this.GameOver;
    }//EndIsGameOver.
    
    public void setGameOver(boolean GameOverState){
        this.GameOver = GameOverState;
    }//EndSetGameOver
    
    public static IModel getInstance(){
        if (instance == null)
            instance = new GameModel();
        return instance;
    }//EndGetInstance.

    @Override
    public void setShovelDrag(boolean state) {
        ShovelModel.getInstance().setShovelDrag(state);
    }

    @Override
    public boolean isShovelDrag() {
        return ShovelModel.getInstance().isShovelDrag();
    }

    @Override
    public void ShovelSetX(int x) {
        ShovelModel.getInstance().ShovelSetX(x);
    }

    @Override
    public void ShovelSetY(int y) {
        ShovelModel.getInstance().ShovelSetY(y);
    }

    @Override
    public int ShovelGetX() {
        return ShovelModel.getInstance().ShovelGetX();
    }

    @Override
    public int ShovelGetY() {
        return ShovelModel.getInstance().ShovelGetY();
    }
    
}//EndGameModel.
