package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.CentriVaccinali;
import com.uninsubria.clientCV.cittadini.entity.CittadinoRegistrato;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Proxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private UtenteRegistrato utente;

    public void accediComeOspite (ActionEvent event) throws IOException {
        changeSceneAndSetValues("HomeCittadino.fxml", null, event);
    }

    public void switchToRegistratiScene(ActionEvent event) throws IOException{
        changeSceneAndSetValues("RegistraCittadino.fxml", utente, event);
    }

    public void verifyLogin(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank())
            return;

        Proxy proxy = new Proxy();
        String query = "select * from utentiregistrati where userid = '" + username+ "'and pword = '" + password +"'";
        utente = proxy.login(query, username);

        if(utente == null) {
            //credenziali sono errate show dialog
        } else {
            if(utente instanceof CittadinoRegistrato) {

                //da testare con DB, il metodo ha stesso scopo del codice commentato

                /*FXMLLoader loader = new
                        FXMLLoader(CentriVaccinali.class.getClassLoader().getResource("com/uninsubria/layout/HomeCittadino.fxml"));

                Parent root = loader.load();

                HomeCittadinoController controller = loader.getController();
                controller.setUtente(utente);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();*/

                changeSceneAndSetValues("HomeCittadino.fxml", utente, event);
            }
            else {

                //da testare con DB, il metodo ha stesso scopo del codice commentato

                /*switchToHomeOperatoreScene(event);
                FXMLLoader loader = new
                        FXMLLoader(CentriVaccinali.class.getClassLoader().getResource("com/uninsubria/layout/HomeOperatore.fxml"));

                Parent root = loader.load();

                //instance of HomeCittadinoController controller/HomeOperatoreController
                HomeOperatoreController controller = loader.getController();
                controller.setUtente(utente);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();*/
                changeSceneAndSetValues("HomeOperatore.fxml", utente, event);

            }
        }
    }

    public void reset() {
        usernameTextField.setText(null);
        passwordField.setText(null);
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {

    }
}
