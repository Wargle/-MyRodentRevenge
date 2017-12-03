/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import model.FileLevel;

/**
 *
 * @author Alexis Arnould
 */
public class FileLevelVm {

    private final StringProperty bindNom = new SimpleStringProperty();

    public String getBindNom() {
        return bindNom.get();
    }

    public void setBindNom(String value) {
        bindNom.set(value);
    }

    public StringProperty bindNomProperty() {
        return bindNom;
    }
    
    private final StringProperty bindPath = new SimpleStringProperty();

    public String getBindPath() {
        return bindPath.get();
    }

    public void setBindPath(String value) {
        bindPath.set(value);
    }

    public StringProperty bindPathProperty() {
        return bindPath;
    }
    
    private final ObjectProperty<Color> bindColor = new SimpleObjectProperty<>();

    public Color getBindColor() {
        return bindColor.get();
    }

    public void setBindColor(Color value) {
        bindColor.set(value);
    }

    public ObjectProperty bindColorProperty() {
        return bindColor;
    }
    
    private FileLevel model;

    public FileLevelVm(String nom, String path, Color c) {
        model = new FileLevel(nom, path);
        setBindColor(c);
        addMyProperties();
        setMyProperties();
    }
    
    public FileLevelVm(String nom, String path) {
        this(nom, path, Color.BLACK);
    }
    
    public void addMyProperties(){
        bindNomProperty().addListener((observable, oldValue, newValue) -> {
            model.setNom(newValue);
        });
        bindPathProperty().addListener((observable, oldValue, newValue) -> {
            model.setPath(newValue);
        });
    }
    
    public void setMyProperties(){
        setBindNom(model.getNom());
        setBindPath(model.getPath());
    }

    public FileLevel getModel() {
        return model;
    }
    
    public boolean equalsByName(String n) {
        return model.equalsByName(n);
    }
}
