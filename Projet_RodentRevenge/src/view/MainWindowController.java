/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * @author Alexis Arnould
 */
public class MainWindowController implements Initializable {
    public static JeuVm jeuVm;
    
    private FileLevelVm saved;
    
    @FXML
    private ListView<FileLevelVm> listLevels;
    
    @FXML
    private Button buttonStart, buttonCont;
    
    private ListFileLevel lesLevels = new ListFileLevel();
    
    private void startLevel(FileLevelVm levelVm) {
        try {
            jeuVm = new JeuVm(levelVm);
            
            Stage stage = new Stage();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/LevelWindow.fxml")));
            stage.setScene(scene);
            
            stage.setTitle("My Rodent's Revenge ~ Level " + levelVm.getModel().getNom());
            stage.show();

            Stage main = (Stage) listLevels.getScene().getWindow();
            main.close();
        }
        catch (IOException e) {
            System.out.println(e.getCause());
        }
        catch (InstantiationError ie) {
            System.out.println("Wrong file");
            levelVm.setBindColor(Color.RED);
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
        if(saved != null) {
            startLevel(saved);
        } 
    }
    
    @FXML
    private void buttonActionCreate(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/LevelCreationWindow.fxml")));
            stage.setScene(scene);

            stage.setTitle("My Rodent's Revenge ~ Creation");
            stage.show();

            Stage main = (Stage) listLevels.getScene().getWindow();
            main.close();
        }
        catch (IOException e) {
            System.out.println(e.getCause());
        }
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
                        case "current" : 
                            saved = new FileLevelVm(name, System.getProperty("user.dir").replace("\\dist", "") + "/file/savedLevel.txt", Color.PURPLE);
                            lesLevels.ajouterFileLevel(new FileLevelVm(name, f.getPath(), Color.PURPLE)); 
                            buttonCont.disableProperty().set(false); 
                            break;
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
        
        buttonStart.disableProperty().bind(Bindings.isEmpty(listLevels.getSelectionModel().getSelectedItems()));
        
        generateListLevel();
    }
}
