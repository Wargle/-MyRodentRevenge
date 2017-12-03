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
public class Normal extends StratEtatChat {

    public Normal(Chat c) {
        super("/resources/textures/enemyNormal.png", c);
    }

    @Override
    public ChangePosition calculMove() {
        //TODO
        if(canMove()) {
            return null;
        }
        return null;
    }
    
}
