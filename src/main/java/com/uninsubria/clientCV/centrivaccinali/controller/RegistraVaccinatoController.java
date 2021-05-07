package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.entity.Vaccino;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class RegistraVaccinatoController extends Controller implements Initializable {

    @FXML
    private ComboBox<String> vaccinoComboBox;
    @FXML
    private TextField fieldNome, fieldCognome, fieldCodiceFiscale;
    @FXML
    private DatePicker fieldData;
    @FXML
    private Text welcomeTextField;

    private UtenteRegistrato utente;

    public void switchToRegistraCentroScene(ActionEvent event) throws IOException {
        changeScene("RegistraCentro.fxml", event);
    }

    public void switchToRegistraVaccinatoScene(ActionEvent event) throws IOException {
        changeScene("RegistraVaccinato.fxml", event);
    }

    public void switchToLogoutScene(ActionEvent event) throws IOException {
        changeScene("LogoutCittadino.fxml", event);
    }

    public void registraVaccinato(ActionEvent event) throws ParseException, IOException, SQLException {
        String nome = fieldNome.getText();
        String cognome = fieldCognome.getText();
        String CF = fieldCodiceFiscale.getText();
        String vaccino = vaccinoComboBox.getValue();
        LocalDate date = fieldData.getValue();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String data = date.toString();
        Date myDate = formatter.parse(data);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        String query = "INSERT INTO vaccinati_"+nome+" VALUES('"+cognome+"','"+CF+"','"+sqlDate+"','"+vaccino+"')";
        Proxy proxy = new Proxy();
        proxy.insertDb(query);
        reset();
    }

    public void reset() {
        fieldNome.setText(null);
        fieldCognome.setText(null);
        fieldCodiceFiscale.setText(null);
        fieldData.setValue(null);
        vaccinoComboBox.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] vaccino = {Vaccino.ASTRAZENECA.toString(), Vaccino.JANDJ.toString(),
                Vaccino.MODERNA.toString(), Vaccino.PFIZER.toString()};

        vaccinoComboBox.getItems().addAll(vaccino);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
        welcomeTextField.setText(utente.getUsername());
    }
}
