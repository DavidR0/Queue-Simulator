package org.int32_t.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AnalyticsDialog extends AnchorPane {

    public AnalyticsDialog(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AnalyticsDialog.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
