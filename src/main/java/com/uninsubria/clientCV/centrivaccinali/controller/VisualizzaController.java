package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class VisualizzaController extends Controller {

    @FXML
    private Text welcomeTextField;
    @FXML
    private Button btnSegnala;

    private UtenteRegistrato utente;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeScene("Cerca.fxml", event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeScene("RegistraCittadino.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }

    public void switchToSegnalaScene(ActionEvent event) throws IOException {

        if (utente == null)
            btnSegnala.setDisable(true);
        else {
            btnSegnala.setDisable(false);
            changeScene("Segnala.fxml", event);
        }
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        if (utente == null)
            welcomeTextField.setText("Accesso come ospite");
        else
            welcomeTextField.setText("Ciao, " + utente.getUsername());
    }
}
