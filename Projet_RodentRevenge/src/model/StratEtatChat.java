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
import javafx.scene.image.Image;

/**
 * Classe utilisant un pattern Strategie pour definir le comportement du Chat selon son état
 * @author Alexis Arnould
 */
public abstract class StratEtatChat {
    protected Image image;
    protected Chat chat;

    public StratEtatChat(String img, Chat c) {
        chat = c;
        
        double size = (int) Level.params.get("IMAGE_SIZE");
        image = new Image(img, size, size, true, true);
    }
    
    /**
     * Permet d'avoir l'ensemble des positions accessibles à partir de la position du Chat
     * @return la liste des positions
     */
    protected List<Position> posibleMove() {
        List<Position> posibles = new ArrayList<>();
        GestPosition g = chat.refGest;
        List<Position> pos = new ArrayList<>();
        double x = chat.getX(), y = chat.getY();
        
        if(x != 0) pos.add(new Position(-1, 0));
        if(y != (int) Level.params.get("VERTICAL_MAX")) pos.add(new Position(0, 1));
        if(x != (int) Level.params.get("HORIZONTAL_MAX")) pos.add(new Position(1, 0));
        if(y != 0) pos.add(new Position(0, -1));
        
        for(Position p : pos) {
            Entite e = g.getEntite(x + p.getX(), y + p.getY());
            if(e == null)
                posibles.add(p);
            else if(e.getTYPE().equals("Souris"))
                chat.sourisMange();
        }
        return posibles;
    }
    
    /**
     * Methode abstraite qui permet de choisir quelle est la meilleur position dans la liste des possibles
     * @return la position choisie
     */
    public abstract Position calculMove();
    
    public Image getImage() { return image; }
}
