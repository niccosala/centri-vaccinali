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
    private Button btnSegnala;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Cerca.fxml", utente, event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeScene("RegistraCittadino.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }

    public void switchToSegnalaScene(ActionEvent event) throws IOException {
        changeScene("Segnala.fxml", event);
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

        }
    }
}
