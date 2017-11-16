/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import view_model.FileLevelVm;
import view_model.ListFileLevel;

/**
 *
 * @author Eleme
 */
public class MainWindowController implements Initializable {
    
    @FXML
    private ListView<FileLevelVm> listLevels;
    
    private ListFileLevel lesLevels = new ListFileLevel();
    
    @FXML
    private void startLevel(ActionEvent event) throws Exception {
        //TODO
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/LevelWindow.fxml")));
        scene.getStylesheets().add("/resources/style/style.css");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void quitApplication(ActionEvent event) {
        Platform.exit();
    }
    
    private List<String> getCompletedLevels(){
        List<String> cl = new ArrayList<String>();
        try {
            String path = System.getProperty("user.dir").replace("\\dist", "") + "/file/completedLevels.txt";
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                cl.add(sc.nextLine());
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return cl;
    }
    
    private void generateListLevel(){
        List<String> completed = getCompletedLevels();
        try {
            String path = System.getProperty("user.dir").replace("\\dist", "") + "/file/levels";
            File rep = new File(path);
            File[] fichiersJava = rep.listFiles();
            for(File f : fichiersJava){
                String name = f.getName().replaceFirst("[.][^.]+$", "");
                if(!completed.contains(name))
                    lesLevels.ajouterFileLevel(new FileLevelVm(name, f.getPath()));
                else
                    lesLevels.ajouterFileLevel(new FileLevelVm(name, f.getPath(), Color.GREEN));
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
                        textProperty().bind(item.bindNomProperty().concat("\n").concat(item.bindPathProperty()));
                        textFillProperty().bind(item.bindColorProperty());
                    }else{
                        textProperty().unbind();
                        textProperty().set("");
                    }
                }
            };
        });
        
        generateListLevel();
    }
}
