package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.entity.Tipologia;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CercaController extends Controller implements Initializable{

    @FXML
    private ComboBox<String> tipologiaComboBox;
    @FXML
    private RadioButton filtraComuneRadio, filtraNomeRadio;
    @FXML
    private TextField nomeTextField, comuneTextField;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeScene("Cerca.fxml", event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeScene("RegistraCittadino.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
       changeScene("LogoutCittadino.fxml", event);
    }

    public void switchToVisualizzaScene(ActionEvent event) throws IOException {
        changeScene("Visualizza.fxml", event);
    }

    public void mostraCentriVaccinali(ActionEvent event) throws IOException, SQLException {

        Proxy proxy = new Proxy();

        if(filtraNomeRadio.isSelected()) {
            String nome = nomeTextField.getText();
            String query = "SELECT * FROM centrivaccinali WHERE nome ='" + nome + "'";
            //ricerca per nome
            proxy.filter(query);
            //update listview
        }
        else if(filtraComuneRadio.isSelected()) {
            String comune = comuneTextField.getText();
            String tipologia = tipologiaComboBox.getValue();
            //ricerca per comune e tipologia
            String query = "SELECT * FROM centrivaccinali WHERE indirizzo='"+ comune +"' AND tipologia='"+ tipologia +"'";
            proxy.filter(query);
            //update listview
        }
    }

    public void enableFiltering () {
        nomeTextField.setDisable(filtraComuneRadio.isSelected());
        comuneTextField.setDisable(filtraNomeRadio.isSelected());
        tipologiaComboBox.setDisable(filtraNomeRadio.isSelected());
    }

    public void reset() {
        nomeTextField.setText(null);
        comuneTextField.setText(null);
        tipologiaComboBox.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] tipologia = {Tipologia.OSPEDALIERO.toString(), Tipologia.HUB.toString(), Tipologia.AZIENDALE.toString()};
        tipologiaComboBox.getItems().addAll(tipologia);
    }

}
