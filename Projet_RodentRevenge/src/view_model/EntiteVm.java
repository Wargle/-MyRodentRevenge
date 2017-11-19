/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ChangePosition;
import model.Entite;

/**
 *
 * @author Eleme
 */
public class EntiteVm extends ImageView {

    private final ObjectProperty bindImage = new SimpleObjectProperty<>();
    public Object getBindImage() { return bindImage.get(); }
    public void setBindImage(Object value) { bindImage.set(value); }
    public ObjectProperty bindImageProperty() { return bindImage; }
    
    private final DoubleProperty bindX = new SimpleDoubleProperty();

    public double getBindX() {
        return bindX.get();
    }

    public void setBindX(double value) {
        bindX.set(value * (double) LevelVm.params.get("IMAGE_SIZE"));
    }

    public DoubleProperty bindXProperty() {
        return bindX;
    }
    private final DoubleProperty bindY = new SimpleDoubleProperty();

    public double getBindY() {
        return bindY.get();
    }

    public void setBindY(double value) {
        bindY.set(value * (double) LevelVm.params.get("IMAGE_SIZE"));
    }

    public DoubleProperty bindYProperty() {
        return bindY;
    }
    
    private Entite model;
    
    public EntiteVm(Entite m){
        model = m;
        setMyProperties();
        //addMyProperties();
        
        imageProperty().bindBidirectional(bindImage);
        xProperty().bindBidirectional(bindX);
        yProperty().bindBidirectional(bindY);
    }
    
    public void addMyProperties(){
        bindImageProperty().addListener((observable, oldValue, newValue) -> {
            model.setImage((Image) newValue);
        });
       bindXProperty().addListener((observable, oldValue, newValue) -> {
            model.setX((double) newValue);
        });
       bindYProperty().addListener((observable, oldValue, newValue) -> {
            model.setY((double) newValue);
        });
    }
    
    public void setMyProperties(){
        setBindImage(model.getImage());
        setBindX(model.getX());
        setBindY(model.getY());
    }
    
    public Entite getModel() { return model; }
    
    public void deplacer(ChangePosition cp) {
        model.deplacer(cp);
        setMyProperties();
    }
}
