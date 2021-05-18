/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.cittadini.entity.CittadinoRegistrato;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * The type Login controller.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private UtenteRegistrato utente;

    /**
     * Accedi come ospite.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void accediComeOspite (ActionEvent event) throws IOException {
        changeSceneAndSetValues("HomeCittadino.fxml", null, event);
    }

    /**
     * Switch to registrati scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToRegistratiScene(ActionEvent event) throws IOException{
        changeSceneAndSetValues("RegistraCittadino.fxml", null, event);
    }

    /**
     * Verify login.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void verifyLogin(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            showWarningDialog("Campi mancanti", "Inserire username e password per accedere");
            return;
        }

        Proxy proxy = new Proxy();
        String query = "select * from utentiregistrati where userid = '" + username+ "'and pword = '" + password +"'";
        utente = proxy.login(query, username);

        if(utente == null) {
            showWarningDialog("Credenziali errate", "Username e password non corrispondono a nessun utente\nregistrato");
        } else {
            if(utente instanceof CittadinoRegistrato) {
                changeSceneAndSetValues("HomeCittadino.fxml", utente, event);
            }
            else {
                changeSceneAndSetValues("HomeOperatore.fxml", utente, event);

            }
        }
    }

    /**
     * Reset.
     */
    public void reset() {
        usernameTextField.clear();
        passwordField.clear();
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
    }
}
