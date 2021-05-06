package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.entity.Vaccino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistraVaccinatoController extends Controller implements Initializable {

    @FXML
    private ComboBox<String> vaccinoComboBox;
    @FXML
    private TextField fieldNome, fieldCognome, fieldCodiceFiscale;
    @FXML
    private DatePicker fieldData;

    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
        changeScene("RegistraCentro.fxml", event);
    }

    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeScene("RegistraVaccinato.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }

    public void reset() {
        fieldNome.setText(null);
        fieldCognome.setText(null);
        fieldCodiceFiscale.setText(null);
        fieldData.setValue(null);
        vaccinoComboBox.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] vaccino = {Vaccino.ASTRAZENECA.toString(), Vaccino.JANDJ.toString(),
                Vaccino.MODERNA.toString(), Vaccino.PFIZER.toString()};

        vaccinoComboBox.getItems().addAll(vaccino);
    }
}
