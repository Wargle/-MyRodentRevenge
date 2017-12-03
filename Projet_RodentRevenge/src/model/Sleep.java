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
public class Sleep extends StratEtatChat {

    public Sleep(Chat c) {
        super("/resources/textures/enemySleep.png", c);
    }

    @Override
    public ChangePosition calculMove() {
        //TODO
        if(canMove()) {
            return new ChangePosition(0, 1);
        }
        return null;
    }
    
}
