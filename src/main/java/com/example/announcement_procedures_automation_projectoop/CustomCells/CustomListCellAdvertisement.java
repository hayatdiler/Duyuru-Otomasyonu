package com.example.announcement_procedures_automation_projectoop.CustomCells;

import com.example.announcement_procedures_automation_projectoop.Controllers.Alistcontroller;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import java.util.ArrayList;

public class CustomListCellAdvertisement extends ListCell<String> {
    private final VBox cellContent;
    private final Text mainText;
    private final ListView<String> subList;

    private boolean isSubListVisible = false;

    public CustomListCellAdvertisement() {
        mainText = new Text();
        subList = new ListView<>();
        subList.setMaxHeight(80);
        cellContent = new VBox(5, mainText);

        setOnMouseClicked(event -> {
            String company = getItem();
            if(company != null && company.startsWith("Company: ")){
                company=company.substring(9);
            }
            if (isSubListVisible) {
                cellContent.getChildren().remove(subList);
            } else {
                subList.getItems().clear();
                ArrayList<String> announcements3 = (ArrayList<String>) Alistcontroller.Advertisementannounce.getOrDefault(company, new ArrayList<>());
                subList.getItems().addAll(FXCollections.observableArrayList(announcements3));
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
