package com.example.announcement_procedures_automation_projectoop.Controllers;

import com.example.announcement_procedures_automation_projectoop.CustomCells.CustomListCellAdvertisement;
import com.example.announcement_procedures_automation_projectoop.CustomCells.CustomListCellPerson;
import com.example.announcement_procedures_automation_projectoop.DataBases.DataBaseAdvertisement;
import com.example.announcement_procedures_automation_projectoop.DataBases.DataBasePerson;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserSceneController {

    @FXML
    private ListView<String> proclamationList;
    @FXML
    private ListView<String> advertisementList;
    @FXML
    private ListView<String> personalAnnouncementList;
    @FXML
    private Stage stage;

    private String username;

    public  UserSceneController(String username){
        this.username=username;
    }
    @FXML
    public void initialize() {
        Alistcontroller.Advertisementannounce= DataBaseAdvertisement.loadData();

        for(String company:Alistcontroller.Advertisementannounce.keySet()){
            if(!Alistcontroller.announcments3.contains("Company:"+company)){
                Alistcontroller.announcments3.add("Company:"+company);
            }
        }
        PAlistcontroller.personalAnnouncements= DataBasePerson.loadData();
        for(String name:PAlistcontroller.personalAnnouncements.keySet()){
            if(!PAlistcontroller.announcments2.contains("Person:"+name)){
                PAlistcontroller.announcments2.add("Person:"+name);
            }
        }
        ArrayList<String> personalAnnouncements = getPersonalAnnouncementsForUser(username);
        List<String> Advertisements = Alistcontroller.announcments3;
        List<String> Proclamations = pListcontroller.announcments1;

        advertisementList.getItems().addAll(Advertisements);
        proclamationList.getItems().addAll(Proclamations);
        personalAnnouncementList.getItems().addAll(personalAnnouncements);
        advertisementList.setCellFactory(listview-> new CustomListCellAdvertisement());
        personalAnnouncementList.setCellFactory(listView -> new CustomListCellPerson());

    }
    @FXML
    public void switchToBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginScene.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private ArrayList<String> getPersonalAnnouncementsForUser(String username) {
        ArrayList<String> filteredAnnouncements = new ArrayList<>();

        // DataBasePerson'dan veriyi yükle
        Object data = DataBasePerson.loadData();
        System.out.println("Loaded data: " + data);

        // Verinin Map türünde olup olmadığını kontrol et
        if (data instanceof Map) {
            Map<String, Object> dataMap = (Map<String, Object>) data;
            System.out.println("Data Map: " + dataMap);

            // Map'teki kullanıcı adını kontrol et
            if (dataMap.containsKey(username)) {
                Object announcementsObj = dataMap.get(username); // Kullanıcı adı ile duyuruları al
                System.out.println("Personal Announcements: " + announcementsObj);

                // Eğer duyurular bir listeyse
                if (announcementsObj instanceof List) {
                    List<String> allAnnouncements = (List<String>) announcementsObj;

                    // Her bir duyuruyu ekle
                    for (String announcement : allAnnouncements) {
                        System.out.println("Checking announcement: " + announcement);
                        filteredAnnouncements.add(announcement);  // Kullanıcıya ait duyuruları ekle
                    }
                }
            }
        }

        return filteredAnnouncements;
    }


}
