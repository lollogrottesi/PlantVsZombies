/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author lorenzo
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class Config {
    
    //---------------------------------------------------------------
    // STATIC CONSTANTS
    //---------------------------------------------------------------
    private final static boolean IS_DIST_VERSION = true; // this flag must be set to true when compiling for the dist version
    //STATIC FIELDS
    private static Config instance = null;

    //INSTANCE FIELDS
    private Properties properties;
    
    private Config(){
       /* try {
            String configFile = getConfigFile();
	    BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(configFile), "ISO-8859-1"));
	    this.properties = new Properties();
            this.properties.load(buffRead);
        }
        catch(URISyntaxException urise) {
            urise.printStackTrace();
        }
	catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
	catch(IOException ioe) {
            ioe.printStackTrace();
	}*/
    }//EndBuilder.
    
   private String getConfigFile() throws URISyntaxException {
        
        String configFile = null;
	//String relPath = "\\conf\\images\\backyard.jpg";
        String relPath = "\\conf\\config.txt";
	if (System.getProperty("os.name").startsWith("Linux")) {
	//relPath = "/conf/images/backyard.jpg";
            relPath = "/conf/config.txt";
	}
        
        if (IS_DIST_VERSION)
            configFile = getHomeFolderForDistVersion() + relPath;
        else
            configFile = getHomeFolderForDevVersion() + relPath;
        
	return configFile;
    }//EndFetConfigFile.*/
        
    private String getHomeFolderForDistVersion() throws URISyntaxException {
        
	String homeDir = null;
        String s = null;
	String jarPath = Config.class.getResource("Config.class").toURI().toString();
	int indexOfExclamationMark = jarPath.indexOf("!");
	String prefix = "jar:file:/"; // this is the prefix for Windows OS platform
	if (System.getProperty("os.name").startsWith("Linux")) {
            prefix = "jar:file:";
	}
	homeDir = jarPath.substring(prefix.length(), indexOfExclamationMark);
	int lastIndexOfSlash = homeDir.lastIndexOf("/");
	homeDir = homeDir.substring(0, lastIndexOfSlash);
        s = homeDir.replace("/", "\\");
        return s;
	//return homeDir;
        
    }//EndGetHomeFolderForDistVersion.
                
    private String getHomeFolderForDevVersion() throws URISyntaxException {
	File configFile = null;
	File byteCodeFileOfThisClass = new File(Config.class.getResource("Config.class").toURI());
	//System.out.println("byteCodeFileOfThisClass: " + byteCodeFileOfThisClass);
	configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile();
	//System.out.println("configFile: " + configFile.toString());
	return configFile.toString();
        
    }//EndGetHomeFolderForDevVersion.

    public static Config getInstance(){
        if (instance == null)
            instance = new Config();
        return instance;
    }//EndGetInstance.
    
    public String getBackGround(){
        String configFile = null;
        String relPath = "\\conf\\images\\backyard.jpg";
	if (System.getProperty("os.name").startsWith("Linux")) {
            relPath = "/conf/images/backyard.jpg";
	}
        try{
            if (IS_DIST_VERSION)
                configFile = getHomeFolderForDistVersion() + relPath;
            else
                configFile = getHomeFolderForDevVersion() + relPath;
        }catch(URISyntaxException e){
            System.out.println("URI exeption loading background");
        }//EndCatch
	return configFile;
    }//EndGetBackground.
    
     public String getRanking(){
        String configFile = null;
        String relPath = "\\conf\\ranking.txt";
	if (System.getProperty("os.name").startsWith("Linux")) {
            relPath = "/conf/ranking.txt";
	}
        try{
            if (IS_DIST_VERSION)
                configFile = getHomeFolderForDistVersion() + relPath;
            else
                configFile = getHomeFolderForDevVersion() + relPath;
        }catch(URISyntaxException e){
            System.out.println("URI exeption loading background");
        }//EndCatch
	return configFile;
    }//EndGetBackground.
    
    public String getPath(){
        String configFile = null;
        String s = null; 
        try{
            if (IS_DIST_VERSION)
                configFile = getHomeFolderForDistVersion();
            else
                configFile = getHomeFolderForDevVersion();
        }catch(URISyntaxException e){
            System.out.println("URI exeption loading path");
        }//EndCatch
        s = configFile.replaceAll("%20", " ");
        
	//return configFile
        return s;
    }//EndGetPath.
    
    
}//EndConfig.
