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
 *
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
    
    public void load(String path) {
        current = loader.loadLevel(path);
    }
    
    public void save() {
        saver.saveLevel(System.getProperty("user.dir").replace("\\dist", "") + "/file/savedLevel.txt", current);
        reWriteSpecific(current.getName(), "current");
    }
    
    public void finish() {
        //TODO
    }
    
    public Level getCurrent() { return current; }
    
    public List<Entite> getAllEntite() { return current.getAllEntites(); }
}
