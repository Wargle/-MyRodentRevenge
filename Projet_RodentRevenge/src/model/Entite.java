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
    protected GestPosition refGest;
    
    protected Image image;
    
    protected double x = 2, y = 3;
    
    protected String TYPE;
    
    public Entite(GestPosition g, double x, double y, String img){
        double size = (double) Level.params.get("IMAGE_SIZE");
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
    
    public abstract boolean deplacer(ChangePosition cp);
    
    public String getTYPE() { return TYPE; }
    
    protected void notifyMove(double oC, double oL) {
        refGest.changePosition(oC, x, oL, y);
    }
}
