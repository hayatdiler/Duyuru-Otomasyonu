package com.example.announcement_procedures_automation_projectoop.CustomCells;

import com.example.announcement_procedures_automation_projectoop.Controllers.PAlistcontroller;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import java.util.ArrayList;

public class CustomListCellPerson extends ListCell<String> {
    private final VBox cellContent;
    private final Text mainText;
    private final ListView<String> subList;

    private boolean isSubListVisible = false;

    public CustomListCellPerson() {
        mainText = new Text();
        subList = new ListView<>();
        subList.setMaxHeight(80);
        cellContent = new VBox(5, mainText);
        // kullanici uzerine tiklandiginda ona atanan duyurulari goruntuleyebilmek icin bir liste(customlist) goruntulenir
        setOnMouseClicked(event -> {
            String person = getItem();
            if(person != null && person.startsWith("Person:")){
                person=person.substring(7);
            }
            if (isSubListVisible) {
                cellContent.getChildren().remove(subList);
            } else {
                subList.getItems().clear();
                ArrayList<String> announcements1 = (ArrayList<String>) PAlistcontroller.personalAnnouncements.getOrDefault(person, new ArrayList<>());
                subList.getItems().addAll(FXCollections.observableArrayList(announcements1));
                if (!subList.getItems().isEmpty()) {
                    cellContent.getChildren().add(subList);
                }
            }
            isSubListVisible = !isSubListVisible;
        });

        setGraphic(cellContent);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            mainText.setText(item);
            setGraphic(cellContent);
        }
    }
}
