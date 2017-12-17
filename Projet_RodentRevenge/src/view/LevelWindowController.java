/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Position;
import view_model.*;

/**
 *
 * @author Alexis Arnould
 */
public class LevelWindowController implements Initializable {
    @FunctionalInterface
    public interface NoParamFunction<NULL> {
        public void apply();
    }
    
    public JeuVm jeu = MainWindowController.jeuVm;
    private LevelVm lm;
    
    private boolean isPause = true;
    private TranslateTransition menuTranslation;
    
    Map<KeyCode, NoParamFunction> mapControl = new HashMap<>();
    
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
    
    Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(1000),
        ex -> time1sec()));
    
    void time05sec() {
        //System.out.println("0.5 sec");
        lm.faireJouerChat();
    }
    void time1sec() {
        time05sec();
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
    
    private void faireJouerSouris(Position c) {
        lm.faireJouerSouris(c);
    }
    
    @FXML
    private void buttonActionTogglePlayPause(ActionEvent event) {        
        togglePlayPause();
    }
    
    @FXML
    private void buttonActionBackMenu(ActionEvent event) {   
        try {            
            Stage stage = new Stage();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml")));
            stage.setScene(scene);
            stage.show();

            Stage me = (Stage) mainPane.getScene().getWindow();
            me.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
    @FXML
    private void buttonActionSave(ActionEvent event) {        
        jeu.getModel().save();
        buttonActionBackMenu(event);
    }
    
    @FXML
    private void buttonActionQuit(ActionEvent event) {        
        Stage current = (Stage) window.getScene().getWindow();
        current.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lm = jeu.getCurrent();
        
        menuTranslation = new TranslateTransition(Duration.millis(500), menu);
        menuTranslation.setFromX(0);
        menuTranslation.setToX(-200);
    
        mapControl.put(KeyCode.UP, () -> { faireJouerSouris(new Position(0, -1)); });
        mapControl.put(KeyCode.DOWN, () -> { faireJouerSouris(new Position(0, 1)); });
        mapControl.put(KeyCode.LEFT, () -> { faireJouerSouris(new Position(-1, 0)); });
        mapControl.put(KeyCode.RIGHT, () -> { faireJouerSouris(new Position(1, 0)); });
        mapControl.put(KeyCode.ESCAPE, () -> { togglePlayPause(); });
        
        mainPane.setMinSize((int) LevelVm.params.get("HORIZONTAL_MAX") * (int) LevelVm.params.get("IMAGE_SIZE"),
                (int) LevelVm.params.get("VERTICAL_MAX") * (int) LevelVm.params.get("IMAGE_SIZE"));
        mainPane.setMaxSize((int) LevelVm.params.get("HORIZONTAL_MAX") * (int) LevelVm.params.get("IMAGE_SIZE"),
                (int) LevelVm.params.get("VERTICAL_MAX") * (int) LevelVm.params.get("IMAGE_SIZE"));
        
        mainPane.getChildren().addAll(lm.getAllEntites());
        
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), ex -> time05sec()));
        timeline.setCycleCount(-1);
        
        window.setMinSize(mainPane.getMaxWidth(), mainPane.getMaxHeight() + topPane.getMaxHeight());
            
        window.setOnKeyPressed((event) -> {
            NoParamFunction p = mapControl.get(event.getCode());
            if(p != null && !isPause)
                p.apply();
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
                buttonActionTogglePlayPause(null);
        });
        
        compteur.textProperty().bind(lm.getClockVm().bindMinProperty().concat(":").concat(lm.getClockVm().bindSecProperty()));
    }    
    
}
