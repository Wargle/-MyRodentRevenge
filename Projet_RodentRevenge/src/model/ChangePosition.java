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
public class ChangePosition {
    private int changeC, changeL;

    public ChangePosition(int cC, int cL) throws IllegalArgumentException {
        if((cC == 0 && cL == 1 || cL == -1) || (cC == 1 || cC == -1 && cL == 0)){
            changeC = cC;
            changeL = cL;
        }
        else throw new IllegalArgumentException("Les valeures doivent de 1 pour l'une et de 0 pour l'autre !");
    }

    public int getChangeC() {
        return changeC;
    }

    public int getChangeL() {
        return changeL;
    }
    
    
}
