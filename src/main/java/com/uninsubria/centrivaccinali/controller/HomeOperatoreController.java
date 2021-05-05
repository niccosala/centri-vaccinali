package com.uninsubria.centrivaccinali.controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class HomeOperatoreController extends Controller {

    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
        changeScene("RegistraCentro.fxml", event);
    }

    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeScene("RegistraVaccinato.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }
}
