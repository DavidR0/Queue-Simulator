package org.int32_t.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class QueueComponent extends AnchorPane {
    @FXML
    private Label title;

    @FXML
    private HBox clients;

    public QueueComponent(int ID){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/QueueComponent.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        title.setText("Queue ID: " + String.valueOf(ID));
    }

    public void setClients(List<ClientComponent> clients){
        this.clients.getChildren().clear();
        this.clients.getChildren().addAll(clients);
    }

    public void addClient(ClientComponent clients){
        this.clients.getChildren().add(clients);
    }
}
