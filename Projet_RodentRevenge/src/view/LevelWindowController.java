/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.ChangePosition;
import view_model.*;

/**
 *
 * @author Eleme
 */
public class LevelWindowController implements Initializable {
    
    private boolean isPause = true;
    
    Map<KeyCode, Predicate> mapControl = new HashMap<KeyCode, Predicate>();
    
    @FXML
    private Pane mainPane;
    
    @FXML
    private Label label;
    
    //TODO
    LevelVm lm = new LevelVm();
    EntiteVm souris;
    
    Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(1000),
        ex -> test2()));
    
    void test() {
        System.out.println("0.5 sec");
    }
    void test2() {
        test();
        System.out.println("1 sec");
        lm.getClockVm().increment();
    }
    
    private void togglePlayPause(){
        if(isPause)
            timeline.play();
        else
            timeline.pause();
        isPause = !isPause;
    }
    
    private void faireJouerSouris(ChangePosition c) {
        souris.deplacer(c);
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {        
        togglePlayPause();
        lm.faireJouerChat();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapControl.put(KeyCode.UP, (s) -> { faireJouerSouris(new ChangePosition(0, -1)); return true; });
        mapControl.put(KeyCode.DOWN, (s) -> { faireJouerSouris(new ChangePosition(0, 1)); return true; });
        mapControl.put(KeyCode.LEFT, (s) -> { faireJouerSouris(new ChangePosition(-1, 0)); return true; });
        mapControl.put(KeyCode.RIGHT, (s) -> { faireJouerSouris(new ChangePosition(1, 0)); return true; });
        mapControl.put(KeyCode.SPACE, (s) -> { togglePlayPause(); return true; });
        
        mainPane.setPrefSize((int) LevelVm.params.get("HORIZONTAL_MAX") * (double) LevelVm.params.get("IMAGE_SIZE"),
                (int) LevelVm.params.get("VERTICAL_MAX") * (double) LevelVm.params.get("IMAGE_SIZE"));
        
        souris = lm.getSourisVm();
        
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), ex -> test()));
        
        label.textProperty().bind(lm.getClockVm().bindMinProperty().concat(":").concat(lm.getClockVm().bindSecProperty()));
        
        timeline.setCycleCount(-1);
        mainPane.getChildren().addAll(lm.getAllEntites());
        
        mainPane.setOnKeyPressed((event) -> {
            Predicate p = mapControl.get(event.getCode());
            if(p != null && !isPause)
                p.test(null);
        });
    }    
    
}
