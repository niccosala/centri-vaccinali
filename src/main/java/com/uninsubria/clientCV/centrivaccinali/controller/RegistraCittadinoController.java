package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.Util;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class RegistraCittadinoController extends Controller {

    @FXML
    private TextField fieldNome, fieldCognome, fieldCodiceFiscale,
            fieldUsername, fieldEmail, fieldID;
    @FXML
    private PasswordField fieldPassword;

    private UtenteRegistrato utente;
    private Util util = new Util();

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Cerca.fxml", utente, event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCittadino.fxml", utente, event);
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        changeScene("Login.fxml", event);
    }

    public void registraCittadino() throws IOException, SQLException, InterruptedException {
        String nome = fieldNome.getText();
        String cognome = fieldCognome.getText();
        String CF = fieldCodiceFiscale.getText();
        String user = fieldUsername.getText();
        String password = fieldPassword.getText();
        String email = fieldEmail.getText();

        if(nome.isBlank() || cognome.isBlank() || CF.isBlank() ||
                user.isBlank() || password.isBlank() || email.isBlank() || fieldID.getText().isBlank() )
            return;

        //controllo codice fiscale
        if(!util.cfIsValid(CF)) {
            showDialog("Codice fiscale errato", "Il codice fiscale inserito è errato, riprovare");
            return;
        }

        //controllo email
        if(!util.emailIsValid(email)) {
            showDialog("Email errato", "L'email inserita è errata, riprovare");
            return;
        }

        int IDvaccinazione = Integer.parseInt(fieldID.getText());

        String insertAsUtente = "INSERT INTO utentiregistrati VALUES('"+user+"','"+password+"','"+CF+"','"+nome+"','"+cognome+"')";
        Proxy proxyUtenti = new Proxy();
        proxyUtenti.insertDb(insertAsUtente);

        Thread.sleep(100);

        String insertAsCittadino = "INSERT INTO cittadiniregistrati VALUES('"+user+"','"+email+"','"+IDvaccinazione+"')";
        Proxy proxyCittadini = new Proxy();
        proxyCittadini.insertDb(insertAsCittadino);

        reset();
    }

    public void reset() {
        fieldNome.clear();
        fieldCognome.clear();
        fieldUsername.clear();
        fieldCodiceFiscale.clear();
        fieldEmail.clear();
        fieldID.clear();
        fieldPassword.clear();
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
    }
}
