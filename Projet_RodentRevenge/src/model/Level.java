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
 * @author Alexis Arnould
 */
public class Level { //TODO
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
    
    public boolean ajouter(Chat c) { 
        if(gest.addPosition(c.getX(), c.getY(), c)) {
            cs.add(c); 
            return true;
        }
        return false;
    }
    
    public boolean ajouter(Block b) { 
        if(gest.addPosition(b.getX(), b.getY(), b)) {
            bs.add(b);
            return true;
        }
        return false;
    }
    
    public void enlever(int x, int y) {
        Entite e = gest.removePosition(x, y);
        switch(e.getTYPE()) {
            case "Souris": souris = null; break;
            case "Chat": cs.remove((Chat) e); break;
            default: bs.remove((Block) e); break;
        }
    }
    
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
    
    public ArrayList<Entite> getAllEntites() {
        ArrayList<Entite> res = new ArrayList<Entite>();
        res.addAll(cs);
        res.addAll(bs);
        if(souris != null)
            res.add(souris);
        return res;
    }
    
    public static void endLevel(boolean isWin) {
        System.out.println(isWin);
        System.exit(0);
    }
}
