/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;

/**
 *
 * @author Eleme
 */
public class Chat extends Entite {

    private StratEtatChat stratEtat;
    
    public Chat(double x, double y, String img) {
        super(x, y, img);
        TYPE = "Chat";
        changerStratEtat(new Normal(this));
    }
    
    @Override
    public boolean deplacer(ChangePosition cp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void jouer() {
        //TODO
        setX(0);
        changerStratEtat(new Sleep(this));
    }
    
    public void changerStratEtat(StratEtatChat e) {
        stratEtat = e;
        image = e.getImage();
    }
}
