package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.entity.Tipologia;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    public void registraCentro(ActionEvent event) throws IOException, SQLException {
        String nomeCentro = fieldNome.getText();
        String indirizzo = fieldIndirizzo.getText();
        String tipologia = tipologiaComboBox.getValue();

        String query=  "INSERT INTO centrivaccinali VALUES('"+nomeCentro+"','"+indirizzo+"','"+tipologia+"')";
        Proxy proxy = new Proxy();
        proxy.populateCentriVaccinali(query, nomeCentro);

        reset();
    }

    public void reset() {
        fieldNome.setText(null);
        fieldIndirizzo.setText(null);
        tipologiaComboBox.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] tipologia = {Tipologia.AZIENDALE.toString(), Tipologia.HUB.toString(),
                Tipologia.OSPEDALIERO.toString()};
        tipologiaComboBox.getItems().addAll(tipologia);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {

    }
}
