package com.uninsubria.centrivaccinali.controller;

import com.uninsubria.centrivaccinali.entity.Vaccino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistraVaccinatoController extends Controller implements Initializable {

    @FXML
    private ComboBox<String> vaccinoComboBox;

    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
        changeScene("RegistraCentro.fxml", event);
    }

    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeScene("RegistraVaccinato.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] vaccino = {Vaccino.ASTRAZENECA.toString(), Vaccino.JANDJ.toString(),
                Vaccino.MODERNA.toString(), Vaccino.PFIZER.toString()};

        vaccinoComboBox.getItems().addAll(vaccino);
    }
}
