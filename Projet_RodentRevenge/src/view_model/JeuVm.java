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
 * @author Alexis Arnould
 */
public class JeuVm {
    private Jeu model;
    private LevelVm current;

    public JeuVm(FileLevelVm path) throws InstantiationError {
        
        model = new Jeu();
        model.load(path.getModel());
        
        current = new LevelVm(model.getCurrent());        
        current.setAllEntiteVm();
        current.sourisVm = new EntiteVm(model.getCurrent().getSouris());
    }
    
    public JeuVm() {
        model = new Jeu();
        Level lvl = new Level("", 32, 10, 10);
        lvl.setClock(0, 0);
        current = new LevelVm(lvl);
        model.setCurrent(lvl);
    }
    
    public LevelVm getCurrent() { return current; }
    
    public Jeu getModel() { return model; }
}
