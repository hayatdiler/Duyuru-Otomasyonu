package com.example.announcement_procedures_automation_projectoop.Controllers;


import com.example.announcement_procedures_automation_projectoop.Announcements.Advertisements;
import com.example.announcement_procedures_automation_projectoop.CustomCells.CustomListCellAdvertisement;
import com.example.announcement_procedures_automation_projectoop.DataBases.DataBaseAdvertisement;
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

public class Alistcontroller implements WithCustomCell, Initializable {

    @FXML
    private ListView<String> alist;
    @FXML
    private TextField textField,textField1;
    @FXML
    private Stage stage;

    public static ArrayList<String> announcments3 = new ArrayList<>();
    public static Map<String, List<String>> Advertisementannounce = new HashMap<>();


    // reklam ekleme
    @FXML
    private void addAdvertisement() {
        String comp = textField.getText().trim();
        String advertisement = textField1.getText().trim();
        Advertisements a=new Advertisements("Advertisement",advertisement,comp);

        if (a.getMessage().isEmpty() || a.getCompany().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("cannot be empty!");
            alert.showAndWait();// textfieldlar bos birakilamaz
        } else {
            Advertisementannounce.computeIfAbsent(a.getCompany(), k -> new ArrayList<>()).add(a.getMessage());// eger metod bulamazsa ikinci kisimi calistirir sonraki denemelerde bulacagi icin dogrudan getCompany() dondurulur

            if(!announcments3.contains("Company: "+a.getCompany())){
                announcments3.add("Company: "+a.getCompany());
            }
            /*
            eger listede yok ise hem kullanici hem mesaj eklenir
            listede kullanici var ise mesaj kutusuna(customlist) eklenir
             */
            alist.getItems().clear();
            alist.getItems().addAll(announcments3);

            textField.clear();;
            textField1.clear();

            DataBaseAdvertisement.saveData(Advertisementannounce);
        }
    }

    // silme islemi
    @FXML
    public void removeAdvertisement(){
        String selectedElement=alist.getSelectionModel().getSelectedItem();




            if (selectedElement != null) {
                Alert confirmationalert=new Alert(Alert.AlertType.CONFIRMATION);
                confirmationalert.setTitle("Delete advertisement");
                confirmationalert.setHeaderText("Are you sure?");
                confirmationalert.setContentText("Do you want to delete");

                Optional<ButtonType> answer = confirmationalert.showAndWait();

                if(answer.isPresent() && answer.get()==ButtonType.OK) {

                    String company = selectedElement.replace("Company: ", "").trim();// elemanin icerigini bos bir string ile degistirmek silme ile ayni islevi gorur

                    if (Advertisementannounce.containsKey(company)) {
                        Advertisementannounce.remove(company);

                        announcments3.remove(selectedElement);
                        alist.getItems().clear();
                        alist.getItems().addAll(announcments3);

                        DataBaseAdvertisement.saveData(Advertisementannounce);
                    }
                }
                else return;
              // secilmezse uyari gonderilir
            } else {
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
        System.out.println("Advertisement class have custom cell");
    }

    // ilk calistirilan method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Advertisementannounce= DataBaseAdvertisement.loadData();

        for(String company:Advertisementannounce.keySet()){
            if(!announcments3.contains("Company: "+company)){
                announcments3.add("Company: "+company);
            }
        }
        // listeyi guncelleme
        alist.getItems().addAll(announcments3);
        alist.setCellFactory(listview->new CustomListCellAdvertisement());
    }
}
