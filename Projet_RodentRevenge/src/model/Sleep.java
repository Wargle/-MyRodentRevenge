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
public class Sleep extends StratEtatChat {

    public Sleep(Chat c) {
        chat = c;
        image = new Image("/resources/textures/enemySleep.png");
    }

    @Override
    public ChangePosition calculMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
