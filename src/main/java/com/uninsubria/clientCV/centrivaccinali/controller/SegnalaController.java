package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.entity.CentroVaccinale;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.IOException;

public class SegnalaController extends Controller {

    private UtenteRegistrato utente;
    private CentroVaccinale centroVaccinale;

    @FXML
    private TextArea textAreaAggiuntive;
    public static final int MAX_CHARS = 256;

    @FXML
    private Text welcomeTextField, nomeCentroText;
    @FXML
    private Button btnRegistrati, btnLogout;

    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Cerca.fxml", utente, event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCittadino.fxml", utente, event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        if(utente == null)
            changeSceneAndSetValues("Login.fxml", null, event);
        else
            changeSceneAndSetValues("LogoutCittadino.fxml", utente, event);
    }

    public void switchToVisualizzaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Visualizza.fxml", utente, event);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        welcomeTextField.setText("Ciao, " + utente.getUsername());
        btnRegistrati.setDisable(true);

        //inutile fare il controllo, ci può accedere solo utente registrato

        /*if (utente == null) {
            welcomeTextField.setText("Accesso come ospite");
            btnRegistrati.setDisable(false);
            btnLogout.setText("Accedi");
        }
        else {
            welcomeTextField.setText("Ciao, " + utente.getUsername());
            btnRegistrati.setDisable(true);
        }*/
    }

    public void setCentro(CentroVaccinale centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
        nomeCentroText.setText(centroVaccinale.getNome());
    }
}
