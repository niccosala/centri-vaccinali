package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class HomeOperatoreController extends Controller {

    private UtenteRegistrato utente;
    @FXML
    private Text welcomeTextField;

    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCentro.fxml", utente, event);
    }

    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraVaccinato.fxml", utente, event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("LogoutOperatore.fxml", utente, event);
    }

    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        welcomeTextField.setText(utente.getUsername());
    }
}
