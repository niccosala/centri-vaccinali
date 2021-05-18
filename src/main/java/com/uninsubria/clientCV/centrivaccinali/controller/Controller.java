/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * The type Controller.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public abstract class Controller {

    /**
     * The constant path.
     */
    public static final String path = "com/uninsubria/layout/";

    /**
     * Change scene: to switch from a scene to another.
     *
     * @param layout the layout
     * @param event  the event
     * @throws IOException the io exception
     */
    public void changeScene(String layout, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(CentriVaccinali.class.getClassLoader().getResource(
                        path + layout)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Change scene and set values: to switch from a scene to another passing the current user.
     *
     * @param layout the layout
     * @param utente the user
     * @param event  the event
     * @throws IOException the io exception
     */
    public void changeSceneAndSetValues(String layout, UtenteRegistrato utente, ActionEvent event) throws IOException {
        FXMLLoader loader = new
                FXMLLoader(CentriVaccinali.class.getClassLoader().getResource(path + layout));

        Parent root = loader.load();

        Controller mController = loader.getController();
        mController.setUtente(utente);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        //per usare file css
        /*scene.getStylesheets().add(Objects.requireNonNull(
                CentriVaccinali.class.getClassLoader().getResource("com/uninsubria/layout/style/Generic.css"))
                .toExternalForm());*/

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Show warning dialog.
     *
     * @param title the title
     * @param body  the body
     */
    public void showWarningDialog(String title, String body) {
        Alert warningDialog = new Alert(Alert.AlertType.WARNING, "", ButtonType.CLOSE);
        warningDialog.setHeaderText(title);
        warningDialog.setContentText(body);
        warningDialog.show();
    }

    /**
     * Show success dialog.
     *
     * @param title the title
     * @param body  the body
     */
    public void showSuccessDialog(String title, String body) {
        Alert warningDialog = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.CLOSE);
        warningDialog.setHeaderText(title);
        warningDialog.setContentText(body);
        warningDialog.show();
    }

    /**
     * Sets up combo box.
     *
     * @param comboBox the combo box
     */
    public void setUpComboBox(ComboBox<String> comboBox) {
        comboBox.setStyle("-fx-font: 13px \"Microsoft Sans Serif\";" +
                "    -fx-border-radius: 30;\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-border-style: solid;\n" +
                "    -fx-border-color: silver;\n" +
                "    -fx-border-width: 1.5;\n" +
                "    -fx-background-color: rgba(255,255,255,0.75);");
    }

    /**
     * Sets the current user.
     *
     * @param utente the utente
     */
    public abstract void setUtente(UtenteRegistrato utente);
}
