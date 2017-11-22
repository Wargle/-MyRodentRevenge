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
public class Block extends Entite {
    
    public Block(GestPosition g, double x, double y) {
        super(g, x, y, "/resources/textures/block.png");
        TYPE = "Block";
    }
    
    protected Block(GestPosition g, double x, double y, String img){
        super(g, x, y, img);
    }
    
    @Override
    public boolean deplacer(ChangePosition cp) {
        double tempX = x + cp.getChangeC(), tempY = y + cp.getChangeL();
        if(tempX >= 0 && tempX < (int) Level.params.get("HORIZONTAL_MAX") && tempY >= 0 && tempY < (int) Level.params.get("VERTICAL_MAX")) {
            Entite getE = refGest.getEntite(tempX, tempY);
            if(getE == null) {
                x += cp.getChangeC();
                y += cp.getChangeL();
                notifyMove(x - cp.getChangeC(), y - cp.getChangeL());
            }
            else {
                if(!getE.getTYPE().equals("Chat") && getE.deplacer(cp)) {
                    x += cp.getChangeC();
                    y += cp.getChangeL();
                    notifyMove(x - cp.getChangeC(), y - cp.getChangeL());
                }
                else
                    return false;
            }
            return true;
        }
        return false;
    }
}
