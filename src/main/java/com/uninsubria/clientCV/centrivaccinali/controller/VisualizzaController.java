/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */

package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.CentriVaccinali;
import com.uninsubria.clientCV.centrivaccinali.entity.CentroVaccinale;
import com.uninsubria.clientCV.centrivaccinali.entity.Segnalazione;
import com.uninsubria.clientCV.centrivaccinali.entity.Sintomo;
import com.uninsubria.clientCV.centrivaccinali.entity.Vaccinato;
import com.uninsubria.clientCV.cittadini.entity.CittadinoRegistrato;
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
import java.util.HashMap;

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
        CittadinoRegistrato cittadino = (CittadinoRegistrato)utente;
        String query = "SELECT * FROM vaccinati_" + centroVaccinale.getNome() + " WHERE idvacc = " + cittadino.getIdVaccinazione();
        System.out.println(query);
        Proxy proxy;

        try {
            proxy = new Proxy();
            ArrayList<Vaccinato> vaccinati = proxy.getVaccinati(query);

            if(vaccinati.isEmpty()) {
                showWarningDialog("Non sei registrato a questo centro vaccinale", "Puoi segnalare eventi avversi solo presso il centro vaccinale in cui ti è stato somministrato il vaccino");
                return;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

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

        String query = "SELECT * FROM centrivaccinali WHERE nome = '" + centro + "'";
        String querySegnalazione = "SELECT * FROM segnalazione join eventiavversi on (eventiavversi.idevento = segnalazione.idevento) WHERE centrovaccinale = '" + centro + "'";
        StringBuilder descrizioni = new StringBuilder();
        StringBuilder severita = new StringBuilder();
        int totaleSegnalazioni = 0;
        ArrayList<Segnalazione> segnalazioni = new ArrayList<>();

        try {
            proxy = new Proxy();
            proxy1 = new Proxy();
            centroVaccinale = proxy.pickCentro(query);
            segnalazioni = proxy1.getSegnalazione(querySegnalazione);

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Segnalazione s : segnalazioni) {
            for(int i = 1; i <= 5; i++) {
                if (i <= s.getSeverita())
                    severita.append("◆");
                else
                    severita.append("◇");
            }

            descrizioni
                    .append("Sintomo: ")
                    .append(s.getSintomo())
                    .append("\nSeverità: ")
                    .append(severita)
                    .append("\nDescrizione: ")
                    .append(s.getDescrizione())
                    .append("\n\n");
            totaleSegnalazioni += s.getSeverita();

            severita.delete(0, severita.length());
        }

        nomeCentroText.setText(centroVaccinale.getNome());
        tipologiaText.setText(centroVaccinale.getTipologia().toString());
        indirizzoText.setText(centroVaccinale.getIndirizzo().toString());
        numeroSegnalazioni.setText(String.valueOf(segnalazioni.size()));
        labelSegnalazioni.setText(String.valueOf(descrizioni));

        double media = ((double) totaleSegnalazioni) / segnalazioni.size();
        if (Double.isNaN(media))
            mediaSeverita.setText("0,0 / 5,00");
        else
            mediaSeverita.setText(String.format("%.02f", media) + " / 5,00");
    }
}
