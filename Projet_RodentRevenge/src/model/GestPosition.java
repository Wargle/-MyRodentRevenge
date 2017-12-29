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
 * Classe qui permet de stocker les positions de toutes les Entites du niveau
 * @author Alexis Arnould
 */
public class GestPosition {
    private Map<String, Entite> allPos = new HashMap<String, Entite>();
    public double xSouris, ySouris;
    
    public GestPosition(){ }
    
    /**
     * Notifie que la Souris à changer de Position
     * @param x : la nouvelle coordonnée x de la Souris
     * @param y : la nouvelle coordonnée y de la Souris
     */
    public void notifySourisMove(double x, double y) {
        xSouris = x;
        ySouris = y;
    }
    
    /**
     * Enregistre une nouvelle Entite
     * @param x : la coordonnée x de l'Entite
     * @param y : la coordonnée y de l'Entite
     * @param e : l'Entite
     * @return si oui on non l'Entite a pu être enregistrée
     */
    public boolean addPosition(double x, double y, Entite e) {
        String pos = x + "x" + y;
        if(!allPos.containsKey(pos)) {
            allPos.put(pos, e);
            return true;
        }
        return false;
    }
    
    /**
     * Supprime une Entite
     * @param x : la coordonnée x de l'Entite
     * @param y : la coordonnée y de l'Entite
     * @return l'Entite supprimé
     */
    public Entite removePosition(double x, double y) {
        String pos = x + "x" + y;
        Entite e = null;
        if(allPos.containsKey(pos)) {
            e = allPos.remove(pos);
        }
        return e;
    }
    
    /**
     * Permet d'obtenir une Entite
     * @param x : la coordonnée x de l'Entite
     * @param y : la coordonnée y de l'Entite
     * @return l'Entite
     */
    public Entite getEntite(double x, double y) {
        String req = x + "x" + y;
        return allPos.get(req);
    }
    
    /**
     * Notifie qu'une Entite a été déplacé
     * @param oX : l'ancienne coordonnée x de l'Entite
     * @param nX : l'ancienne coordonnée y de l'Entite
     * @param oY : la nouvelle coordonnée x de l'Entite
     * @param nY : la nouvelle coordonnée y de l'Entite 
     */
    public void changePosition(double oX, double nX, double oY, double nY) {
        String oldP = oX + "x" + oY, newP = nX + "x" + nY;
        if(allPos.containsKey(oldP) && !allPos.containsKey(newP)) {
            Entite e = allPos.get(oldP);
            allPos.remove(oldP);
            allPos.put(newP, e);
        }
    }
    
    public double getXSouris() { return xSouris; }
    
    public double getYSouris() { return ySouris; }
}
