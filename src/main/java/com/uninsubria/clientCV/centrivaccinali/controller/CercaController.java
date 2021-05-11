package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.entity.Tipologia;
import com.uninsubria.clientCV.condivisa.Util;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CercaController extends Controller implements Initializable{

    private UtenteRegistrato utente;
    private Util util = new Util();

    @FXML
    private ComboBox<String> tipologiaComboBox;
    @FXML
    private RadioButton filtraComuneRadio, filtraNomeRadio;
    @FXML
    private TextField nomeTextField, comuneTextField;
    @FXML
    private Text welcomeTextField;
    @FXML
    private Button btnRegistrati, btnLogout;
    @FXML
    private ListView<String> centriListView;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Cerca.fxml", utente, event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCittadino.fxml", utente, event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        if(utente == null)
            changeSceneAndSetValues("Login.fxml", null, event);
        else
            changeSceneAndSetValues("LogoutCittadino.fxml", utente, event);
    }

    public void switchToVisualizzaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Visualizza.fxml", utente, event);
    }

    public void mostraCentriVaccinali() throws IOException, SQLException {

        Proxy proxy;
        ArrayList<String> centrivaccinali;
        ObservableList<String> data;

        if(filtraNomeRadio.isSelected()) {
            String nome = util.lowercaseNotFirst(nomeTextField.getText().trim());

            if(nome.isBlank()) {
                showDialog("Campi mancanti", "Inserire il nome del centro per effettuare la ricerca");
                return;
            }

            //ricerca per nome
            proxy = new Proxy();
            String query = "SELECT * FROM centrivaccinali WHERE nome LIKE '%" + nome + "%'";
            centrivaccinali = proxy.filter(query);

            if(centrivaccinali.size() == 0)
                showDialog("Nessun centro trovato", "Non esistono centri vaccinali con questo nome");

            data = FXCollections.observableArrayList();
            data.addAll(centrivaccinali);

            centriListView.setItems(data);

        }
        else if(filtraComuneRadio.isSelected()) {
            String comune = util.lowercaseNotFirst(comuneTextField.getText().trim());
            String tipologia = tipologiaComboBox.getValue();

            if(comune.isBlank() || tipologia == null) {
                showDialog("Campi mancanti", "Inserire il comune e la tipologia per effettuare la ricerca");
                return;
            }

            //ricerca per comune e tipologia
            proxy = new Proxy();
            String query = "SELECT * FROM centrivaccinali WHERE comune='"+ comune +"' AND tipologia='"+ tipologia +"'";
            centrivaccinali = proxy.filter(query);

            if(centrivaccinali.size() == 0)
                showDialog("Nessun centro trovato", "Non esistono centri vaccinali corrispondenti ai criteri di ricerca");

            data = FXCollections.observableArrayList();
            data.addAll(centrivaccinali);

            centriListView.setItems(data);
        }
    }

    public void enableFiltering () {
        nomeTextField.setDisable(filtraComuneRadio.isSelected());
        comuneTextField.setDisable(filtraNomeRadio.isSelected());
        tipologiaComboBox.setDisable(filtraNomeRadio.isSelected());
    }

    public void reset() {
        nomeTextField.clear();
        comuneTextField.clear();
        tipologiaComboBox.setValue(null);
        centriListView.setItems(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] tipologia = {Tipologia.OSPEDALIERO.toString(), Tipologia.HUB.toString(), Tipologia.AZIENDALE.toString()};
        tipologiaComboBox.getItems().addAll(tipologia);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        if (utente == null) {
            welcomeTextField.setText("Accesso come ospite");
            btnRegistrati.setDisable(false);
            btnLogout.setText("Accedi");
        }
        else {
            welcomeTextField.setText("Ciao, " + utente.getUsername());
            btnRegistrati.setDisable(true);
        }
    }
}
