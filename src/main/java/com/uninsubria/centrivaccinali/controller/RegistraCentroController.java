package com.uninsubria.centrivaccinali.controller;

import com.uninsubria.centrivaccinali.entity.Tipologia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistraCentroController extends Controller implements Initializable {

    @FXML
    private ComboBox<String> tipologiaComboBox;
    @FXML
    private TextField fieldNome, fieldIndirizzo;

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutOperatore.fxml", event);
    }

    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
       changeScene("RegistraCentro.fxml", event);
    }

    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeScene("RegistraVaccinato.fxml", event);
    }

    public void reset(ActionEvent event) throws IOException {
        fieldNome.setText(null);
        fieldIndirizzo.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] tipologia = {Tipologia.AZIENDALE.toString(), Tipologia.HUB.toString(),
                Tipologia.OSPEDALIERO.toString()};
        tipologiaComboBox.getItems().addAll(tipologia);
    }
}
