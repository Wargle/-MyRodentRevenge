/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FileLevel;

/**
 *
 * @author Eleme
 */
public class ListFileLevel {
    private ObservableList<FileLevelVm> lesLevels = FXCollections.observableArrayList();
    private final ListProperty<FileLevelVm> obsLevels = new SimpleListProperty<FileLevelVm>(lesLevels);

    public ObservableList getObsLevels() {
        return obsLevels.get();
    }

    public void setObsLevels(ObservableList value) {
        obsLevels.set(value);
    }

    public ListProperty obsLevelsProperty() {
        return obsLevels;
    }

    public ListFileLevel() {
    }
    
    public void ajouterFileLevel(FileLevelVm f){
        lesLevels.add(f);
    }
}
