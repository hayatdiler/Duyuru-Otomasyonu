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
import javafx.scene.control.ButtonType;
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


    // ekleme islemi
    @FXML
    private void addProclamation() {
        String proclamation = textField.getText().trim();
        Proclamation p=new Proclamation("Proclamation",proclamation);
        if (p.getMessage().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Proclamation cannot be empty!");// bos olursa her hangi bir sey eklenemez
            alert.showAndWait();
        } else {

            announcments1.add("proclamation:"+p.getMessage());
            plist.getItems().clear();
            plist.getItems().addAll(announcments1);

            textField.clear();

            DataBaseProclamation.saveData(announcments1);
        }
    }

    // silme islemi
    @FXML
    private void removeProclamation(){
        String selectedElement = plist.getSelectionModel().getSelectedItem();

        if(selectedElement!=null){
            Alert confirmationalert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationalert.setTitle("Delete proclamation");
            confirmationalert.setHeaderText("Are you sure?");
            confirmationalert.setContentText("Do you want to delete");

            Optional<ButtonType> answer = confirmationalert.showAndWait();
            if(answer.isPresent() && answer.get()==ButtonType.OK) {
                if (announcments1.contains(selectedElement)) {
                    announcments1.remove(selectedElement);

                    plist.getItems().clear();
                    plist.getItems().addAll(announcments1);

                    DataBaseProclamation.saveData(announcments1);
                }
            }
            else return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select an element");
            alert.showAndWait();
        }
    }

    // geri donme butonu icin yazilan menthod
    @FXML
    public void switchToBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UIProject.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //ilk calisirilan method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        announcments1= (ArrayList<String>) DataBaseProclamation.loadData();
        plist.getItems().addAll(announcments1);
    }
}
