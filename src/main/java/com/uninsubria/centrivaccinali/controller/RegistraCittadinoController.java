package com.uninsubria.centrivaccinali.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistraCittadinoController extends Controller {

    @FXML
    private TextField fieldNome, fieldCognome, fieldCodiceFiscale, fieldEmail, fieldID;
    @FXML
    private PasswordField fieldPassword;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeScene("Cerca.fxml", event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeScene("RegistraCittadino.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }

    public void reset() {
        fieldNome.setText(null);
        fieldCognome.setText(null);
        fieldCodiceFiscale.setText(null);
        fieldEmail.setText(null);
        fieldID.setText(null);
        fieldPassword.setText(null);
    }

}
