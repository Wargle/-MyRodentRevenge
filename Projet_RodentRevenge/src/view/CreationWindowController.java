/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Block;
import model.Border;
import model.Chat;
import model.Level;
import model.Souris;
import view_model.JeuVm;
import view_model.LevelVm;

/**
 * FXML Controller class
 *
 * @author Eleme
 */
public class CreationWindowController implements Initializable {
    private JeuVm newJeu;
    private LevelVm newLevel;
    
    @FunctionalInterface
    public interface ThreeParamsFunction<L, X, Y> {
        public void apply(L l, X x, Y y);
    }
    private List<ThreeParamsFunction<Level, Integer, Integer>> whatEntite;
    
    @FXML
    private GridPane window, menu, controlPane;
    
    @FXML
    private Pane mainPane;
    
    @FXML
    private TextField xNum, yNum, name;
    
    @FXML
    private ListView<ImageView> listEntite;
    
    private TranslateTransition menuTranslation;
    private boolean isPause = true;
    
    @FXML
    private void buttonActionBegin(ActionEvent event) {        
       if(isPause) {
            menuTranslation.setRate(1);
            menuTranslation.play();
        }
        else {
            menuTranslation.setRate(-1);
            menuTranslation.play();
        }
        isPause = !isPause;
    }
    
    @FXML
    private void buttonActionBackMenu(ActionEvent event) {   
        try {            
            Stage stage = new Stage();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml")));
            stage.setScene(scene);
            stage.show();

            Stage me = (Stage) menu.getScene().getWindow();
            me.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
    @FXML
    private void buttonActionSave(ActionEvent event) {        
        String nom = name.getText();
        if(nom.equals("defaut"))
            return;
        if(newLevel.getModel().getSouris() == null)
            return;
        newJeu.getModel().save(System.getProperty("user.dir").replace("\\dist", "") + "/file/levels/" + nom + ".txt");
        buttonActionBackMenu(event);
    }
    
    @FXML
    private void buttonActionQuit(ActionEvent event) {        
        Stage current = (Stage) window.getScene().getWindow();
        current.close();
    }
    
    private void resizeLevel() {
        try {
            newLevel.getModel().params.replace("HORIZONTAL_MAX", Integer.parseInt(xNum.getText()));
            newLevel.getModel().params.replace("VERTICAL_MAX", Integer.parseInt(yNum.getText()));

            mainPane.setMinSize((int) LevelVm.params.get("HORIZONTAL_MAX") * (int) LevelVm.params.get("IMAGE_SIZE"),
                    (int) LevelVm.params.get("VERTICAL_MAX") * (int) LevelVm.params.get("IMAGE_SIZE"));
            mainPane.setMaxSize((int) LevelVm.params.get("HORIZONTAL_MAX") * (int) LevelVm.params.get("IMAGE_SIZE"),
                    (int) LevelVm.params.get("VERTICAL_MAX") * (int) LevelVm.params.get("IMAGE_SIZE"));
            
            Stage current = (Stage) window.getScene().getWindow();
            current.setWidth(mainPane.getMinWidth() + controlPane.getMinWidth() + 18);
            current.setHeight(mainPane.getMinHeight() + 47);
        }
        catch (Exception e) {}
    }
    
    private void placeEntite(int index, int x, int y) {
        System.out.println("left");
        whatEntite.get(index + 1).apply(newLevel.getModel(), x, y);
        newLevel.setAllEntiteVm();
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(newLevel.getAllEntites());
    }
    private void removeEntite(int x, int y) {
        System.out.println("right");
        newLevel.getModel().enlever(x, y);
        newLevel.setAllEntiteVm();
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(newLevel.getAllEntites());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newJeu = new JeuVm();
        newLevel = newJeu.getCurrent();
        
        whatEntite = new ArrayList<>();
        whatEntite.add((l, x, y) -> { });
        whatEntite.add((l, x, y) -> { l.ajouter(new Souris(l.getGest(), x, y)); });
        whatEntite.add((l, x, y) -> { l.ajouter(new Chat(l.getGest(), x, y)); });
        whatEntite.add((l, x, y) -> { l.ajouter(new Block(l.getGest(), x, y)); });
        whatEntite.add((l, x, y) -> { l.ajouter(new Border(l.getGest(), x, y)); });
        
        listEntite.getItems().add(new ImageView(new Image("/resources/textures/player.png")));
        listEntite.getItems().add(new ImageView(new Image("/resources/textures/enemyNormal.png")));
        listEntite.getItems().add(new ImageView(new Image("/resources/textures/block.png")));
        listEntite.getItems().add(new ImageView(new Image("/resources/textures/border.png")));
        
        menuTranslation = new TranslateTransition(Duration.millis(500), menu);
        menuTranslation.setFromX(0);
        menuTranslation.setToX(-200);
        
        window.addEventFilter(KeyEvent.KEY_PRESSED, k -> {
            if (k.getCode() == KeyCode.SPACE){
                k.consume();
            }
        });
        
        mainPane.setOnMouseClicked((event) -> {
            int index = listEntite.getSelectionModel().getSelectedIndex();
            if(index < 0 || index > 3)
                return;
            if(event.getButton() == MouseButton.PRIMARY && !isPause)
                placeEntite(index, (int) event.getX() / 32, (int) event.getY() / 32);
            if(event.getButton() == MouseButton.SECONDARY && !isPause)
                removeEntite((int) event.getX() / 32, (int) event.getY() / 32);
        });
        
        listEntite.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ESCAPE && isPause)
                buttonActionQuit(null);
            if(event.getCode() == KeyCode.ESCAPE && !isPause)
                buttonActionBegin(null);
            if(event.getCode() == KeyCode.ENTER && isPause)
                buttonActionBegin(null);
        });
        
        xNum.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                resizeLevel();
                menu.requestFocus();
            }
        });
        yNum.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                resizeLevel();
                menu.requestFocus();
            }
        });
        resizeLevel();
    }
}
