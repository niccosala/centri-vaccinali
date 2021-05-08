package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.temporal.UnsupportedTemporalTypeException;

public class RegistraCittadinoController extends Controller {

    @FXML
    private TextField fieldNome, fieldCognome, fieldCodiceFiscale,
            fieldUsername, fieldEmail, fieldID;
    @FXML
    private PasswordField fieldPassword;

    private UtenteRegistrato utente;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Cerca.fxml", utente, event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCittadino.fxml", utente, event);
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        changeScene("Login.fxml", event);
    }

    public void registraCittadino(ActionEvent event) throws IOException, SQLException {
        String nome = fieldNome.getText();
        String cognome = fieldCognome.getText();
        String CF = fieldCodiceFiscale.getText();
        String user = fieldUsername.getText();
        String password = fieldPassword.getText();
        String email = fieldEmail.getText();
        int IDvaccinazione = Integer.parseInt(fieldID.getText());

        String insertAsUtente = "INSERT INTO utentiregistrati VALUES('"+user+"','"+password+"','"+CF+"','"+nome+"','"+cognome+"')";
        Proxy proxyUtenti = new Proxy();
        proxyUtenti.insertDb(insertAsUtente);

        String insertAsCittadino = "INSERT INTO cittadiniregistrati VALUES('"+IDvaccinazione+"','"+user+"','"+email+"')";;
        Proxy proxyCittadini = new Proxy();
        proxyCittadini.insertDb(insertAsCittadino);

        reset();
    }

    public void reset() {
        fieldNome.setText(null);
        fieldCognome.setText(null);
        fieldUsername.setText(null);
        fieldCodiceFiscale.setText(null);
        fieldEmail.setText(null);
        fieldID.setText(null);
        fieldPassword.setText(null);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
    }
}
