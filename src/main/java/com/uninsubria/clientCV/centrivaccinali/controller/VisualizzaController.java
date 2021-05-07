package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class VisualizzaController extends Controller {

    private UtenteRegistrato utente;

    @FXML
    private Text welcomeText;
    @FXML
    private Button btnSegnala, btnRegistrati, btnLogout;

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

    public void switchToSegnalaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Segnala.fxml", utente, event);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        if (utente == null) {
            welcomeText.setText("Accesso come ospite");
            btnSegnala.setDisable(true);
        }
        else {
            welcomeText.setText("Ciao, " + utente.getUsername());
            btnSegnala.setDisable(false);
            if (utente == null) {
                welcomeText.setText("Accesso come ospite");
                btnRegistrati.setDisable(false);
                btnLogout.setText("Accedi");
            }
            else {
                welcomeText.setText("Ciao, " + utente.getUsername());
                btnRegistrati.setDisable(true);
            }
        }
    }
}
