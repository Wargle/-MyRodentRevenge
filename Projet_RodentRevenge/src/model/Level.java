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
 * Classe qui permet de stocker toutes les Entites
 * @author Alexis Arnould
 */
public class Level {
    private String name;
    
    public static Map params = new HashMap<String, Object>();
    
    private List<Chat> cs = new ArrayList<>();
    private List<Block> bs = new ArrayList<>();
    private Entite souris;
    
    private Clock c;
    private GestPosition gest;
    
    public Level(String n, int tailleImg, int tailleX, int tailleY) {
        gest = new GestPosition();
        name = n;
        
        params.put("IMAGE_SIZE", tailleImg);
        params.put("HORIZONTAL_MAX", tailleX);
        params.put("VERTICAL_MAX", tailleY);
    }
    
    /**
     * Ajoute une Souris au niveau
     * @param s : la Souris
     * @return 
     */
    public boolean ajouter(Souris s){
        if(souris != null) {
            gest.removePosition(souris.getX(), souris.getY());
        }
        if(gest.addPosition(s.getX(), s.getY(), s)) {
            souris = s;
            gest.notifySourisMove(s.getX(), s.getY());
            return true;
        }
        return false;
    }
    
    /**
     * Ajoute un Chat au niveau
     * @param c : le Chat
     * @return 
     */
    public boolean ajouter(Chat c) { 
        if(gest.addPosition(c.getX(), c.getY(), c)) {
            cs.add(c); 
            return true;
        }
        return false;
    }
    
    /**
     * Ajoute un Block ou Border au niveau
     * @param b : le Block/Border
     * @return 
     */
    public boolean ajouter(Block b) { 
        if(gest.addPosition(b.getX(), b.getY(), b)) {
            bs.add(b);
            return true;
        }
        return false;
    }
    
    /**
     * Enleve une Entite du niveau
     * @param x : la coordonnée x de l'Entite
     * @param y : la coordonnée y de l'Entite
     */
    public void enlever(int x, int y) {
        Entite e = gest.removePosition(x, y);
        switch(e.getTYPE()) {
            case "Souris": souris = null; break;
            case "Chat": cs.remove((Chat) e); break;
            default: bs.remove((Block) e); break;
        }
    }
    
    /**
     * Fait jouer à tour de rôle tous les Chats du niveau
     */
    public void faireJouerChat() {
        int allBlock = 0;
        for(Chat c : cs){
            if(!c.jouer())
                allBlock++;
        }
        if(allBlock == cs.size())
            Level.endLevel(true);
    }
    
    public String getName() { return name; }
    
    public Clock getClock() { return c; }
    
    public void setClock(int s, int m) { c = new Clock(m, s); }
    
    public Entite getSouris() { return souris; }
    
    public GestPosition getGest() { return gest; }
    
    /**
     * Renvoit toutes les Entites du niveau
     * @return la liste des Entites
     */
    public ArrayList<Entite> getAllEntites() {
        ArrayList<Entite> res = new ArrayList<Entite>();
        res.addAll(cs);
        res.addAll(bs);
        if(souris != null)
            res.add(souris);
        return res;
    }
    
    /**
     * Specifie quand le niveau est fini
     * @param isWin  : si le joueur à gagner ou non
     */
    public static void endLevel(boolean isWin) {
        System.out.println(isWin);
        System.exit(0);
    }
}
