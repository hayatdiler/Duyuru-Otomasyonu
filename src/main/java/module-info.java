module com.example.announcement_procedures_automation_projectoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires com.fasterxml.jackson.annotation;


    opens com.example.announcement_procedures_automation_projectoop.Announcements to com.google.gson;
    opens com.example.announcement_procedures_automation_projectoop to javafx.fxml;
    exports com.example.announcement_procedures_automation_projectoop;
    exports com.example.announcement_procedures_automation_projectoop.Announcements;
    exports com.example.announcement_procedures_automation_projectoop.Controllers;
    opens com.example.announcement_procedures_automation_projectoop.Controllers to javafx.fxml;
    exports com.example.announcement_procedures_automation_projectoop.CustomCells;
    opens com.example.announcement_procedures_automation_projectoop.CustomCells to javafx.fxml;
    exports com.example.announcement_procedures_automation_projectoop.DataBases;
    opens com.example.announcement_procedures_automation_projectoop.DataBases to javafx.fxml;
}