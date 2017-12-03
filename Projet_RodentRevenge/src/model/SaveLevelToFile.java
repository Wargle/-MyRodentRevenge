/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author Alexis Arnould
 */
public class SaveLevelToFile implements ISaveLevel {

    @Override
    public void saveLevel(String file, Level level) {
        try {
            File fi = new File(file);
            PrintWriter writer = new PrintWriter(fi);
            
            int x = (int)level.params.get("HORIZONTAL_MAX"), y = (int)level.params.get("VERTICAL_MAX");
            
            writer.write(level.params.get("IMAGE_SIZE").toString() + "\n");
            writer.write(x + "\n");
            writer.write(y + "");
            
            for(int ix = 0; ix < x; ix++) {
                writer.write("\n");
                for(int iy = 0; iy < y; iy++) {
                    if(iy != 0)
                        writer.write(",");
                    Entite e = level.getGest().getEntite(ix, iy);
                    if(e != null)
                        writer.write(e.getCODE());
                    else
                        writer.write("0");
                }
            }
            writer.write("\n" + level.getClock().getSec() + "\n");
            writer.write(level.getClock().getMin()+ "");
            
            writer.close();
            
        }
        catch (Exception e) {
            
        }
    }
    
}
