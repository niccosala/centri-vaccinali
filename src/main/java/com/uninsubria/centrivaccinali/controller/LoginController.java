package com.uninsubria.centrivaccinali.controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class LoginController extends Controller {

    public void switchToHomeCittadinoScene (ActionEvent event) throws IOException {
        changeScene("HomeCittadino.fxml", event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException{
        changeScene("RegistraCittadino.fxml", event);
    }
}
