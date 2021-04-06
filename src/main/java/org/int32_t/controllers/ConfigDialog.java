package org.int32_t.controllers;

import com.jfoenix.controls.JFXDialog;
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
    JFXDialog diag;
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

        nrClients.setPromptText("Number of Clients (Current: " + settings.getNrClients() +" )");
        nrQueues.setPromptText("Number of Queues (Current: " + settings.getNrQueues() +" )");
        simInterval.setPromptText("Simulation Interval (Current: " + settings.getSimInterval() +" )");
        minArrivalTime.setPromptText("Minimum Arrival Time (Current: " + settings.getMinArrivalTime() +" )");
        minServiceTime.setPromptText("Minimum Service Time (Current: " + settings.getMinServiceTime() +" )");
        maxArrivalTime.setPromptText("Maximum Arrival Time (Current: " + settings.getMaxArrivalTime() +" )");
        maxServiceTime.setPromptText("Maximum Service Time (Current: " + settings.getMaxServiceTime() +" )");

    }

    public void setDiag(JFXDialog d){
        diag = d;
    }
    public void saveBtn(){
        if(!nrClients.getText().equals("")){
            settings.setNrClients(Integer.parseInt(nrClients.getText()));
        }

        if(!nrQueues.getText().equals("")){
            settings.setNrQueues(Integer.parseInt(nrQueues.getText()));
        }

        if(!simInterval.getText().equals("")){
            settings.setSimInterval(Integer.parseInt(simInterval.getText()));
        }

        if(!minArrivalTime.getText().equals("")){
            settings.setMinArrivalTime(Integer.parseInt(minArrivalTime.getText()));
        }

        if(!maxArrivalTime.getText().equals("")){
            settings.setMaxArrivalTime(Integer.parseInt(maxArrivalTime.getText()));
        }

        if(!minServiceTime.getText().equals("")){
            settings.setMinServiceTime(Integer.parseInt(minServiceTime.getText()));
        }

        if(!maxServiceTime.getText().equals("")){
            settings.setMaxServiceTime(Integer.parseInt(maxServiceTime.getText()));
        }
        diag.close();
    }

}
