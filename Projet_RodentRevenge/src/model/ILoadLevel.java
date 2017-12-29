/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Interface pour le chargement d'un niveau
 * @author Alexis Arnould
 */
public interface ILoadLevel {
    /**
     * Permet de convertir un fichier en niveau
     * @param pathFile : le fichier à chatger
     * @return le niveau
     */
    public Level loadLevel(FileLevel pathFile);
}
