package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.entity.Qualificatore;
import com.uninsubria.clientCV.centrivaccinali.entity.Tipologia;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegistraCentroController extends Controller implements Initializable {

    @FXML
    private ComboBox<String> qualificatoreComboBox, tipologiaComboBox;
    @FXML
    private TextField fieldNome, fieldStrada, fieldCap,
            fieldCivico, fieldComune, fieldProvincia;
    @FXML
    private Text welcomeTextField;

    private UtenteRegistrato utente;


    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("LogoutOperatore.fxml", utente, event);
    }

    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
       changeSceneAndSetValues("RegistraCentro.fxml", utente, event);
    }

    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraVaccinato.fxml", utente, event);
    }

    public void registraCentro(ActionEvent event) throws IOException, SQLException {
        String nomeCentro = fieldNome.getText();
        String qualificatore = qualificatoreComboBox.getValue();
        String strada = fieldStrada.getText();
        String civico = fieldCivico.getText();
        String comune = fieldComune.getText();
        String provincia = fieldProvincia.getText();
        String cap = fieldCap.getText();
        String tipologia = tipologiaComboBox.getValue();

        // Generic controls
        if(nomeCentro.isBlank() || qualificatore == null || strada.isBlank() ||
                civico.isBlank() || comune.isBlank() || provincia.isBlank()
                || tipologia == null) {
            // TODO: Segnala errore
            return;
        }

        // Specific controls by field
        if(cap.length() != 5 || cap.matches("^[a-zA-Z]+$"))
            if(Integer.parseInt(cap) < 10) {
                // TODO: Segnala errore
                return;
            }

        // TODO: Prima della query, controllare se il centro vaccinale (nome - PK) esiste già all'interno del DB. Se esiste, segnalare errore
        String query = "INSERT INTO centrivaccinali VALUES('"
                + nomeCentro + "', '"
                + tipologia + "', '"
                + qualificatore + "', '"
                + strada + "', '"
                + civico + "', '"
                + comune + "', '"
                + provincia + "', '"
                + cap + "')";
        Proxy proxy = new Proxy();
        proxy.populateCentriVaccinali(query, nomeCentro);

        reset();
    }

    public void reset() {
        fieldNome.clear();
        qualificatoreComboBox.setValue(null);
        fieldStrada.clear();
        fieldCivico.clear();
        fieldComune.clear();
        fieldProvincia.clear();
        fieldCap.clear();
        tipologiaComboBox.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] tipologia = {
                Tipologia.AZIENDALE.toString(),
                Tipologia.HUB.toString(),
                Tipologia.OSPEDALIERO.toString() };
        tipologiaComboBox.getItems().addAll(tipologia);

        String[] qualificatore = {
                Qualificatore.VIA.toString(),
                Qualificatore.VIALE.toString(),
                Qualificatore.PIAZZA.toString(),
                Qualificatore.CORSO.toString() };
        qualificatoreComboBox.getItems().addAll(qualificatore);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        welcomeTextField.setText("Ciao, " + utente.getUsername());
    }
}
