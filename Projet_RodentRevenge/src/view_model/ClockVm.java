/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Clock;

/**
 *
 * @author Alexis Arnould
 */
public class ClockVm {

    private final StringProperty bindSec = new SimpleStringProperty();

    public String getBindSec() {
        return bindSec.get();
    }

    public void setBindSec(String value) {
        bindSec.set(value);
    }

    public StringProperty bindSecProperty() {
        return bindSec;
    }
    private final StringProperty bindMin = new SimpleStringProperty();

    public String getBindMin() {
        return bindMin.get();
    }

    public void setBindMin(String value) {
        bindMin.set(value);
    }

    public StringProperty bindMinProperty() {
        return bindMin;
    }
    
    private Clock model;
    
    public ClockVm(Clock c) {
        model = c;
        //addMyProperties();
        setMyProperties();
    }

    public void addMyProperties(){
        bindSec.addListener((observable, oldValue, newValue) -> {
            model.setSec(Integer.parseInt(newValue));
        });
        bindMin.addListener((observable, oldValue, newValue) -> {
            model.setMin(Integer.parseInt(newValue));
        });
    }
    
    public void setMyProperties() {
        bindSec.set(String.format("%02d", model.getSec()));
        bindMin.set(String.format("%02d", model.getMin()));
    }
    
    public Clock getModel() {
        return model;
    }
    
    public void increment() {
        model.increment();
        bindSec.setValue(String.format("%02d", model.getSec()));
        bindMin.set(String.format("%02d", model.getMin()));
    }
}
