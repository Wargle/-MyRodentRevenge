/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;

/**
 * Classe fille d'Entite correspondant aux Ennemis
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
    
    /**
     * Permet de déplacer le Chat vers une position
     * @param cp La position vers laquelle le Chat bouge
     * @return si le Chat a pu se deplacer
     */
    @Override
    public boolean deplacer(Position cp) {
        //System.out.println("Chat");
        double tempX = x + cp.getX(), tempY = y + cp.getY();
        if(tempX >= 0 && tempX < (int) Level.params.get("HORIZONTAL_MAX") && tempY >= 0 && tempY < (int) Level.params.get("VERTICAL_MAX")) {
            Entite getE = refGest.getEntite(tempX, tempY);
            if(getE == null) {
                x += cp.getX();
                y += cp.getY();
                notifyMove(x - cp.getX(), y - cp.getY());
            }
        }
        return false;
    }
    
    /**
     * Fait jouer le Chat
     * @return si le chat a pu jouer ou non
     */
    public boolean jouer() {
        Position cp = stratEtat.calculMove();
        if(cp == null){
            changerStratEtat(new Sleep(this));
            return false;
        }
        else {
            changerStratEtat(new Normal(this));
            deplacer(cp);
            return true;
        }
    }
    
    /**
     * Change l'état du Chat
     * @param e : le nouvel état
     */
    public void changerStratEtat(StratEtatChat e) {
        stratEtat = e;
        image = e.getImage();
    }
    
    /**
     * Specifie que le Chat a attrapé la Souris
     */
    public void sourisMange() {
        Level.endLevel(false);
    }
}
