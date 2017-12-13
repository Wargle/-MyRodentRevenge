/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

import java.util.Map;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

/**
 *
 * @author Alexis Arnould
 */
public class LevelVm {
    public static Map params;
    
    private ObservableList<EntiteVm> obsImages = FXCollections.observableArrayList();
    private ListProperty<EntiteVm> imagesProperty = new SimpleListProperty(obsImages);
        public ObservableList<EntiteVm> getImages(){ return imagesProperty.get(); }
        public void setImages(ObservableList<EntiteVm> newValue){ imagesProperty.set(newValue); }
        public ListProperty<EntiteVm> getImagesProperty(){ return imagesProperty; }
        
    private ClockVm cm;
    public EntiteVm sourisVm;
    
    public ClockVm getClockVm() { return cm; }
    public EntiteVm getSourisVm() { return sourisVm; }

    private Level model;

    public LevelVm(Level model) {
        params = model.params;
        
        this.model = model;
        cm = new ClockVm(model.getClock());
    }
    
    public Level getModel() { return model; }
    
    public void setAllEntiteVm() {
        obsImages.clear();
        if(sourisVm != null)
            sourisVm = new EntiteVm(model.getSouris());
        for(Entite e : model.getAllEntites()) {
            obsImages.add(new EntiteVm(e));
        }
    }
    
    public void faireJouerChat() {
        model.faireJouerChat();
        for(EntiteVm iv : obsImages){
            iv.updateProperties();
        }
    }
    
    public void faireJouerSouris(Position cp) {
        sourisVm.deplacer(cp);
        for(EntiteVm em : obsImages)
            em.updateProperties();
    }
    
    public ObservableList<EntiteVm> getAllEntites() {
        ObservableList<EntiteVm> res = FXCollections.observableArrayList();
        res.addAll(obsImages);
        if(sourisVm != null)
            res.add(sourisVm);
        return res;
    }
}
