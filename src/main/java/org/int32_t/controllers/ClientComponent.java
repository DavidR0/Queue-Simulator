package org.int32_t.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.int32_t.models.Client;

import java.io.IOException;

public class ClientComponent extends AnchorPane {

    @FXML
    private Label clientTitle;

    @FXML
    private Label arrival;

    @FXML
    private Label service;

    public ClientComponent(Client client){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ClientComponent.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        clientTitle.setText("Client ID : " + String.valueOf(client.getClientId()));
        arrival.setText("Arrival : " + String.valueOf(client.getArrivalTime()));
        service.setText("Service : " + String.valueOf(client.getServiceTime()));
    }

}
