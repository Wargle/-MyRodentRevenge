/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Alexis Arnould
 */
public class Normal extends StratEtatChat {

    public Normal(Chat c) {
        super("/resources/textures/enemyNormal.png", c);
    }

    @Override
    public Position calculMove() {
        Random r = new Random();
        List<Position> pos;
        if(!(pos = posibleMove()).isEmpty()) {
            int what = r.nextInt(pos.size());
            return pos.get(what);
        }
        return null;
    }
    
}
