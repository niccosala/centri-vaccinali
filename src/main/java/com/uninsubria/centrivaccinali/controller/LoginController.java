package com.uninsubria.centrivaccinali.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void switchToHomeCittadinoScene (ActionEvent event) throws IOException {
        changeScene("HomeCittadino.fxml", event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException{
        changeScene("RegistraCittadino.fxml", event);
    }

    public void reset(ActionEvent event) {
        usernameTextField.setText(null);
        passwordField.setText(null);
    }
}
