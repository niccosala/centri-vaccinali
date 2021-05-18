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
import com.uninsubria.clientCV.condivisa.Util;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SegnalaController extends Controller implements Initializable {

    private UtenteRegistrato utente;
    private CentroVaccinale centroVaccinale;
    private Map<String, Integer> idevento;
    private boolean isNew = true;

    public static final int MAX_CHARS = 256;

    @FXML
    private Text welcomeTextField, nomeCentroText;
    @FXML
    private Button btnRegistrati;
    @FXML
    private ComboBox<String> sintomoComboBox;
    @FXML
    private TextArea textAreaAggiuntive;
    @FXML
    private Slider severitaSlider;
    @FXML
    private Label descrizioneSintomo;

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
        FXMLLoader loader = new
                FXMLLoader(CentriVaccinali.class.getClassLoader().getResource(path + "Visualizza.fxml"));
        Parent root = loader.load();

        Controller mController = loader.getController();
        VisualizzaController visualizzaController = loader.getController();

        mController.setUtente(utente);
        visualizzaController.setCentro(centroVaccinale.getNome());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void pubblicaSegnalazione(ActionEvent event) throws IOException {
        String nomeCentro = centroVaccinale.getNome();
        String descrizione = textAreaAggiuntive.getText().trim();
        String sintomo = sintomoComboBox.getValue();
        int severita = (int) severitaSlider.getValue();
        String query;

        if(descrizione.isBlank() || sintomoComboBox.getValue() == null) {
            showWarningDialog("Campi mancanti", "Inserire tutti i campi richiesti");
            return;
        }

        if(isNew)
            query = "INSERT INTO segnalazione (idevento, userid, centrovaccinale, severita, descrizione) VALUES("+idevento.get(sintomo)+", '"+utente.getUsername()+"', '"+nomeCentro+"', "+severita+",'"+descrizione+"')";
        else
            query = "UPDATE segnalazione SET idevento = "+idevento.get(sintomo)+", severita = "+severita+", descrizione = '"+descrizione+"' WHERE userid = '"+utente.getUsername()+"'";

        System.out.println(query);
        Proxy proxy;

        try {
            proxy = new Proxy();
            proxy.insertDb(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        reset();
        isNew = false;
        showSuccessDialog("Successo", "Segnalazione avvenuta con successo!");
        switchToVisualizzaScene(event);
    }

    public void showDescrizioneSintomo() {
        String sintomoCombo = sintomoComboBox.getValue();
        String query = "SELECT * FROM eventiavversi WHERE sintomo = '" + sintomoCombo + "'";
        Proxy proxy;
        ArrayList<Sintomo> sintomi;

        try {
            proxy = new Proxy();
            sintomi = proxy.getSintomi(query);
            if(sintomi.size() > 0)
                descrizioneSintomo.setText(sintomi.get(0).getDescrizione());

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        welcomeTextField.setText("Ciao, " + utente.getUsername());
        btnRegistrati.setDisable(true);

        Proxy proxy;
        ArrayList<Segnalazione> segnalazione;

        try {
            proxy = new Proxy();
            String query = "SELECT * FROM segnalazione join eventiavversi on (eventiavversi.idevento = segnalazione.idevento) WHERE userid = '" + utente.getUsername() + "'";
            segnalazione = proxy.getSegnalazione(query);

            if (segnalazione.size() > 0) {
                showWarningDialog("Hai già rilasciato una segnalazione", "Se modifichi la tua segnalazione, quella precedente verrà rimossa");
                sintomoComboBox.setValue(segnalazione.get(0).getSintomo());
                severitaSlider.setValue(segnalazione.get(0).getSeverita());
                textAreaAggiuntive.setText(segnalazione.get(0).getDescrizione());
                showDescrizioneSintomo();
                isNew = false;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setCentro(CentroVaccinale centroVaccinale) {
        Util util = new Util();
        this.centroVaccinale = centroVaccinale;
        nomeCentroText.setText(util.lowercaseNotFirst(centroVaccinale.getNome()));
    }

    private void reset() {
        sintomoComboBox.setValue(null);
        textAreaAggiuntive.clear();
        severitaSlider.setValue(1);
        descrizioneSintomo.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query = "SELECT * FROM eventiavversi";
        ArrayList<Sintomo> sintomi;
        Proxy proxy;
        idevento = new HashMap<>();

        //change ComboBox Font
        setUpComboBox(sintomoComboBox);

        try {
            proxy = new Proxy();
            sintomi = proxy.getSintomi(query);

            for (Sintomo sintomo: sintomi) {
                sintomoComboBox.getItems().add(sintomo.getNome());
                idevento.put(sintomo.getNome(), sintomo.getIdevento());
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkCharNumber(KeyEvent event) {
        textAreaAggiuntive.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= MAX_CHARS ? change : null));
    }
}
