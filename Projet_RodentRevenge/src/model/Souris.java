/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Eleme
 */
public class Souris extends Entite{

    public Souris(double x, double y, String img) {
        super(x, y, img);
        TYPE = "Souris";
    }

    @Override
    public boolean deplacer(ChangePosition cp) {
        //TODO
        double tempX = x + cp.getChangeC(), tempY = y + cp.getChangeL();
        if(tempX >= 0 && tempX < (int) Level.params.get("HORIZONTAL_MAX") && tempY >= 0 && tempY < (int) Level.params.get("VERTICAL_MAX")) {
            x += cp.getChangeC();
            y += cp.getChangeL();
        }
        return true;
    }
}
