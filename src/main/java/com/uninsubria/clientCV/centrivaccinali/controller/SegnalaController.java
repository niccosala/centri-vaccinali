package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.IOException;

public class SegnalaController extends Controller {

    private UtenteRegistrato utente;

    @FXML
    private TextArea textAreaAggiuntive;
    public static final int MAX_CHARS = 256;

    @FXML
    private Text welcomeTextField;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeScene("Cerca.fxml", event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeScene("RegistraCittadino.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }

    public void switchToVisualizzaScene(ActionEvent event) throws IOException {
        changeScene("Visualizza.fxml", event);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        welcomeTextField.setText("Ciao, " + utente.getUsername());
    }
}
