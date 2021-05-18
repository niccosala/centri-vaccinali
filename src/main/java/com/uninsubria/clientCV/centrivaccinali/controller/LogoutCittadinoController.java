/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * The type Logout cittadino controller.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class LogoutCittadinoController extends Controller {

    @FXML
    private Text welcomeTextField;
    @FXML
    private Button btnRegistrati;

    private UtenteRegistrato utente;

    /**
     * Switch to cerca scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Cerca.fxml", utente, event);
    }

    /**
     * Switch to registrati scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCittadino.fxml", utente, event);
    }

    /**
     * Switch to logout scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("LogoutCittadino.fxml", utente, event);
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
