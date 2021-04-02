package org.int32_t.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class AnalyticsDialog extends AnchorPane {

    @FXML
    private Text avgTime;


    public AnalyticsDialog(float avgTime){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AnalyticsDialog.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.avgTime.setText(String.valueOf(avgTime));
    }
}
