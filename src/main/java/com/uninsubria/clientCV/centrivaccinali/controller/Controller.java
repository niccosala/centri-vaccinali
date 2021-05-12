package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.centrivaccinali.CentriVaccinali;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public abstract class Controller {

    public static final String path = "com/uninsubria/layout/";

    public void changeScene(String layout, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(CentriVaccinali.class.getClassLoader().getResource(
                        path + layout)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneAndSetValues(String layout, UtenteRegistrato utente, ActionEvent event) throws IOException {
        FXMLLoader loader = new
                FXMLLoader(CentriVaccinali.class.getClassLoader().getResource(path + layout));

        Parent root = loader.load();

        Controller mController = loader.getController();
        mController.setUtente(utente);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showDialog(String title, String body) {
        Alert warningDialog = new Alert(Alert.AlertType.WARNING, "", ButtonType.CLOSE);
        warningDialog.setHeaderText(title);
        warningDialog.setContentText(body);
        warningDialog.show();
    }

    public abstract void setUtente(UtenteRegistrato utente);
}
