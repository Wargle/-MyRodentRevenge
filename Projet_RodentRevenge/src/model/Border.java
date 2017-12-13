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
public class Border extends Block {
    
    public Border(GestPosition g, double x, double y) {
        super(g, x, y, "/resources/textures/border.png");
        TYPE = "Border";
        CODE = "4";
    }
    
    @Override
    public boolean deplacer(Position cp) {
        return false;
    }
}
