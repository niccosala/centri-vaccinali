/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.Util;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Registra cittadino controller.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class RegistraCittadinoController extends Controller {

    @FXML
    private TextField fieldNome, fieldCognome, fieldCodiceFiscale,
            fieldUsername, fieldEmail, fieldID;
    @FXML
    private PasswordField fieldPassword;

    private UtenteRegistrato utente;
    private Util util = new Util();

    /**
     * Switch to cerca scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToCercaScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("Cerca.fxml", utente, event);
    }

    /**
     * Switch to registrati scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToRegistratiScene(ActionEvent event) throws IOException {
        changeSceneAndSetValues("RegistraCittadino.fxml", utente, event);
    }

    /**
     * Switch to login scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToLoginScene(ActionEvent event) throws IOException {
        changeScene("Login.fxml", event);
    }

    /**
     * Register new Cittadino.
     *
     * @throws IOException          the io exception
     * @throws SQLException         the sql exception
     * @throws InterruptedException the interrupted exception
     */
    public void registraCittadino() throws IOException, SQLException, InterruptedException {
        String nome = fieldNome.getText();
        String cognome = fieldCognome.getText();
        String CF = fieldCodiceFiscale.getText();
        String user = fieldUsername.getText();
        String password = fieldPassword.getText();
        String email = fieldEmail.getText();
        String id = fieldID.getText();

        if(nome.isBlank() || cognome.isBlank() || CF.isBlank() ||
                user.isBlank() || password.isBlank() || email.isBlank() || fieldID.getText().isBlank()) {
            showWarningDialog("Campi mancanti", "Inserire tutti i campi richiesti");
            return;
        }

        //controllo codice fiscale
        if(!util.cfIsValid(CF)) {
            showWarningDialog("Codice fiscale errato", "Il codice fiscale inserito è errato, riprovare");
            return;
        }

        //controllo email
        if(!util.emailIsValid(email)) {
            showWarningDialog("Email errato", "L'email inserita è errata, riprovare");
            return;
        }

        // controllo id univoco
        if(!isCorrectID(id)) {
            showWarningDialog("ID univoco errato", "L'ID univoco di vaccinazione viene fornito dall'operatore ed è \nformato da sole cifre");
            return;
        }

        String checkUsername = "SELECT userid FROM utentiregistrati WHERE userid = '" + user + "'";
        Proxy proxyCheckUsername = new Proxy();
        ArrayList<String> users = proxyCheckUsername.getSingleValues(checkUsername, "userid");

        // controllo username db
        if(!users.isEmpty()) {
            showWarningDialog("Scegli un username diverso", "L'username selezionato è gia stato usato da un \naltro utente");
            return;
        }

        // controllo idunivoco e codicefiscale db
        String checkIdUnivoco = "SELECT idvacc FROM idunivoci WHERE idvacc = " + id + " AND codicefiscale = '" + CF + "'";
        Proxy proxyCheckId = new Proxy();
        ArrayList<String> ids = proxyCheckId.getSingleValues(checkIdUnivoco, "idvacc");

        if(ids.isEmpty()) {
            showWarningDialog("ID univoco e/o codice fiscale errati", "L'ID univoco e/o il codice fiscale forniti non sono corretti.\nSi ricorda di registrarsi con l'ID univoco fornito dall'operatore\nal momento della vaccinazione e di controllare il codice\nfiscale inserito");
            return;
        }

        String insertAsUtente = "INSERT INTO utentiregistrati VALUES('"+user+"','"+password+"','"+CF+"','"+nome+"','"+cognome+"')";
        Proxy proxyUtenti = new Proxy();
        proxyUtenti.insertDb(insertAsUtente);

        Thread.sleep(100);

        int IDvaccinazione = Integer.parseInt(id);
        String insertAsCittadino = "INSERT INTO cittadiniregistrati VALUES('"+user+"','"+email+"','"+IDvaccinazione+"')";
        Proxy proxyCittadini = new Proxy();
        proxyCittadini.insertDb(insertAsCittadino);

        reset();
        showSuccessDialog("Registrazione andata a buon fine", "Ti sei correttamente registrato");
    }

    /**
     * Reset fields.
     */
    public void reset() {
        fieldNome.clear();
        fieldCognome.clear();
        fieldUsername.clear();
        fieldCodiceFiscale.clear();
        fieldEmail.clear();
        fieldID.clear();
        fieldPassword.clear();
    }

    private boolean isCorrectID(String id) throws IOException {

        if(id.matches("^[a-zA-Z]+$"))
            return false;

        int IDvaccinazione = Integer.parseInt(id);
        ArrayList<String> ids;
        String query = "SELECT * FROM idunivoci WHERE idvacc = '"+IDvaccinazione+"'";

        Proxy proxy = new Proxy();
        ids = proxy.getSingleValues(query, "idvacc");

        return !ids.isEmpty();
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
    }

}
