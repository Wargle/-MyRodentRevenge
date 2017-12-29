/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Interface pour la sauvegarde d'un niveau
 * @author Alexis Arnould
 */
public interface ISaveLevel {
    /**
     * Permet de convertir un niveau en fichier
     * @param file : le fichier
     * @param level : le niveau
     */
    public void saveLevel(String file, Level level);
}
