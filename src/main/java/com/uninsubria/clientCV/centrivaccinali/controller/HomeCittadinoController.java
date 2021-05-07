package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class HomeCittadinoController extends Controller{

    private UtenteRegistrato utente;

    @FXML
    private Text welcomeTextField;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Cerca.fxml", utente, event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Logout.fxml", utente, event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCittadino.fxml", utente, event);
    }

    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        if (utente == null)
            welcomeTextField.setText("Accesso come ospite");
        else
            welcomeTextField.setText("Ciao, " + utente.getUsername());
    }
}
