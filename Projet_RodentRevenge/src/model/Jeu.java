/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Eleme
 */
public class Jeu {
    private Level current;
    private ILoadLevel loader;
    
    public Jeu() {
        loader = new LoadLevelFromFile();
    }
    
    public void load(String path) {
        current = loader.loadLevel(path);
    }
    
    public Level getCurrent() { return current; }
    
    public List<Entite> getAllEntite() { return current.getAllEntites(); }
}
