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
import javafx.scene.control.ButtonType;
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
            personalAnnouncements.computeIfAbsent(pa.getPerson(), k -> new ArrayList<>()).add(pa.getMessage());// eger metod bulamazsa ikinci kisimi calistirir sonraki denemelerde bulacagi icin dogrudan getCompany() dondurulur


            if (!announcments2.contains("Person:" + pa.getPerson())) {
                announcments2.add("Person:" + pa.getPerson());
            }
            /*
            eger listede yok ise hem kullanici hem mesaj eklenir
            listede kullanici var ise mesaj kutusuna(customlist) eklenir
             */

            palist.getItems().clear();
            palist.getItems().addAll(announcments2);

            textField.clear();
            textField1.clear();

            DataBasePerson.saveData(personalAnnouncements);
        }
    }

    //eleman silme
    @FXML
    public void removePa(){
        String selectedElement=palist.getSelectionModel().getSelectedItem();

        if(selectedElement!=null) {
            Alert confirmationalert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationalert.setTitle("Delete personal announcement");
            confirmationalert.setHeaderText("Are you sure");
            confirmationalert.setContentText("Do you want to delete");

            Optional<ButtonType> answer = confirmationalert.showAndWait();

            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                String person = selectedElement.replace("Person:", "").trim();// elemanin icerigini bos bir string ile degistirmek silme ile ayni islevi gorur

                if (personalAnnouncements.containsKey(person)) {
                    personalAnnouncements.remove(person);
                    announcments2.remove(selectedElement);
                    palist.getItems().clear();
                    palist.getItems().addAll(announcments2);

                    DataBasePerson.saveData(personalAnnouncements);
                }
            }
            else return;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an element");
            alert.showAndWait();
        }
    }


    // geri donme butonu
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

    // sayfa gecisinde ilk calistirilan method
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
