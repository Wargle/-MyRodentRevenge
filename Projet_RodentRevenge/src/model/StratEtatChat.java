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
public abstract class StratEtatChat {
    protected Image image;
    protected Chat chat;
    
    public abstract ChangePosition calculMove();
    
    public Image getImage() { return image; }
}
