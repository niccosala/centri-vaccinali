/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */

package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.CentriVaccinali;
import com.uninsubria.clientCV.centrivaccinali.entity.CentroVaccinale;
import com.uninsubria.clientCV.centrivaccinali.entity.Segnalazione;
import com.uninsubria.clientCV.condivisa.Util;
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
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisualizzaController extends Controller  {

    private UtenteRegistrato utente;
    private CentroVaccinale centroVaccinale;

    @FXML
    private Text welcomeText, nomeCentroText, tipologiaText, numeroSegnalazioni, mediaSeverita;
    @FXML
    private Button btnSegnala, btnRegistrati, btnLogout;
    @FXML
    private Label indirizzoText;
    @FXML
    private TextArea labelSegnalazioni;

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
        Proxy proxy, proxy1;
        Util util = new Util();

        String query = "SELECT * FROM centrivaccinali WHERE nome = '" + centro.toLowerCase() + "'";
        String querySegnalazione = "SELECT * FROM segnalazione join eventiavversi on (eventiavversi.idevento = segnalazione.idevento) WHERE centrovaccinale = '" + centro.toLowerCase() + "'";
        StringBuilder descrizioni = new StringBuilder();
        int totaleSegnalazioni = 0;
        ArrayList<Segnalazione> segnalazioni = new ArrayList<>();

        try {
            proxy = new Proxy();
            proxy1 = new Proxy();
            centroVaccinale = proxy.filter(query).get(0);
            segnalazioni = proxy1.getSegnalazione(querySegnalazione);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        for (Segnalazione s : segnalazioni) {
            descrizioni.append(s.getSintomo()).append("\nIntensità: ").append(s.getSeverita()).append("\n\n");
            totaleSegnalazioni += s.getSeverita();
        }

        /*String getUtenteFromVaccinati_nomeCentro = "SELECT * FROM vaccinati_"+ centroVaccinale.getNome()+" WHERE codfisc = '"+ utente.getCF() +"'";
        System.out.println(getUtenteFromVaccinati_nomeCentro);*/

        nomeCentroText.setText(util.lowercaseNotFirst(centroVaccinale.getNome()));
        tipologiaText.setText(centroVaccinale.getTipologia().toString());
        indirizzoText.setText(centroVaccinale.getIndirizzo().toString());
        numeroSegnalazioni.setText(String.valueOf(segnalazioni.size()));
        labelSegnalazioni.setText(String.valueOf(descrizioni));

        double media = ((double) totaleSegnalazioni) / segnalazioni.size();
        if (Double.isNaN(media))
            mediaSeverita.setText("0.0 / 5");
        else
            mediaSeverita.setText(String.format("%.02f", media) + " / 5");
    }
}
