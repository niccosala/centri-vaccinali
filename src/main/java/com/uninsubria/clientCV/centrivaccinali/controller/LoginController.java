package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.CentriVaccinali;
import com.uninsubria.clientCV.centrivaccinali.entity.Tipologia;
import com.uninsubria.clientCV.cittadini.entity.CittadinoRegistrato;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private UtenteRegistrato utente;

    public void accediComeOspite (ActionEvent event) throws IOException {
        //changeScene("HomeCittadino.fxml", event);
        changeSceneAndSetValues("HomeCittadino.fxml", null, event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException{
        changeSceneAndSetValues("RegistraCittadino.fxml", null, event);
    }

    public void switchToHomeOperatoreScene(ActionEvent event) throws IOException{
        changeScene("HomeOperatore.fxml", event);
    }

    public void verifyLogin(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            showDialog("Campi mancanti", "Inserire username e password per accedere");
            return;
        }

        Proxy proxy = new Proxy();
        String query = "select * from utentiregistrati where userid = '" + username+ "'and pword = '" + password +"'";
        utente = proxy.login(query, username);

        if(utente == null) {
            showDialog("Credenziali errate", "Username e password non corrispondono a nessun utente\nregistrato");
        } else {
            if(utente instanceof CittadinoRegistrato) {
                changeSceneAndSetValues("HomeCittadino.fxml", utente, event);
            }
            else {
                changeSceneAndSetValues("HomeOperatore.fxml", utente, event);

            }
        }
    }

    public void reset() {
        usernameTextField.clear();
        passwordField.clear();
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
    }
}
