/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author lorenzo
 */
public class RankingHandler {
    
    private static RankingHandler instance = null; 
    
    private BufferedReader buffRead = null;
    private PrintWriter printWriter = null;
    private LinkedList<String[]> lst = null;
    private String[] writeLst = null; 
    
    private RankingHandler(){
        this.lst = new LinkedList<String[]>();
        this.writeLst = new String[10];
        updateBuffer();
        //updateBuffer(); //Aggiorna da subito il ranking.
        
    }//EndRankingHandler.
    
    private void updateBuffer(){
        //LinkedList<String[]> tmp = getRanking();
        this.lst = getRanking(); 
        for (int i=0;i<this.lst.size();i++){
            String[] str = this.lst.get(i);
            this.writeLst[i] = str[0] +";"+ str[1];
        }//EndFor.
    }//EndUpdateBuffer.*/
    
    
    public LinkedList<String[]> getRanking(){
        String s = null;
        String path = "\\conf\\ranking.txt";
        LinkedList<String[]> list = new LinkedList<String[]>();
        try{
            buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(Config.getInstance().getPath() + path)));
            while ((s = buffRead.readLine()) != null)
                if (!s.isEmpty() && s.contains(";") )
                    list.add(s.trim().split(";"));
            
            
            if (buffRead != null)
                buffRead.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return list;
        }//EndFinally.
    }//EndGetRanking.
         
    public void addPlayer(String name, String score){
        String path = "\\conf\\ranking.txt";
        int num = getNumberOfNull();
        if (num != 0){
            boolean flag = true;//inserisci primo classificato che trovi.
            for (int i=0;i<this.writeLst.length;i++){
                if ( (this.writeLst[i] == null) && (flag == true) ){
                    this.writeLst[i] = name + ";" + score;
                    flag = false;
                }
            }//EndFor.
        }//endIf
        sortRanking();
        try{
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.getInstance().getPath() + path))), true);
      
            for (int i=0;i<this.writeLst.length;i++)//stampa ranking.txt
                printWriter.println(this.writeLst[i]);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
             printWriter.close();
        }//EndFinally.
            
        
    }//EndAddPlayer.
    
    private int getNumberOfNull(){
        int cnt = 0;
        for (int i=0;i<this.writeLst.length;i++){
            if (this.writeLst[i] == null)
                cnt++;
        }//EndIFor.
        return cnt;
    }//EndSortRanking.
    
    public void sortRanking(){
        
        int n = 10 - getNumberOfNull();
        String tmp = new String();
        
        for (int i=0;i<n-1;i++){
            for (int j=0;j<n-1-i;j++){
                Integer s1 = new Integer(this.writeLst[j].split(";")[1]);
                Integer s2 = new Integer(this.writeLst[j+1].split(";")[1]);
                if (s1<s2){
                    tmp = this.writeLst[j];
                    this.writeLst[j] = this.writeLst[j+1];
                    this.writeLst[j+1] = tmp;
                }//EndIf.
            }//EndJFor.
        }//EndIFor.
    }//EndSortRanking.
    
    public static RankingHandler getInstance(){
        if (instance == null)
            instance = new RankingHandler();
        return instance;
    }//EndGetInstance.
    
}//EndRankingHandler.
