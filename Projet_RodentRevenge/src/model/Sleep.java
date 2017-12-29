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
public class Sleep extends StratEtatChat {

    public Sleep(Chat c) {
        super("/resources/textures/enemySleep.png", c);
    }

    /**
     * Méthode concrète
     * @return null si le Chat ne peut pas bouger sinon le Chat change d'état
     */
    @Override
    public Position calculMove() {
        List<Position> pos;
        if(!(pos = posibleMove()).isEmpty()) {
            return new Position(0, 0);
        }
        return null;
    }
    
}
