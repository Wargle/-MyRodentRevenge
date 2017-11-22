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
 * @author Eleme
 */
public class LevelVm {
    public static Map params;
    
    private ObservableList<EntiteVm> obsImages = FXCollections.observableArrayList();
    private ListProperty<EntiteVm> imagesProperty = new SimpleListProperty(obsImages);
        public ObservableList<EntiteVm> getImages(){ return imagesProperty.get(); }
        public void setImages(ObservableList<EntiteVm> newValue){ imagesProperty.set(newValue); }
        public ListProperty<EntiteVm> getImagesProperty(){ return imagesProperty; }
        
    private ClockVm cm;
    private EntiteVm sourisVm;
    
    public ClockVm getClockVm() { return cm; }
    public EntiteVm getSourisVm() { return sourisVm; }

    private Level model;
    
    public LevelVm() { //TODO
        params = model.params;
        
        model = new Level(32, 12, 12);
        cm = new ClockVm(model.getClock());
        sourisVm = new EntiteVm(model.getSouris());
        
        ajouterChat(new EntiteVm(new Chat(model.getGest(), 1, 1)));
        ajouterChat(new EntiteVm(new Chat(model.getGest(), 2, 2)));
        
        ajouterBlock(new EntiteVm(new Block(model.getGest(), 5, 6)));
        ajouterBlock(new EntiteVm(new Block(model.getGest(), 6, 6)));
        ajouterBlock(new EntiteVm(new Border(model.getGest(), 7, 6)));
    }
        
    public void ajouterChat(EntiteVm iv) { 
        if(model.ajouter((Chat)iv.getModel()))
            obsImages.add(iv); 
    }
    
    public void ajouterBlock(EntiteVm iv) { 
        if(model.ajouter((Block)iv.getModel()))
            obsImages.add(iv); 
    }
    
    public void faireJouerChat() {
        model.faireJouerChat();
        for(EntiteVm iv : obsImages){
            iv.updateProperties();
        }
    }
    
    public void faireJouerSouris(ChangePosition cp) {
        sourisVm.deplacer(cp);
        for(EntiteVm em : obsImages)
            em.updateProperties();
    }
    
    public ObservableList<EntiteVm> getAllEntites() {
        ObservableList<EntiteVm> res = FXCollections.observableArrayList();
        res.addAll(obsImages);
        res.add(sourisVm);
        return res;
    }
}
