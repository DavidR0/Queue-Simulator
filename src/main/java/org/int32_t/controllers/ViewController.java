package org.int32_t.controllers;

import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.int32_t.models.Client;
import org.int32_t.models.Settings;
import java.util.LinkedList;
import java.util.List;

public class ViewController {

    @FXML
    private VBox queues;

    @FXML
    private StackPane root;

    @FXML
    private HBox availableClients;

    private static Settings settings = new Settings();
    private static List<QueueComponent> queuesComponentsList = new LinkedList<>(); //Should we use some sorta multi thread list here?
    private static List<ClientComponent> clientComponentsList = new LinkedList<>();


    public static void updateViewData(List<Client> clientList){

        queuesComponentsList.clear();
        clientComponentsList.clear();

        //Create the Queues
        for(int i = 0; i < settings.getNrQueues(); ++i){
            queuesComponentsList.add(new QueueComponent(i));
        }

        //Add the clients to the pool if they are not in a queue, if they are them add them to the correct queue;
        for(Client cl : clientList){
            if(cl.getQueue() != -1){
                queuesComponentsList.get(cl.getQueue()).addClient(new ClientComponent(cl));
            }else{
                clientComponentsList.add(new ClientComponent(cl));
            }
        }
    }

    public void startSimulation() {
        //Clear any previous elements
        availableClients.getChildren().clear();
        queues.getChildren().clear();

        SimulationController simulationController = new SimulationController();
        Thread simThread = new Thread(simulationController);
        simThread.start();

        //Create a task on the JavaFX thread so that we update the view every X-ms
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int simulationTime = 0;
                while (simulationTime < settings.getSimInterval() * 4) {
                    Platform.runLater(() -> {
                        queues.getChildren().clear();
                        availableClients.getChildren().clear();
                        queues.getChildren().addAll(queuesComponentsList);
                        availableClients.getChildren().addAll(clientComponentsList);
                    });
                    Thread.sleep(250);
                    simulationTime++;
                }
                System.out.println("UI not updating dynamically anymore");
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }

    public void ConfigDialog(){
        ConfigDialog diag = new ConfigDialog(settings);
        JFXDialog dialog = new JFXDialog(root, diag, JFXDialog.DialogTransition.CENTER);
        diag.setDiag(dialog);
        dialog.show();
    }

    public void AnalyticsDialog(){
        JFXDialog dialog = new JFXDialog(root, new AnalyticsDialog(), JFXDialog.DialogTransition.CENTER);
    }
}
