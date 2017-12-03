/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;

/**
 *
 * @author Alexis Arnould
 */
public class Chat extends Entite {

    private StratEtatChat stratEtat;
    
    public Chat(GestPosition g, double x, double y, String img) {
        super(g, x, y, img);
        stratEtat = new Normal(this);
        TYPE = "Chat";
        CODE = "2";
    }
    
    public Chat(GestPosition g, double x, double y){
        this(g, x, y, "/resources/textures/enemyNormal.png");
    }
    
    @Override
    public boolean deplacer(ChangePosition cp) {
        //TODO
        x += cp.getChangeC();
        y += cp.getChangeL();
        return true;
    }
    
    public void jouer() {
        //TODO
        ChangePosition cp = stratEtat.calculMove();
        if(cp == null){
            changerStratEtat(new Sleep(this));
        }
        else {
            changerStratEtat(new Normal(this));
            deplacer(cp);
        }
    }
    
    public void changerStratEtat(StratEtatChat e) {
        stratEtat = e;
        image = e.getImage();
    }
}
