package com.uninsubria.clientCV.centrivaccinali.controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class LogoutCittadinoController extends Controller {

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeScene("Cerca.fxml", event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeScene("RegistraCittadino.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        changeScene("Login.fxml", event);
    }

}
