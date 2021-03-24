package org.int32_t.controllers;

import javafx.application.Platform;
import org.int32_t.models.Client;
import org.int32_t.models.Settings;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationController implements Runnable{
    Settings settings = new Settings();

    public SimulationController() {}

    @Override
    public void run() {
        int currentTime = 0;
        while(currentTime < settings.getSimInterval()){
            //TimeRelated stuff
            System.out.println("Current time interval : " + currentTime);
            currentTime++;

            //Update UI Data
            Platform.runLater(() -> ViewController.updateViewData(createClientList()));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Client> createClientList(){ //TODO make this from the blocking queue, and not fake data
        List<Client> clientList = new LinkedList<>();
        for(int i = 0; i < 20; ++i){
            if(ThreadLocalRandom.current().nextInt(settings.getMinArrivalTime(), settings.getMaxArrivalTime())%2==0) {
                clientList.add(new Client(i, ThreadLocalRandom.current().nextInt(settings.getMinArrivalTime(), settings.getMaxArrivalTime()), ThreadLocalRandom.current().nextInt(settings.getMinServiceTime(), settings.getMaxServiceTime())));
            }else{
                clientList.add(new Client(i, ThreadLocalRandom.current().nextInt(settings.getMinArrivalTime(), settings.getMaxArrivalTime()), ThreadLocalRandom.current().nextInt(settings.getMinServiceTime(), settings.getMaxServiceTime()), i % settings.getNrQueues()));
            }
        }
        return  clientList;
    }
}
