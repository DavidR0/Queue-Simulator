package org.int32_t;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/View.fxml")); //Loads the UI
        primaryStage.setTitle("Queue Simulator");
        Scene scene = new Scene(root, 1400, 1000);
        scene.getStylesheets().add(getClass().getResource("styles/bootstrap3.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("org/int32_t/resources/icon.png")); //Adds the application icon
        primaryStage.show();

    }

    //Entry point into the app
    public static void main(String[] args)  {
        launch(args);
    }


}
