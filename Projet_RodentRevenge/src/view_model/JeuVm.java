/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

import model.Block;
import model.Border;
import model.Chat;
import model.Jeu;
import model.Level;
import model.Souris;

/**
 *
 * @author Eleme
 */
public class JeuVm {
    private Jeu model;
    private LevelVm current;

    public JeuVm(String path) {
        
        model = new Jeu();
        model.load(path);
        
        current = new LevelVm(model.getCurrent());
        current.setAllEntiteVm();
    }
    
    public LevelVm getCurrent() { return current; }
    
    public Jeu getModel() { return model; }
}
