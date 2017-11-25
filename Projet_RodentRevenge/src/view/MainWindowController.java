/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MyRegex;
import view_model.FileLevelVm;
import view_model.JeuVm;
import view_model.ListFileLevel;

/**
 *
 * @author Eleme
 */
public class MainWindowController implements Initializable {
    public static JeuVm jeuVm;
    
    @FXML
    private ListView<FileLevelVm> listLevels;
    
    private ListFileLevel lesLevels = new ListFileLevel();
    
    private void startLevel(FileLevelVm levelVm) {
        try {
            jeuVm = new JeuVm(levelVm.getModel().getPath());
            
            Stage stage = new Stage();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/LevelWindow.fxml")));
            stage.setScene(scene);
            stage.show();

            Stage main = (Stage) listLevels.getScene().getWindow();
            main.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
    @FXML
    private void buttonActionStartLevel(ActionEvent event) {    
        FileLevelVm l = (FileLevelVm) listLevels.getSelectionModel().getSelectedItem();
        if(l != null) {
            startLevel(l);
        }       
    }
    
    @FXML
    private void buttonActionContinue(ActionEvent event) {
        
    }
    
    @FXML
    private void buttonActionQuitApplication(ActionEvent event) {
        Platform.exit();
    }
    
    private Map<String, String> getSpecificsLevels(){
        Map<String, String> specf = new HashMap<>();
        try {            
            String path = System.getProperty("user.dir").replace("\\dist", "") + "/file/specificsLevels.txt";
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(MyRegex.isMatch(".:(done|current)", line)){
                    String[] vals = line.split(":");
                    specf.put(vals[0], vals[1]);
                }
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return specf;
    }
    
    private void generateListLevel(){
        Map<String, String> completed = getSpecificsLevels();
        try {
            String path = System.getProperty("user.dir").replace("\\dist", "") + "/file/levels";
            File rep = new File(path);
            File[] fichiersJava = rep.listFiles();
            for(File f : fichiersJava){
                String name = f.getName().replaceFirst("[.][^.]+$", "");
                if(!completed.containsKey(name))
                    lesLevels.ajouterFileLevel(new FileLevelVm(name, f.getPath()));
                else {
                    switch(completed.get(name)) {
                        case "done" : lesLevels.ajouterFileLevel(new FileLevelVm(name, f.getPath(), Color.GREEN)); break;
                        case "current" : lesLevels.ajouterFileLevel(new FileLevelVm(name, f.getPath(), Color.PURPLE)); break;
                        default: lesLevels.ajouterFileLevel(new FileLevelVm(name, f.getPath()));
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listLevels.itemsProperty().bind(lesLevels.obsLevelsProperty());
        listLevels.setCellFactory((param) -> {
            return new ListCell<FileLevelVm>(){
                @Override
                protected void updateItem(FileLevelVm item, boolean empty) {
                    super.updateItem(item, empty);
                    if(!empty){
                        textProperty().bind(item.bindNomProperty()/*.concat("\n").concat(item.bindPathProperty())*/);
                        textFillProperty().bind(item.bindColorProperty());
                    }else{
                        textProperty().unbind();
                        textProperty().set("");
                    }
                }
            };
        });
        listLevels.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                FileLevelVm l = (FileLevelVm) listLevels.getSelectionModel().getSelectedItem();
                if(l != null) {
                    startLevel(l);
                }
            }
        });
        
        generateListLevel();
    }
}
