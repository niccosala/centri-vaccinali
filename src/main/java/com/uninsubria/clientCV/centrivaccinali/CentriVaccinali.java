/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali;

import com.uninsubria.clientCV.condivisa.Util;
import com.uninsubria.serverCV.IComandiServer;
import com.uninsubria.serverCV.ServerInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

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

        boolean connected = false;

        while(!connected) {
            System.out.println(" > inserisci indirizzo IP del server a cui vuoi connetterti: ");
            ServerInfo.IP_SERVER = new Scanner(System.in).nextLine();
            connected = pingHost(ServerInfo.IP_SERVER, IComandiServer.PORT);

            if(connected)
                System.out.println("Connessione riuscita");
            else
                System.out.println("Connessione fallita. Riprovare\n");
        }

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

    private static boolean pingHost(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 1500);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}