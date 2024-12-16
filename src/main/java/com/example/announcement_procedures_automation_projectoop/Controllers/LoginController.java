package com.example.announcement_procedures_automation_projectoop.Controllers;

import com.example.announcement_procedures_automation_projectoop.DataBases.DataBaseUser;
import com.example.announcement_procedures_automation_projectoop.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField textField;  // Kullanıcı adı alanı
    @FXML
    private PasswordField passwordField;  // Şifre alanı
    @FXML
    private Stage stage;

    private Map<String, String> users = DataBaseUser.loadUserData();

    public void SwitchToMainScene(ActionEvent event) throws IOException {
        String username = textField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password cannot be empty!");
            return;
        }
        if(username.equals("Admin") && password.equals("1234")){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UIProject.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (users.containsKey(username) && users.get(username).equals(password)) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserScene.fxml"));

            fxmlLoader.setControllerFactory(t -> new UserSceneController(username));

            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            showAlert("Error", "Invalid username or password!");
        }
    }

    public void SwitchToRegisterMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegisterScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
