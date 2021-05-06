package com.uninsubria.centrivaccinali.controller;

import com.uninsubria.centrivaccinali.CentriVaccinali;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void switchToHomeCittadinoScene (ActionEvent event) throws IOException {
        changeScene("HomeCittadino.fxml", event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException{
        changeScene("RegistraCittadino.fxml", event);
    }

    public void switchToHomeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new
                FXMLLoader(CentriVaccinali.class.getClassLoader().getResource("com/uninsubria/layout/HomeCittadino.fxml"));

        //scene of HomeCittadino/HomeOperatore, mancano i controlli per sapere se è operatore o cittadino
        Parent root = loader.load();

        //instance of HomeCittadinoController controller/HomeOperatoreController
        HomeCittadinoController controller = loader.getController();

        //calling method of HomeCittadinoController/HomeOperatoreController
        controller.setFields(usernameTextField.getText());

        //change scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void reset() {
        usernameTextField.setText(null);
        passwordField.setText(null);
    }
}
