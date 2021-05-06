package com.uninsubria.clientCV.centrivaccinali.controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class LogoutOperatoreController extends Controller {

    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
        changeScene("RegistraCentro.fxml", event);
    }

    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeScene("RegistraVaccinato.fxml", event);
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        changeScene("Login.fxml", event);
    }

}
