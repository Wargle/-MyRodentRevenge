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
import model.Chat;
import model.Level;

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
        
        model = new Level(32, 10, 8);
        cm = new ClockVm(model.getClock());
        sourisVm = new EntiteVm(model.getSouris());
        
        ajouter(new EntiteVm(new Chat(1, 1, "/resources/textures/enemyNormal.png")));
        ajouter(new EntiteVm(new Chat(2, 2, "/resources/textures/enemyNormal.png")));
    }
        
    public void ajouter(EntiteVm iv) { model.ajouter((Chat)iv.getModel()); obsImages.add(iv); }
    
    public void faireJouerChat() {
        model.faireJouerChat();
        for(EntiteVm iv : obsImages){
            iv.setMyProperties();
        }
    }
    
    public ObservableList<EntiteVm> getAllEntites() {
        ObservableList<EntiteVm> res = FXCollections.observableArrayList();
        res.addAll(obsImages);
        res.add(sourisVm);
        return res;
    }
}
