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
public abstract class Entite {
    protected GestPosition refGest;
    
    protected Image image;
    
    protected double x = 2, y = 3;
    
    protected String TYPE, CODE;
    
    public Entite(GestPosition g, double x, double y, String img){
        double size = (int) Level.params.get("IMAGE_SIZE");
        refGest = g;

        image = new Image(img, size, size, true, true);
        this.x = x;
        this.y = y;
    }
    
    public void setImage(Image i) { image = i; }
    public Image getImage() { return image; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    
    public abstract boolean deplacer(Position cp);
    
    public String getTYPE() { return TYPE; }
    
    public String getCODE() { return CODE; }
    
    protected void notifyMove(double oC, double oL) {
        refGest.changePosition(oC, x, oL, y);
    }
}
