/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.entity.CentroVaccinale;
import com.uninsubria.clientCV.centrivaccinali.entity.Qualificatore;
import com.uninsubria.clientCV.centrivaccinali.entity.Tipologia;
import com.uninsubria.clientCV.condivisa.Util;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Registra centro controller.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class RegistraCentroController extends Controller implements Initializable {

    @FXML
    private ComboBox<String> qualificatoreComboBox, tipologiaComboBox;
    @FXML
    private TextField fieldNome, fieldStrada, fieldCap,
            fieldCivico, fieldComune, fieldProvincia;
    @FXML
    private Text welcomeTextField;

    private UtenteRegistrato utente;
    private Util util = new Util();


    /**
     * Switch to logout scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("LogoutOperatore.fxml", utente, event);
    }

    /**
     * Switch to registra centro scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
       changeSceneAndSetValues("RegistraCentro.fxml", utente, event);
    }

    /**
     * Switch to registra vaccinato scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraVaccinato.fxml", utente, event);
    }

    /**
     * Register new CentroVaccinale.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public void registraCentro() throws IOException, SQLException {
        String nomeCentro = fieldNome.getText().trim();
        String qualificatore = qualificatoreComboBox.getValue();
        String strada = util.lowercaseNotFirst(fieldStrada.getText());
        String civico = fieldCivico.getText();
        String comune = util.lowercaseNotFirst(fieldComune.getText());
        String provincia = fieldProvincia.getText();
        String cap = fieldCap.getText();
        String tipologia = tipologiaComboBox.getValue();

        // Generic controls
        if(nomeCentro.isBlank() || qualificatore == null || strada.isBlank() ||
                civico.isBlank() || comune.isBlank() || provincia.isBlank()
                || tipologia == null) {
            showWarningDialog("Campi mancanti", "Inserire tutti i campi richiesti");
            return;
        }

        // Specific controls by field
        // CAP
        if(!cap.matches("[0-9]+")) {
            showWarningDialog("Errore nei dati inseriti", "Il CAP inserito non è valido");
            return;
        } else if(cap.length() != 5 || Integer.parseInt(cap) < 10) {
            showWarningDialog("Errore nei dati inseriti", "Il CAP inserito è errato o non esistente");
            return;
        }

        // controllo provincia
        if(provincia.length() != 2 || !provincia.matches("^[a-zA-Z]+$")) {
            showWarningDialog("Errore nei dati inseriti", "La provincia inserita è errata");
            return;
        }

        // controllo civico
        if(civico.length()  > 5) {
            showWarningDialog("Errore nei dati inseriti", "Il numero civico inserito è errato");
            return;
        }

        String query = "INSERT INTO centrivaccinali VALUES('"
                + nomeCentro.toLowerCase() + "', '"
                + tipologia + "', '"
                + qualificatore + "', '"
                + strada + "', '"
                + civico + "', '"
                + comune + "', '"
                + provincia.toUpperCase() + "', '"
                + cap + "')";

        Proxy proxy = new Proxy();
        Proxy proxy1 = new Proxy();

        if (centroExist())
            showWarningDialog("Centro già esistente", "Il centro che si sta cercando di inserire è già stato registrato");
        else {
            proxy.insertDb(query);
            proxy1.populateCentriVaccinali(nomeCentro);
            showSuccessDialog("Successo", "Centro registrato correttamente!");
        }

        reset();
    }

    /**
     * Reset fields.
     */
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

    private boolean centroExist() {
        Proxy proxy;
        ArrayList<CentroVaccinale> centriVaccinali = new ArrayList<>();
        String nomeCentro = fieldNome.getText().trim().toLowerCase();

        String query = "SELECT * FROM centrivaccinali WHERE nome = '" + nomeCentro + "'";

        try {
             proxy = new Proxy();
             centriVaccinali = proxy.filter(query);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return !centriVaccinali.isEmpty();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] tipologia = {
                Tipologia.AZIENDALE.toString(),
                Tipologia.HUB.toString(),
                Tipologia.OSPEDALIERO.toString() };
        tipologiaComboBox.getItems().addAll(tipologia);

        setUpComboBox(tipologiaComboBox);

        String[] qualificatore = {
                Qualificatore.VIA.toString(),
                Qualificatore.VIALE.toString(),
                Qualificatore.PIAZZA.toString(),
                Qualificatore.CORSO.toString() };
        qualificatoreComboBox.getItems().addAll(qualificatore);

        qualificatoreComboBox.setStyle(
                "-fx-font: 13px \"Microsoft Sans Serif\";" +
                        "    -fx-border-radius:  30 0 0 30;\n" +
                        "    -fx-background-radius:  30 0 0 30;\n" +
                        "    -fx-border-style: solid;\n" +
                        "    -fx-border-color: silver;\n" +
                        "    -fx-border-width: 1.5;\n" +
                        "    -fx-background-color: rgba(255,255,255,0.75)"
        );
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        welcomeTextField.setText("Ciao, " + utente.getUsername());
    }

}
