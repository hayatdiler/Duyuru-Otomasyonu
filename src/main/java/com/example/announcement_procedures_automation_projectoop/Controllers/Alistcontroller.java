package com.example.announcement_procedures_automation_projectoop.Controllers;


import com.example.announcement_procedures_automation_projectoop.Announcements.Advertisements;
import com.example.announcement_procedures_automation_projectoop.CustomCells.CustomListCellAdvertisement;
import com.example.announcement_procedures_automation_projectoop.DataBases.DataBaseAdvertisement;
import com.example.announcement_procedures_automation_projectoop.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alistcontroller {

    @FXML
    private ListView<String> alist;
    @FXML
    private TextField textField,textField1;
    @FXML
    private Stage stage;

    public static ArrayList<String> announcments3 = new ArrayList<>();
    public static Map<String, List<String>> Advertisementannounce = new HashMap<>();

    public void initialize() {

        Advertisementannounce= DataBaseAdvertisement.loadData();

        for(String company:Advertisementannounce.keySet()){
            if(!announcments3.contains("Company:"+company)){
                announcments3.add("Company:"+company);
            }
        }

        alist.getItems().addAll(announcments3);
        alist.setCellFactory(listview->new CustomListCellAdvertisement());
    }

    @FXML
    private void addAdvertisement() {
        String comp = textField.getText().trim();
        String advertisement = textField1.getText().trim();
        Advertisements a=new Advertisements("Advertisement",advertisement,comp);

        if (a.getMessage().isEmpty() || a.getCompany().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Proclamation cannot be empty!");
            alert.showAndWait();
        } else {
            Advertisementannounce.computeIfAbsent(a.getCompany(), k -> new ArrayList<>()).add(a.getMessage());

            if(!announcments3.contains("Company:"+a.getCompany())){
                announcments3.add("Company:"+a.getCompany());
            }
            alist.getItems().clear();
            alist.getItems().addAll(announcments3);

            textField.clear();;
            textField1.clear();

            DataBaseAdvertisement.saveData(Advertisementannounce);
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
}
