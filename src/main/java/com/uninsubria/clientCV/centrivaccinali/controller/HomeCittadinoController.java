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
 * The type Home cittadino controller.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class HomeCittadinoController extends Controller{

    @FXML
    private Text welcomeTextField;
    @FXML
    private Button btnLogout, btnRegistrati;

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
     * Switch to logout scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToLogoutScene(ActionEvent event) throws IOException {
        if(utente == null)
            changeSceneAndSetValues("Login.fxml", null, event);
        else
            changeSceneAndSetValues("LogoutCittadino.fxml", utente, event);
    }

    /**
     * Switch to registrati scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeScene("RegistraCittadino.fxml", event);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        if (utente == null) {
            welcomeTextField.setText("Accesso come ospite");
            btnRegistrati.setDisable(false);
            btnLogout.setText("Accedi");
        }
        else {
            welcomeTextField.setText("Ciao, " + utente.getUsername());
            btnRegistrati.setDisable(true);
            btnLogout.setText("Logout");
        }
    }
}
