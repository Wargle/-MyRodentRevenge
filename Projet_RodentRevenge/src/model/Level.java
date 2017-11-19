/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Eleme
 */
public class Level { //TODO
    public static Map params = new HashMap<String, Object>();
    
    private List<Chat> es = new ArrayList<>();
    private Clock c = new Clock();
    private Entite souris;
    
    public Level(double tailleImg, int tailleX, int tailleY) {
        params.put("IMAGE_SIZE", tailleImg);
        params.put("HORIZONTAL_MAX", tailleX);
        params.put("VERTICAL_MAX", tailleY);
        
        souris = new Souris(5, 2, "/resources/textures/player.png");
    }
    
    public void ajouter(Chat i) { es.add(i); }
    
    public void faireJouerChat() {
        for(Chat c : es){
            c.jouer();
        }
    }
    
    public Clock getClock() { return c; }
    
    public Entite getSouris() { return souris; }
    
    public ArrayList<Entite> getAllEntites() {
        ArrayList<Entite> res = new ArrayList<Entite>();
        res.addAll(es);
        res.add(souris);
        return res;
    }
}
