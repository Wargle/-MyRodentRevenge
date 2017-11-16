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
public abstract class Entite {
    protected Image image;
    
    protected double x = 2, y = 3;
    
    protected String TYPE;
    
    public Entite(double x, double y, String img){
        image = new Image(img);
        this.x = x;
        this.y = y;
        TYPE = "Entite";
    }
    
    public void setImage(Image i) {
        image = i;
    }
    public Image getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public abstract boolean deplacer(ChangePosition cp);/* {
        x += cp.getChangeC();
        y += cp.getChangeL();
    }*/
    
    public String getTYPE() { return TYPE; }
}
