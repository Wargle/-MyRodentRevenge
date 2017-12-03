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
public abstract class StratEtatChat {
    protected Image image;
    protected Chat chat;

    public StratEtatChat(String img, Chat c) {
        chat = c;
        
        double size = (int) Level.params.get("IMAGE_SIZE");
        image = new Image(img, size, size, true, true);
    }
    
    
    protected boolean canMove() {
        //TOFO
        return false;
    }
    
    public abstract ChangePosition calculMove();
    
    public Image getImage() { return image; }
}
