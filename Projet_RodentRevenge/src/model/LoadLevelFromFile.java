/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Eleme
 */
public class LoadLevelFromFile implements ILoadLevel{

    @Override
    public Level loadLevel(String pathFile) {
        Level lvl = new Level(32, 10, 10);
        
        //TODO
        lvl.ajouter(new Souris(lvl.getGest(), 5, 2));
        
        lvl.ajouter(new Chat(lvl.getGest(), 1, 1));
        lvl.ajouter(new Chat(lvl.getGest(), 2, 2));
        
        lvl.ajouter(new Block(lvl.getGest(), 5, 6));
        lvl.ajouter(new Block(lvl.getGest(), 6, 6));
        lvl.ajouter(new Border(lvl.getGest(), 7, 6));
        
        return lvl;
    }
    
}
