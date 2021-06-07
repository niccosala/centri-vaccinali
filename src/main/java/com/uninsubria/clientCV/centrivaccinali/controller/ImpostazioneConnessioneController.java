package com.uninsubria.clientCV.centrivaccinali.controller;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;
import com.uninsubria.serverCV.Server;
import com.uninsubria.serverCV.ServerInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ImpostazioneConnessioneController extends Controller {

    @FXML
    private Button btnDefault, btnConferma;
    @FXML
    private TextField portaTextField, ipTextField;
    @FXML
    private Label labelInfo;


    public void tryConnection() {
        boolean connected;

        ServerInfo.IP_SERVER = ipTextField.getText();
        ServerInfo.PORT = Integer.parseInt(portaTextField.getText());

        connected = pingHost(ServerInfo.IP_SERVER, ServerInfo.PORT);

        if (connected)
            labelInfo.setText("Connessione riuscita");
        else
            labelInfo.setText("Connessione rifiutata, riprovare.");

    }

    private static boolean pingHost(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 1500);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void setDefaultValue() {
        ipTextField.setText("localhost");
        portaTextField.setText("8888");
    }

    @Override
    public void setUtente(UtenteRegistrato utente) {
    }
}
