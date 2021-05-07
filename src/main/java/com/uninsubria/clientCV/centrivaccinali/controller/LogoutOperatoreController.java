package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class LogoutOperatoreController extends Controller {

    private UtenteRegistrato utente;

    @FXML
    Text welcomeTextField;

    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCentro.fxml", utente, event);
    }

    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraVaccinato.fxml", utente, event);
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Login.fxml", utente, event);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        welcomeTextField.setText("Ciao, " + utente.getUsername());
    }
}
