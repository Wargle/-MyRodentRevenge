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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ChangePosition;
import view_model.*;

/**
 *
 * @author Eleme
 */
public class LevelWindowController implements Initializable {
    
    private boolean isPause = true;
    private TranslateTransition menuTranslation;
    
    Map<KeyCode, Predicate> mapControl = new HashMap<KeyCode, Predicate>();
    
    @FXML
    private GridPane window;
    
    @FXML
    private Pane mainPane, topPane;
    
    @FXML
    private GridPane menu;
    
    @FXML
    private Label compteur;
    
    @FXML
    private Button bQuit, bPlay;
    
    //TODO
    LevelVm lm = new LevelVm();
    
    Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(1000),
        ex -> test2()));
    
    void test() {
        //System.out.println("0.5 sec");
    }
    void test2() {
        test();
        //System.out.println("1 sec");
        lm.getClockVm().increment();
    }
    
    private void togglePlayPause(){
        if(isPause) {
            menuTranslation.setRate(1);
            menuTranslation.play();
            timeline.play();
        }
        else {
            menuTranslation.setRate(-1);
            menuTranslation.play();
            timeline.pause();
        }
        isPause = !isPause;
    }
    
    private void faireJouerSouris(ChangePosition c) {
        lm.faireJouerSouris(c);
    }
    
    @FXML
    private void buttonActionTogglePlayPause(ActionEvent event) {        
        togglePlayPause();
    }
    
    @FXML
    private void buttonActionQuit(ActionEvent event) {        
        Stage current = (Stage) window.getScene().getWindow();
        current.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuTranslation = new TranslateTransition(Duration.millis(500), menu);
        menuTranslation.setFromX(0);
        menuTranslation.setToX(-200);
    
        mapControl.put(KeyCode.UP, (s) -> { faireJouerSouris(new ChangePosition(0, -1)); return true; });
        mapControl.put(KeyCode.DOWN, (s) -> { faireJouerSouris(new ChangePosition(0, 1)); return true; });
        mapControl.put(KeyCode.LEFT, (s) -> { faireJouerSouris(new ChangePosition(-1, 0)); return true; });
        mapControl.put(KeyCode.RIGHT, (s) -> { faireJouerSouris(new ChangePosition(1, 0)); return true; });
        mapControl.put(KeyCode.ESCAPE, (s) -> { togglePlayPause(); return true; });
        
        mainPane.setMinSize((int) LevelVm.params.get("HORIZONTAL_MAX") * (double) LevelVm.params.get("IMAGE_SIZE"),
                (int) LevelVm.params.get("VERTICAL_MAX") * (double) LevelVm.params.get("IMAGE_SIZE"));
        mainPane.setMaxSize((int) LevelVm.params.get("HORIZONTAL_MAX") * (double) LevelVm.params.get("IMAGE_SIZE"),
                (int) LevelVm.params.get("VERTICAL_MAX") * (double) LevelVm.params.get("IMAGE_SIZE"));
        
        mainPane.getChildren().addAll(lm.getAllEntites());
        
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), ex -> test()));
        timeline.setCycleCount(-1);
        
        window.setMaxSize(mainPane.getMaxWidth(), mainPane.getMaxHeight() + topPane.getMaxHeight());
        window.setOnKeyPressed((event) -> {
            Predicate p = mapControl.get(event.getCode());
            if(p != null && !isPause)
                p.test(null);
        });
        window.addEventFilter(KeyEvent.KEY_PRESSED, k -> {
            if (k.getCode() == KeyCode.SPACE){
                k.consume();
            }
        });
        
        menu.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ESCAPE && isPause)
                buttonActionQuit(null);
            if(event.getCode() == KeyCode.ENTER && isPause)
                buttonActionTogglePlayPause( null);
        });
        
        compteur.textProperty().bind(lm.getClockVm().bindMinProperty().concat(":").concat(lm.getClockVm().bindSecProperty()));
    }    
    
}
