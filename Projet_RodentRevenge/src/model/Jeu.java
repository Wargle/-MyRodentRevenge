/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe qui correspond à la façade du systeme
 * @author Alexis Arnould
 */
public class Jeu {
    private Level current;
    private ILoadLevel loader;
    private ISaveLevel saver;
    
    public Jeu() {
        loader = new LoadLevelFromFile();
        saver = new SaveLevelToFile();
    }
    
    /**
     * Permet de mettre à jour le fichier d'informations sur les niveaux
     * @param name : le nom du niveau
     * @param info : l'information
     */
    private void reWriteSpecific(String name, String info) {
        try {
            List<String> lines = new ArrayList<>();
                    
            File file = new File(System.getProperty("user.dir").replace("\\dist", "") + "/file/specificsLevels.txt");
            Scanner sc = new Scanner(file);
            
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(MyRegex.isMatch(".*:(done|current)", line)){
                    lines.add(line);
                }
            }
            sc.close();
            
            PrintWriter writer = new PrintWriter(file);
            
            for(String line : lines){
                if(line.contains(name))
                    continue;
                if(line.contains(info) && !line.contains(name))
                    continue;
                writer.write(line + "\n");
            }
            writer.write(name + ":" + info);
            
            writer.close();
        }
        catch (Exception e) { }
    }
    
    /**
     * Charge un niveau selon un fichier
     * @param path : le fichier txt du niveau
     * @throws InstantiationError 
     */
    public void load(FileLevel path) throws InstantiationError{
        current = loader.loadLevel(path);
    }
    
    /**
     * Sauvegarde ue niveau
     */
    public void save() {
        save(System.getProperty("user.dir").replace("\\dist", "") + "/file/savedLevel.txt");
        reWriteSpecific(current.getName(), "current");
    }
    
    /**
     * Sauvegarde un niveau vers un fuichier txt
     * @param path : le fichier
     */
    public void save(String path) {
        saver.saveLevel(path, current);
    }
    
    /**
     * Met fin au jeu
     */
    public void finish() {
        //TODO
    }
    
    public Level getCurrent() { return current; }
    
    public void setCurrent(Level lvl) { current = lvl; }
    
    public List<Entite> getAllEntite() { return current.getAllEntites(); }
}
