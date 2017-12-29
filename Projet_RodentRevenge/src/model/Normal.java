/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Random;

/**
 * Classe Concrete du pattern Strategie
 * @author Alexis Arnould
 */
public class Normal extends StratEtatChat {

    public Normal(Chat c) {
        super("/resources/textures/enemyNormal.png", c);
    }

    /**
     * Méthode concrete : Choisit une position pour se rapprocher de la Souris
     * @return la position choisie
     */
    @Override
    public Position calculMove() {
        double xC = chat.getX(), yC = chat.getY(), xS = chat.getGest().getXSouris(), yS = chat.getGest().getYSouris(), minD = 1000;        
        Position move = null;
        
        List<Position> pos = posibleMove();
        
        if(!pos.isEmpty()) {
            for(Position p : pos) {
                double d = Math.sqrt(Math.pow((xC + p.getX() - xS), 2) + Math.pow((yC + p.getY() - yS), 2));
                if(d < minD) {
                    minD = d;
                    move = p;
                }
            }
            return move;
        }
        return null;
    }
    
}
