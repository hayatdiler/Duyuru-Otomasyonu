package com.example.announcement_procedures_automation_projectoop.Controllers;

import com.example.announcement_procedures_automation_projectoop.Announcements.PersonalAnnouncement;
import com.example.announcement_procedures_automation_projectoop.CustomCells.CustomListCellPerson;
import com.example.announcement_procedures_automation_projectoop.DataBases.DataBasePerson;
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

public class PAlistcontroller implements WithCustomCell, Initializable {

    @FXML
    private ListView<String> palist;
    @FXML
    private TextField textField,textField1;
    @FXML
    private Stage stage;

    public static ArrayList<String> announcments2 = new ArrayList<>();
    public static Map<String, List<String>> personalAnnouncements = new HashMap<>();

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

            DataBasePerson.saveData(personalAnnouncements);
        }
    }

    @FXML
    public void removePa(){
        String selectedElement=palist.getSelectionModel().getSelectedItem();

        if(selectedElement!=null){
            String person = selectedElement.replace("Person:","").trim();

            if(personalAnnouncements.containsKey(person)){
                personalAnnouncements.remove(person);

                announcments2.remove(selectedElement);
                palist.getItems().clear();
                palist.getItems().addAll(announcments2);

                DataBasePerson.saveData(personalAnnouncements);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an element");
            alert.showAndWait();
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
    public void messageCustomList() {
        System.out.println("Personal announcement class have custom cell");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        personalAnnouncements=DataBasePerson.loadData();

        for(String person:personalAnnouncements.keySet()){
            if(!announcments2.contains("Person:"+person)){
                announcments2.add("Person:"+person);
            }
        }

        palist.getItems().addAll(announcments2);
        palist.setCellFactory(listView -> new CustomListCellPerson());
    }
}
