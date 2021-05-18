/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * The type Logout operatore controller.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class LogoutOperatoreController extends Controller {

    private UtenteRegistrato utente;

    @FXML
    private Text welcomeTextField;

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
     * Switch to login scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToLoginScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Login.fxml", utente, event);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        welcomeTextField.setText("Ciao, " + utente.getUsername());
    }

}
