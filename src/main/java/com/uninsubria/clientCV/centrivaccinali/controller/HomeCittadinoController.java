package com.uninsubria.clientCV.centrivaccinali.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class HomeCittadinoController extends Controller{

    @FXML
    private Text welcomeTextField;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeScene("Cerca.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeScene("RegistraCittadino.fxml", event);
    }

    public void setFields(String welcomeText) {
        welcomeTextField.setText("Ciao " + welcomeText);
    }
}
