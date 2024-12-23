package com.example.announcement_procedures_automation_projectoop.Controllers;

import com.example.announcement_procedures_automation_projectoop.Announcements.Proclamation;
import com.example.announcement_procedures_automation_projectoop.DataBases.DataBaseProclamation;
import com.example.announcement_procedures_automation_projectoop.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class pListcontroller implements Initializable {

    @FXML
    private ListView<String> plist;
    @FXML
    private TextField textField;
    @FXML
    private Stage stage;

    public static ArrayList<String> announcments1 = new ArrayList<>();


    @FXML
    private void addProclamation() {
        String proclamation = textField.getText().trim();
        Proclamation p=new Proclamation("Proclamation",proclamation);
        if (p.getMessage().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Proclamation cannot be empty!");
            alert.showAndWait();
        } else {

            announcments1.add("proclamation:"+p.getMessage());
            plist.getItems().clear();
            plist.getItems().addAll(announcments1);

            textField.clear();

            DataBaseProclamation.saveData(announcments1);
        }
    }

    @FXML
    private void removeProclamation(){
        String selectedElement = plist.getSelectionModel().getSelectedItem();

        if(selectedElement!=null){
            if(announcments1.contains(selectedElement)){
                announcments1.remove(selectedElement);

                plist.getItems().clear();
                plist.getItems().addAll(announcments1);

                DataBaseProclamation.saveData(announcments1);
            }

        }
    }


    @FXML
    public void switchToBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UIProject.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        announcments1= (ArrayList<String>) DataBaseProclamation.loadData();
        plist.getItems().addAll(announcments1);
    }
}
