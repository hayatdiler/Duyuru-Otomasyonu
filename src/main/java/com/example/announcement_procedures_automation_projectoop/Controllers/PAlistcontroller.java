package com.example.announcement_procedures_automation_projectoop.Controllers;

import com.example.announcement_procedures_automation_projectoop.Announcements.PersonalAnnouncement;
import com.example.announcement_procedures_automation_projectoop.CustomCells.CustomListCellPerson;
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

public class PAlistcontroller {

    @FXML
    private ListView<String> palist;
    @FXML
    private TextField textField,textField1;
    @FXML
    private Stage stage;

    public static ArrayList<String> announcments2 = new ArrayList<>();
    public static Map<String, List<String>> personalAnnouncements = new HashMap<>();

    public void initialize() {
        palist.getItems().addAll(announcments2);
        palist.setCellFactory(listView -> new CustomListCellPerson());
    }

    @FXML
    private void addPa() {
        String name = textField.getText().trim();
        String personalannouncement = textField1.getText().trim();
        PersonalAnnouncement pa = new PersonalAnnouncement("Personal announcement", personalannouncement, name);

        if (pa.getMessage().isEmpty() || pa.getPerson().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Proclamation cannot be empty!");
            alert.showAndWait();
        } else {
            personalAnnouncements.computeIfAbsent(pa.getPerson(), k -> new ArrayList<>()).add(pa.getMessage());


            if (!announcments2.contains("Person:" + pa.getPerson())) {
                announcments2.add("Person:" + pa.getPerson());
            }

            palist.getItems().clear();
            palist.getItems().addAll(announcments2);

            textField.clear();
            textField1.clear();
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
