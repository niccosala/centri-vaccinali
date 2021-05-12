package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.CentriVaccinali;
import com.uninsubria.clientCV.centrivaccinali.entity.CentroVaccinale;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualizzaController extends Controller {

    private UtenteRegistrato utente;
    private CentroVaccinale centroVaccinale;

    @FXML
    private Text welcomeText, nomeCentroText, tipologiaText, numeroSegnalazioni, mediaSeverita;
    @FXML
    private Button btnSegnala, btnRegistrati, btnLogout;
    @FXML
    private Label indirizzoText;

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

    public void switchToSegnalaScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new
                FXMLLoader(CentriVaccinali.class.getClassLoader().getResource(path + "Segnala.fxml"));
        Parent root = loader.load();

        Controller mController = loader.getController();
        SegnalaController segnalaController = loader.getController();

        mController.setUtente(utente);
        segnalaController.setCentro(centroVaccinale);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        if (utente == null) {
            welcomeText.setText("Accesso come ospite");
            btnSegnala.setDisable(true);
            btnLogout.setText("Accedi");
        }
        else {
            welcomeText.setText("Ciao, " + utente.getUsername());
            btnSegnala.setDisable(false);
            btnRegistrati.setDisable(true);
            }
    }

    public void setCentro(String centro) {
        Proxy proxy;

        String query = "SELECT * FROM centrivaccinali WHERE nome = '"+ centro +"'";

        try {
            proxy = new Proxy();
            centroVaccinale = proxy.pickCentro(query);

        } catch (IOException e) {
            e.printStackTrace();
        }

        nomeCentroText.setText(centroVaccinale.getNome());
        tipologiaText.setText(centroVaccinale.getTipologia().toString());
        indirizzoText.setText(centroVaccinale.getIndirizzo().toString());
        mediaSeverita.setText("demo");
        numeroSegnalazioni.setText("demo");

    }
}
