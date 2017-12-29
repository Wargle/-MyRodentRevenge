/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe fille d'Entite correspondant au Joueur
 * @author Alexis Arnould
 */
public class Souris extends Entite{

    public Souris(GestPosition g, double x, double y) {
        super(g, x, y, "/resources/textures/player.png");
        TYPE = "Souris";
        CODE = "1";
    }

    /**
     * Permet de déplacer la souris vers une position
     * @param cp La position vers laquelle la souris bouge
     * @return si la Souris a pu se deplacer
     */
    @Override
    public boolean deplacer(Position cp) {
        //System.out.println("Souris");
        double tempX = x + cp.getX(), tempY = y + cp.getY();
        if(tempX >= 0 && tempX < (int) Level.params.get("HORIZONTAL_MAX") && tempY >= 0 && tempY < (int) Level.params.get("VERTICAL_MAX")) {
            Entite getE = refGest.getEntite(tempX, tempY);
            if(getE == null) {
                x += cp.getX();
                y += cp.getY();
                notifyMove(x - cp.getX(), y - cp.getY());
                refGest.notifySourisMove(x, y);
            }
            else {
                if(!getE.getTYPE().equals("Chat") && getE.deplacer(cp)) {
                    x += cp.getX();
                    y += cp.getY();
                    notifyMove(x - cp.getX(), y - cp.getY());
                    refGest.notifySourisMove(x, y);
                }
            }
            return true;
        }
        return false;
    }
}
