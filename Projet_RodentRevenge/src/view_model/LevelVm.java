/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

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
    private ObservableList<MyImageView> obsImages = FXCollections.observableArrayList();
    private ListProperty<MyImageView> imagesProperty = new SimpleListProperty(obsImages);
        public ObservableList<MyImageView> getImages(){ return imagesProperty.get(); }
        public void setImages(ObservableList<MyImageView> newValue){ imagesProperty.set(newValue); }
        public ListProperty<MyImageView> getImagesProperty(){ return imagesProperty; }
        
    private ClockVm cm;
    private MyImageView sourisVm;
    
    public ClockVm getClockVm() { return cm; }
    public MyImageView getSourisVm() { return sourisVm; }

    private Level model = new Level(10, 8);
    
    public LevelVm() {
        cm = new ClockVm(model.getClock());
        sourisVm = new MyImageView(model.getSouris());
        ajouter(new MyImageView(new Chat(1, 1, "/resources/textures/enemySleep.png")));
        ajouter(new MyImageView(new Chat(2, 2, "/resources/textures/enemySleep.png")));
    }
        
    public void ajouter(MyImageView iv) { model.ajouter((Chat)iv.getModel()); obsImages.add(iv); }
    
    public void faireJouerChat() {
        model.faireJouerChat();
        for(MyImageView iv : obsImages){
            iv.setMyProperties();
        }
    }
    
    public ObservableList<MyImageView> getAllEntites() {
        ObservableList<MyImageView> res = FXCollections.observableArrayList();
        res.addAll(obsImages);
        res.add(sourisVm);
        return res;
    }
}
