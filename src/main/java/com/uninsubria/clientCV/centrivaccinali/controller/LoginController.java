/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.CentriVaccinali;
import com.uninsubria.clientCV.cittadini.entity.CittadinoRegistrato;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import com.uninsubria.serverCV.ServerInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;

/**
 * The type Login controller.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private UtenteRegistrato utente;

    /**
     * Login as guest, no username or password required. Functionalities are limited
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void loginAsGuest(ActionEvent event) throws IOException {
        changeSceneAndSetValues("HomeCittadino.fxml", null, event);
    }

    public void goToConnectionSettings() throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(CentriVaccinali.class.getClassLoader().getResource(
                        "com/uninsubria/layout/ImpostazioniConnessione.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Impostazioni connessione");
        stage.show();
    }

    /**
     * Switch to registrati scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void switchToRegistratiScene(ActionEvent event) throws IOException{
        changeSceneAndSetValues("RegistraCittadino.fxml", null, event);
    }

    /**
     * Verify login: check the correctness of the fields and the compliance of the constraints in the db.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void verifyLogin(ActionEvent event) throws IOException {

        if(!tryConnection())
            return;

        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            showWarningDialog("Campi mancanti", "Inserire username e password per accedere");
            return;
        }

        Proxy proxy = new Proxy();
        String query = "select * from utentiregistrati where userid = '" + username+ "'and pword = '" + password +"'";
        utente = proxy.login(query, username);

        if(utente == null) {
            showWarningDialog("Credenziali errate", "Username e password non corrispondono a nessun utente\nregistrato");
        } else {
            if(utente instanceof CittadinoRegistrato) {
                changeSceneAndSetValues("HomeCittadino.fxml", utente, event);
            }
            else {
                changeSceneAndSetValues("HomeOperatore.fxml", utente, event);

            }
        }
    }

    /**
     * Reset fields.
     */
    public void reset() {
        usernameTextField.clear();
        passwordField.clear();
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
        this.utente = utente;
    }

    public boolean tryConnection() throws IOException {
        boolean connected;

        connected = pingHost(ServerInfo.IP_SERVER, ServerInfo.PORT);

        if (!connected) {
            goToConnectionSettings();
            return false;
        }
        return true;

    }

    private static boolean pingHost(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 1500);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
