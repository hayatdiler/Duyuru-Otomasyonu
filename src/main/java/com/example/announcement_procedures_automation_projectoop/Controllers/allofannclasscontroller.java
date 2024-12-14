package com.example.announcement_procedures_automation_projectoop.Controllers;

import com.example.announcement_procedures_automation_projectoop.Announcements.Proclamation;
import com.example.announcement_procedures_automation_projectoop.CustomCells.CustomListCellAdvertisement;
import com.example.announcement_procedures_automation_projectoop.CustomCells.CustomListCellPerson;
import com.example.announcement_procedures_automation_projectoop.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class allofannclasscontroller {
    @FXML
    private Stage stage;
    @FXML
    private ListView<String> listView1;
    @FXML
    private ListView<String> listView2;
    @FXML
    private ListView<String> listView3;

    @FXML
    public void initialize() {
        List<String> personalAnnouncements = PAlistcontroller.announcments2;
        List<String> Advertisements = Alistcontroller.announcments3;
        List<String> Proclamations = pListcontroller.announcments1;

        listView1.getItems().addAll(Advertisements);
        listView2.getItems().addAll(Proclamations);
        listView3.getItems().addAll(personalAnnouncements);

        listView1.setCellFactory(listview-> new CustomListCellAdvertisement());
        listView3.setCellFactory(listView -> new CustomListCellPerson());
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
