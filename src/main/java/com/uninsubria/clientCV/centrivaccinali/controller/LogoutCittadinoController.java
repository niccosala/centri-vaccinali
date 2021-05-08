package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class LogoutCittadinoController extends Controller {

    @FXML
    private Text welcomeTextField;
    @FXML
    private Button btnRegistrati;

    private UtenteRegistrato utente;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Cerca.fxml", utente, event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCittadino.fxml", utente, event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("LogoutCittadino.fxml", utente, event);
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Login.fxml", utente, event);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        if (utente == null) {
            welcomeTextField.setText("Accesso come ospite");
            btnRegistrati.setDisable(false);
        }
        else {
            welcomeTextField.setText("Ciao, " + utente.getUsername());
            btnRegistrati.setDisable(true);
        }
    }
}
