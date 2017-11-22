/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eleme
 */
public class GestPosition {
    private Map<String, Entite> allPos = new HashMap<String, Entite>();
    
    public GestPosition(){ }
    
    public boolean addPosition(double c, double l, Entite e) {
        String pos = c + "x" + l;
        if(!allPos.containsKey(pos)) {
            allPos.put(pos, e);
            return true;
        }
        return false;
    }
    
    public Entite getEntite(double c, double l) {
        String req = c + "x" + l;
        return allPos.get(req);
    }
    
    public void changePosition(double oC, double nC, double oL, double nL) {
        String oldP = oC + "x" + oL, newP = nC + "x" + nL;
        if(allPos.containsKey(oldP) && !allPos.containsKey(newP)) {
            Entite e = allPos.get(oldP);
            allPos.remove(oldP);
            allPos.put(newP, e);
        }
    }
}
