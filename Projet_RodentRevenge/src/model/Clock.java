/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe qui stocker l'état du compteur du Jeu
 * @author Alexis Arnould
 */
public class Clock {
    
    private Integer sec;

    /**
     * Get the value of sec
     *
     * @return the value of sec
     */
    public Integer getSec() {
        return sec;
    }

    /**
     * Set the value of sec
     *
     * @param sec new value of sec
     */
    public void setSec(Integer sec) {
        this.sec = sec;
    }

    private int min;

    /**
     * Get the value of min
     *
     * @return the value of min
     */
    public Integer getMin() {
        return min;
    }

    /**
     * Set the value of min
     *
     * @param min new value of min
     */
    public void setMin(int min) {
        this.min = min;
    }

    public Clock(int min, int sec) {
        this.sec = sec;
        this.min = min;
    }
    
    public Clock() {
        this(0, 0);
    }

    /**
     * Increment d'une seconde le compteur
     */
    public void increment(){
        sec++;
        if(sec == 60){
            sec = 0;
            min++;
        }
    }
}
