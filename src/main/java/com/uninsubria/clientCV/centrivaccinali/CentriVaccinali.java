/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali;

import com.uninsubria.clientCV.condivisa.Util;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Contains the Main of the application
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class CentriVaccinali extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException {

        Parent root = FXMLLoader.load(
                Objects.requireNonNull(CentriVaccinali.class.getClassLoader().getResource(
                        "com/uninsubria/layout/Login.fxml")));

        Scene scene = new Scene(root, 900, 600);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Centri Vaccinali");
        stage.show();

        Util util = new Util();
        util.populateDatabase();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }

}