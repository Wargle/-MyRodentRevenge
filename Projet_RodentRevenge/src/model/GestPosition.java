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
import java.util.Map.Entry;

/**
 *
 * @author Alexis Arnould
 */
public class GestPosition {
    private Map<String, Entite> allPos = new HashMap<String, Entite>();
    
    public GestPosition(){ }
    
    public boolean addPosition(double x, double y, Entite e) {
        String pos = x + "x" + y;
        if(!allPos.containsKey(pos)) {
            allPos.put(pos, e);
            return true;
        }
        return false;
    }
    
    public Entite removePosition(double x, double y) {
        String pos = x + "x" + y;
        Entite e = null;
        if(allPos.containsKey(pos)) {
            e = allPos.remove(pos);
        }
        return e;
    }
    
    public Entite getEntite(double x, double y) {
        String req = x + "x" + y;
        return allPos.get(req);
    }
    
    public void changePosition(double oX, double nX, double oY, double nY) {
        String oldP = oX + "x" + oY, newP = nX + "x" + nY;
        if(allPos.containsKey(oldP) && !allPos.containsKey(newP)) {
            Entite e = allPos.get(oldP);
            allPos.remove(oldP);
            allPos.put(newP, e);
        }
    }
}
