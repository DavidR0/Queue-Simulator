package org.int32_t.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.int32_t.models.Settings;

import java.io.IOException;

public class ConfigDialog extends AnchorPane {
    @FXML private JFXTextField nrClients;
    @FXML private JFXTextField nrQueues;
    @FXML private JFXTextField simInterval;
    @FXML private JFXTextField minArrivalTime;
    @FXML private JFXTextField maxArrivalTime;
    @FXML private JFXTextField minServiceTime;
    @FXML private JFXTextField maxServiceTime;

    Settings settings;
    public ConfigDialog(Settings settings){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ConfigDialog.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.settings = settings;
    }

    public void saveBtn(){
        settings.setNrClients(Integer.parseInt(nrClients.getText()));
        settings.setNrQueues(Integer.parseInt(nrQueues.getText()));
        settings.setSimInterval(Integer.parseInt(simInterval.getText()));
        settings.setMinArrivalTime(Integer.parseInt(minArrivalTime.getText()));
        settings.setMaxArrivalTime(Integer.parseInt(maxArrivalTime.getText()));
        settings.setMinServiceTime(Integer.parseInt(minServiceTime.getText()));
        settings.setMaxServiceTime(Integer.parseInt(maxServiceTime.getText()));
    }

}
