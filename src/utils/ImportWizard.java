/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author lorenzo
 */
public class ImportWizard {
    
    private static ImportWizard instance = null;
    
    private ImportWizard(){
        
    }//EndBuilder.
    
    public Clip[] importGameSound(){
        Clip[] sounds = new Clip[6];
        try{//ImportImage.
            String path[] = new String[6];
          
            path[0] = "\\conf\\sounds\\background.wav";
            path[1] = "\\conf\\sounds\\zombies_coming.wav";
            path[2] = "\\conf\\sounds\\atebrains.wav";
            path[3] = "\\conf\\sounds\\game_end.wav";
            path[4] = "\\conf\\sounds\\click.wav";
            path[5] = "\\conf\\sounds\\sun.wav";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                path[0] = "/conf/sounds/background.wav";
                path[1] = "/conf/sounds/zombies_coming.wav";
                path[2] = "/conf/sounds/atebrains.wav";
                path[3] = "/conf/sounds/game_end.wav";
                path[4] = "/conf/sounds/click.wav";
                path[5] = "/conf/sounds/sun.wav";
            }//endif.
        
            AudioInputStream stream1 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[0]))));
            AudioInputStream stream2 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[1]))));
            AudioInputStream stream3 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[2]))));
            AudioInputStream stream4 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[3]))));
            AudioInputStream stream5 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[4]))));
            AudioInputStream stream6 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[5]))));
            sounds[0] = AudioSystem.getClip();
            sounds[1] = AudioSystem.getClip();
            sounds[2] = AudioSystem.getClip();
            sounds[3] = AudioSystem.getClip();
            sounds[4] = AudioSystem.getClip();
            sounds[5] = AudioSystem.getClip();
            sounds[0].open(stream1);
            sounds[1].open(stream2);
            sounds[2].open(stream3);
            sounds[3].open(stream4);
            sounds[4].open(stream5);
            sounds[5].open(stream6);
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig game media"); 
       }//EndImporting.
        return sounds;
    }//EndImportGameSound.
    
    public BufferedImage importBeetBulletMedia(){
        BufferedImage img = null;
        try{//ImportImage.
            String Path = "\\conf\\images\\beetbullet.png";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                Path = "/conf/images/beetbullet.png";
            }//endif.
        img = ImageIO.read(new File(Config.getInstance().getPath() + Path));
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig BeetBullet image media"); 
       }//EndImporting.
        return img;
    }//EndImportBeetBulletMedia.
    
    public BufferedImage importBulletMedia(){
        BufferedImage img = null;
        try{//ImportImage.
            
            String Path = "\\conf\\images\\bullet.png";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                Path = "/conf/images/bullet.png";
            }//endif.
        img = ImageIO.read(new File(Config.getInstance().getPath() + Path));
        
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig bullet image media"); 
       }//EndImporting.
       return img;
    }//EndImportMedia.
    
    public Object[] importGameOverPanelMedia(){
        Object[] obj = new Object[3];
        BufferedImage img = null;
        Clip[] clip = new Clip[2];
        try{
            String path[] = new String[9];
            path[0] = "\\conf\\images\\gameOver.jpg";
            path[1] = "\\conf\\sounds\\atebrains.wav";
            path[2] = "\\conf\\sounds\\game_end.wav";
            
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                path[0] = "/conf/images/gameOver.jpg";
                path[1] = "/conf/sounds/atebrains.wav";
                path[2] = "/conf/sounds/game_end.wav";
            }//endif.
            
            img = ImageIO.read(new File(Config.getInstance().getPath() + path[0]));
            AudioInputStream stream1 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[1]))));
            AudioInputStream stream2 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[2]))));
            clip[0] = AudioSystem.getClip();
            clip[1] = AudioSystem.getClip();
            clip[0].open(stream1);
            clip[1].open(stream2);
            obj[0] = img;
            obj[1] = clip[0];
            obj[2] = clip[1];
            
        }catch(Exception e){
            System.out.println("Error loading game over media");
        }//EndCatch.
        return obj;
    }//EndImportGameOverPanelMedia.
    
    public BufferedImage[] importGamePanelMedia(){
        
        BufferedImage[] img = new BufferedImage[10];
        try{
            String path[] = new String[10];
            path[0] = "\\conf\\images\\active_peashooter.png";
            path[1] = "\\conf\\images\\active_sunflower.png";
            path[2] = "\\conf\\images\\active_walnut.png";
            path[3] = "\\conf\\images\\active_beetroot.png";
            path[4] = "\\conf\\images\\inactive_peashooter.png";
            path[5] = "\\conf\\images\\inactive_sunflower.png";
            path[6] = "\\conf\\images\\inactive_walnut.png";
            path[7] = "\\conf\\images\\inactive_beetroot.png"; 
            path[8] = "\\conf\\images\\Counter.png";
            path[9] = "\\conf\\images\\backyard.jpg";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                path[0] = "/conf/images/active_peashooter.png";
                path[1] = "/conf/images/active_sunflower.png";
                path[2] = "/conf/images/active_walnut.png";
                path[3] = "/conf/images/active_beetroot.png";
                path[4] = "/conf/images/inactive_peashooter.png";
                path[5] = "/conf/images/inactive_sunflower.png";
                path[6] = "/conf/images/inactive_walnut.png";
                path[7] = "/conf/images/inactive_beetroot.png";
                path[8] = "/conf/images/Counter.png";
                path[9] = "/conf/images/backyard.jpg";
                //path[9] = "/conf/images/gameOver.jpg";
            }//endif.
            
            for (int i=0;i<10;i++)
                img[i] = ImageIO.read(new File(Config.getInstance().getPath() + path[i]));
            
        }catch(Exception e){
            System.out.println("Error loading background");
        }//EndCatch.
        return img;
    }//EndImportGamePanelMedia.
    
    public Object[] importNamePanelMedia(){
        Object[] obj = new Object[6];
        BufferedImage[] img = new BufferedImage[3];
        Clip[] clip = new Clip[3];
        try{
            
            String path[] = new String[5];
            path[0] = "\\conf\\images\\InsertNamePvZ.png";
            path[1] = "\\conf\\images\\InsertNamePvZ1.png";
            path[2] = "\\conf\\images\\InsertNamePvZ2.png";
            path[3] = "\\conf\\sounds\\touch.wav";
            path[4] = "\\conf\\sounds\\click.wav";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                
                path[0] = "/conf/images/InsertNamePvZ.png";
                path[1] = "/conf/images/InsertNamePvZ1.png";
                path[2] = "/conf/images/InsertNamePvZ2.png";
                path[3] = "/conf/sounds/touch.wav";
                path[4] = "/conf/sounds/click.wav";
            }//endif.
            for (int i=0;i<3;i++)
                img[i] =  ImageIO.read(new File(Config.getInstance().getPath() + path[i]));
            AudioInputStream stream1 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[3]))));
            AudioInputStream stream2 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[3]))));
            AudioInputStream stream3 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[4]))));
            clip[0] = AudioSystem.getClip();
            clip[1] = AudioSystem.getClip();
            clip[2] = AudioSystem.getClip();
            
            clip[0].open(stream1);
            clip[1].open(stream2);
            clip[2].open(stream3);
            
            obj[0] = img[0];
            obj[1] = img[1];
            obj[2] = img[2];
            obj[3] = clip[0];
            obj[4] = clip[1];
            obj[5] = clip[2];
        }catch(Exception e){
            System.out.println("Error loading mainGui media.");
        }//EndCatch.
        return obj;
    }//EndImportNamePanelMedia.
    
    public BufferedImage importRankingMedia(){
        BufferedImage img = null;
        try{
            String path = new String();
            path = "\\conf\\images\\ranking.png";
            
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                path = "/conf/images/ranking.png";
            }//endif.
            
                img =  ImageIO.read(new File(Config.getInstance().getPath() + path));
       
        }catch(Exception e){
            System.out.println("Error loading RankingGui media.");
        }//EndCatch.
        return img;
    }//EndImportRankingMedia.
    
    public Object[] importMainGuiMedia(){
        Object[] obj = new Object[7];
        BufferedImage[] img = new BufferedImage[3];
        Clip[] clip = new Clip[4];
        try{
            String path[] = new String[6];
            path[0] = "\\conf\\images\\PvSGUI_1.jpg";
            path[1] = "\\conf\\images\\PvSGUI_2.jpg";
            path[2] = "\\conf\\images\\PvSGUI_3.jpg";
            path[3] = "\\conf\\sounds\\touch.wav";
            path[4] = "\\conf\\sounds\\menu.wav";
            path[5] = "\\conf\\sounds\\click.wav";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                path[0] = "/conf/images/PvSGUI_1.jpg";
                path[1] = "/conf/images/PvSGUI_2.jpg";
                path[2] = "/conf/images/PvSGUI_3.jpg";
                path[3] = "/conf/sounds/touch.wav";
                path[4] = "/conf/sounds/menu.wav";
                path[5] = "/conf/sounds/click.wav";
            }//endif.
            System.out.println("path"+ Config.getInstance().getPath() + path[0]);
            //this.activePlants[0] = ImageIO.read(new File(Config.getInstance().getPath() + path[0])); 
            for (int i=0;i<3;i++)
                img[i] =  ImageIO.read(new File(Config.getInstance().getPath() + path[i]));
           
            AudioInputStream stream1 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[3]))));
            AudioInputStream stream2 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[3]))));
            AudioInputStream stream3 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[4]))));
            AudioInputStream stream4 = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[5]))));
            
            clip[0] = AudioSystem.getClip();
            clip[1] = AudioSystem.getClip();
            clip[2] = AudioSystem.getClip();
            clip[3] = AudioSystem.getClip();
            
            clip[0].open(stream1);
            clip[1].open(stream2);
            clip[2].open(stream3);
            clip[3].open(stream4);
           
            obj[0] = img[0];
            obj[1] = img[1];
            obj[2] = img[2];
            obj[3] = clip[0];
            obj[4] = clip[1];
            obj[5] = clip[2];
            obj[6] = clip[3];
        }catch(Exception e){
            System.out.println("Error loading mainGui media.");
            
        }//EndCatch.
        return obj;
    }//EndimportMainGuiMedia.
    
    public BufferedImage[] importNormalZombieMedia(){
        BufferedImage[] img = new BufferedImage[8];
        try{//ImportImage.
            String path[] = new String[8];
            //String deathPath[] = new String[6];
            path[0] = "\\conf\\ZombieNormalFrames\\zombie_normal_1.png";
            path[1] = "\\conf\\ZombieNormalFrames\\zombie_normal_2.png";
            path[2] = "\\conf\\ZombieNormalFrames\\zombie_normal_dying_1.png";
            path[3] = "\\conf\\ZombieNormalFrames\\zombie_normal_dying_2.png";
            path[4] = "\\conf\\ZombieNormalFrames\\zombie_normal_dying_3.png";
            path[5] = "\\conf\\ZombieNormalFrames\\zombie_normal_dying_4.png";
            path[6] = "\\conf\\ZombieNormalFrames\\zombie_normal_dying_5.png";
            path[7] = "\\conf\\ZombieNormalFrames\\zombie_normal_dying_6.png";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                path[0] = "/conf/ZombieNormalFrames/zombie_normal_1.png";
                path[1] = "/conf/ZombieNormalFrames/zombie_normal_2.png";
                path[2] = "/conf/ZombieNormalFrames/zombie_normal_dying_1.png";
                path[3] = "/conf/ZombieNormalFrames/zombie_normal_dying_2.png";
                path[4] = "/conf/ZombieNormalFrames/zombie_normal_dying_3.png";
                path[5] = "/conf/ZombieNormalFrames/zombie_normal_dying_4.png";
                path[6] = "/conf/ZombieNormalFrames/zombie_normal_dying_5.png";
                path[7] = "/conf/ZombieNormalFrames/zombie_normal_dying_6.png";
            }//endif.
        
        for (int i=0;i<8;i++)
            img[i] = ImageIO.read(new File(Config.getInstance().getPath() + path[i]));
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig Zombie image media"); 
       }//EndImporting.
        return img;
    }//EndImportNormalZombieMedia.
    
    public Object[] importLawnMoverMedia(){
        Object[] obj = new Object[3];
        BufferedImage[] img = new BufferedImage[2];
        Clip clip = null;
        try{//ImportImage.
            String path[] = new String[3];
                
            path[0] = "\\conf\\LawnMoverFrames\\lawn_mower_1.png";
            path[1] = "\\conf\\LawnMoverFrames\\lawn_mower_2.png";
            path[2] = "\\conf\\sounds\\lamborghini.wav";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                path[0] = "/conf/LawnMoverFrames/lawn_mower_1.png";
                path[1] = "/conf/LawnMoverFrames/lawn_mower_2.png";
                path[2] = "/conf/sounds/lamborghini.wav";
            }//endif.
            img[0] = ImageIO.read(new File(Config.getInstance().getPath() + path[0]));
            img[1] = ImageIO.read(new File(Config.getInstance().getPath() + path[1]));
            
            //importSound.
            File sound = new File(Config.getInstance().getPath() + path[2]);
            AudioInputStream moveSound = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(sound)));
            clip = AudioSystem.getClip();
            clip.open(moveSound);
            obj[0] = img[0];
            obj[1] = img[1];
            obj[2] = clip;
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig lawn mover media"); 
        }//EndImporting.
        return obj;
    }//EndImportLawnMoverMedia.
    
    public Object[] importShovelMedia(){
        Object[] obj = new Object[2];
        BufferedImage img;
        Clip clp ;
        try{//ImportImage.
            String[] path = new String[2];
            path[0] = "\\conf\\images\\shovel.png";
            path[1] = "\\conf\\sounds\\shovel.wav";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                 path[0] = "/conf/images/shovel.png";
                 path[1] = "/conf/sounds/shovel.wav";
            }//endif.
            img = ImageIO.read(new File(Config.getInstance().getPath() + path[0]));
            AudioInputStream stream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(Config.getInstance().getPath() + path[1]))));
            clp = AudioSystem.getClip();
            clp.open(stream);
            obj[0] = img;
            obj[1] = clp;
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig shovel media"); 
       }//EndImporting.
        return obj;
    }//EndImportShovelMedia.
    
    public BufferedImage[] importBeetRootMedia(){
        BufferedImage[] img = new BufferedImage[5];
        try{//ImportImage.
            String path[] = new String[5];
            path[0] = "\\conf\\BeetRootFrames\\beetroot_1.png";
            path[1] = "\\conf\\BeetRootFrames\\beetroot_2.png";
            path[2] = "\\conf\\BeetRootFrames\\beetroot_dying_1.png";
            path[3] = "\\conf\\BeetRootFrames\\beetroot_dying_2.png";
            path[4] = "\\conf\\BeetRootFrames\\beetroot_dying_3.png";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                path[0] = "/conf/BeetRootFrames/beetroot_1.png";
                path[1] = "/conf/BeetRootFrames/beetroot_2.png";
                path[2] = "/conf/BeetRootFrames/beetroot_dying_1.png";
                path[3] = "/conf/BeetRootFrames/beetroot_dying_2.png";
                path[4] = "/conf/BeetRootFrames/beetroot_dying_3.png";
            }//endif.
            for(int i=0;i<5;i++)
                img[i] = ImageIO.read(new File(Config.getInstance().getPath() + path[i]));
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig PeaShoter image media"); 
       }//EndImporting.
        return img;
    }//EndImportBeetRootMedia.
    
    public BufferedImage[] importPeaShooterMedia(){
        BufferedImage[] img = new BufferedImage[5];
         try{//ImportImage.
            String path[] = new String[5];
            path[0] = "\\conf\\PeaShooterFrames\\PeaShooterFrame_1.png";
            path[1] = "\\conf\\PeaShooterFrames\\PeaShooterFrame_2.png";
            path[2] = "\\conf\\PeaShooterFrames\\PeaShooterDyingFrame_1.png";
            path[3] = "\\conf\\PeaShooterFrames\\PeaShooterDyingFrame_2.png";
            path[4] = "\\conf\\PeaShooterFrames\\PeaShooterDyingFrame_3.png";
 
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                path[0] = "/conf/PeaShooterFrames/PeaShooterFrame_1.png";
                path[1] = "/conf/PeaShooterFrames/PeaShooterFrame_2.png";
                path[2] = "/conf/PeaShooterFrames/PeaShooterDyingFrame_1.png";
                path[3] = "/conf/PeaShooterFrames/PeaShooterDyingFrame_2.png";
                path[4] = "/conf/PeaShooterFrames/PeaShooterDyingFrame_3.png";
            }//endif.
            
 
            for(int i=0;i<5;i++)
                img[i] = ImageIO.read(new File(Config.getInstance().getPath() + path[i]));
        
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig PeaShoter image media"); 
       }//EndImporting.
       return img;
    }//EndImportPeaShooterMedia.
    
    public BufferedImage[] importWalnutMedia(){
        BufferedImage[] img = new BufferedImage[9];
        
        try{//ImportImage.
            
            String path[] = new String[9];
            path[0] = "\\conf\\WalnutFrames\\walnut_full_life_1.png";
            path[1] = "\\conf\\WalnutFrames\\walnut_full_life_2.png";
            path[2] = "\\conf\\WalnutFrames\\walnut_full_life_3.png";
            path[3] = "\\conf\\WalnutFrames\\walnut_half_life_1.png";
            path[4] = "\\conf\\WalnutFrames\\walnut_half_life_2.png";
            path[5] = "\\conf\\WalnutFrames\\walnut_half_life_3.png";
            path[6] = "\\conf\\WalnutFrames\\walnut_dead_1.png";
            path[7] = "\\conf\\WalnutFrames\\walnut_dead_2.png";
            path[8] = "\\conf\\WalnutFrames\\walnut_dead_3.png";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                path[0] = "/conf/WalnutFrames/walnut_full_life_1.png";
                path[1] = "/conf/WalnutFrames/walnut_full_life_2.png";
                path[2] = "/conf/WalnutFrames/walnut_full_life_3.png";
                path[3] = "/conf/WalnutFrames/walnut_half_life_1.png";
                path[4] = "/conf/WalnutFrames/walnut_half_life_2.png";
                path[5] = "/conf/WalnutFrames/walnut_half_life_3.png";
                path[6] = "/conf/WalnutFrames/walnut_dead_1.png";
                path[7] = "/conf/WalnutFrames/walnut_dead_2.png";
                path[8] = "/conf/WalnutFrames/walnut_dead_3.png";
            }//endif.
            
            for(int i=0;i<9;i++)
                img[i] = ImageIO.read(new File(Config.getInstance().getPath() + path[i]));
            
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig Walnut image media"); 
       }//EndImporting.
       return img;
    }//EndImportWalnut.
    
    public BufferedImage[] importSunMedia(){
        
        BufferedImage[] img = new BufferedImage[3];
        try{//ImportImage.
            String path[] = new String[3];
            //String deathPath[] = new String[6];
            path[0] = "\\conf\\SunFrames\\sun_frame_1.png";
            path[1] = "\\conf\\SunFrames\\sun_frame_2.png";
            path[2] = "\\conf\\SunFrames\\sun_frame_3.png";
            
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                
                path[0] = "/conf/SunFrames/sun_frame_1.png";
                path[1] = "/conf/SunFrames/sun_frame_2.png";
                path[2] = "/conf/SunFrames/sun_frame_3.png";
               
            }//endif.
        
        for (int i=0;i<3;i++)
            img[i] = ImageIO.read(new File(Config.getInstance().getPath() + path[i]));
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig Sun image media"); 
        }//EndCatch.
        return img;
    }////EndImportSunMedia.
    
    public BufferedImage[] importSunFlowerMedia(){
        
         BufferedImage[] img = new BufferedImage[5];
         try{//ImportImage.
            
            String[] path = new String[5];
            path[0] = "\\conf\\SunflowerFrames\\sun_flower_1.png";
            path[1] = "\\conf\\SunflowerFrames\\sun_flower_2.png";
            path[2] = "\\conf\\SunflowerFrames\\sun_flower_dying_1.png";
            path[3] = "\\conf\\SunflowerFrames\\sun_flower_dying_2.png";
            path[4] = "\\conf\\SunflowerFrames\\sun_flower_dying_3.png";
            if (System.getProperty("os.name").startsWith("Linux")) {//CheckSystem.
                //relPath = "/conf/images/backyard.jpg";
                path[0] = "/conf/SunflowerFrames/sun_flower_1.png";
                path[1] = "/conf/SunflowerFrames/sun_flower_2.png";
                path[2] = "/conf/SunflowerFrames/sun_flower_dying_1.png";
                path[3] = "/conf/SunflowerFrames/sun_flower_dying_2.png";
                path[4] = "/conf/SunflowerFrames/sun_flower_dying_3.png";
            }//endif.
            for(int i=0;i<5;i++)
                img[i] = ImageIO.read(new File(Config.getInstance().getPath() + path[i]));
            
        }//Endtry.
        catch(Exception e){
            System.out.println("Error importig PeaShoter image media"); 
        }
        return img;
    }//EndImportSunFlowerMedia.
    
    public static ImportWizard getInstance(){
        if (instance == null)
            instance = new ImportWizard();
        return instance;
    }//EndGetInstance.
}//EndImportWizard.
