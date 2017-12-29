/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Classe qui permet de convertir un fichier txt en niveau
 * Utilise une liste d'intanciation pour les Entite
 * @author Alexis Arnould
 */
public class LoadLevelFromFile implements ILoadLevel{
    @FunctionalInterface
    public interface ThreeParamsFunction<L, X, Y> {
        public void apply(L l, X x, Y y);
    }
    
    private List<ThreeParamsFunction<Level, Integer, Integer>> whatEntite;

    public LoadLevelFromFile() {
        whatEntite = new ArrayList<>();
        whatEntite.add((l, x, y) -> { });
        whatEntite.add((l, x, y) -> { l.ajouter(new Souris(l.getGest(), x, y)); });
        whatEntite.add((l, x, y) -> { l.ajouter(new Chat(l.getGest(), x, y)); });
        whatEntite.add((l, x, y) -> { l.ajouter(new Block(l.getGest(), x, y)); });
        whatEntite.add((l, x, y) -> { l.ajouter(new Border(l.getGest(), x, y)); });
    }
    
    /**
     * Convertit la fichier en niveau
     * @param pathFile : la fichier à convertir
     * @return le niveau
     * @throws InstantiationError 
     */
    @Override
    public Level loadLevel(FileLevel pathFile) throws InstantiationError {
        String nom;
        int taille, xMax, yMax, x = 0, y = 0, min, sec;
        try {       
            Level lvl;
            File file = new File(pathFile.getPath());
            Scanner sc = new Scanner(file);
            
            if(!sc.hasNextLine())
                throw new InstantiationError();
            taille = Integer.parseInt(sc.nextLine());
            if(!sc.hasNextLine())
                throw new InstantiationError();
            xMax = Integer.parseInt(sc.nextLine());
            if(!sc.hasNextLine())
                throw new InstantiationError();
            yMax = Integer.parseInt(sc.nextLine());
            
            nom = pathFile.getNom().replace("Level ", "");
            lvl = new Level(nom, taille, xMax, yMax);
            
            if(!sc.hasNextLine())
                throw new InstantiationError();
            sec = Integer.parseInt(sc.nextLine());
            if(!sc.hasNextLine())
                throw new InstantiationError();
            min = Integer.parseInt(sc.nextLine());
            
            lvl.setClock(sec, min);
            
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] es = line.split(",");
                if(es.length != xMax)
                    throw new InstantiationError();
                for(String e : es){
                    whatEntite.get(Integer.parseInt(e)).apply(lvl, x, y);
                    x++;
                }
                x = 0;
                y++;
            }
            if(y != yMax)
                throw new InstantiationError();
            sc.close();
            
            return lvl;
        }
        catch (Exception e) {
            System.out.println(e);
            throw new InstantiationError();
        }
    }
}
