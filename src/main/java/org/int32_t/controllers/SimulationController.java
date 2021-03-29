package org.int32_t.controllers;

import javafx.application.Platform;
import org.int32_t.models.Client;
import org.int32_t.models.Settings;
import org.int32_t.utils.Scheduler;
import org.int32_t.utils.Server;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationController implements Runnable{
    Settings settings = new Settings();
    private Scheduler scheduler;
    private final String selectionPolicy = "Time";
    private List<Client> clients;

    public SimulationController() {
        scheduler = new Scheduler(settings);
        scheduler.changeStrategy(selectionPolicy);
        clients = Collections.synchronizedList(createClientList());
        Platform.runLater(() -> ViewController.updateViewData(makeViewData(),0));
    }

    @Override
    public void run() {
//        AtomicInteger currentTime = new AtomicInteger(0);
        int currentTime = 0;
        while(currentTime < settings.getSimInterval()){
            //Send tasks with current time to dispatcher
            Iterator<Client> iter = clients.iterator();
            while (iter.hasNext()) {
                Client c = iter.next();
                if(c.getArrivalTime() == currentTime){
                    scheduler.dispatchClient(c);
                    iter.remove();
                }
            }

            //Update UI Data
            int finalCurrentTime = currentTime;
            Platform.runLater(() -> ViewController.updateViewData(makeViewData(), finalCurrentTime));

            //TimeRelated stuff
            System.out.println("Current time interval : " + currentTime);
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Clear old threads?

    }

    private List<Client> createClientList(){
        List<Client> clientList = new LinkedList<>();
        for(int i = 1; i <= settings.getNrClients(); ++i){
                clientList.add(new Client(i, ThreadLocalRandom.current().nextInt(settings.getMinArrivalTime(), settings.getMaxArrivalTime()), ThreadLocalRandom.current().nextInt(settings.getMinServiceTime(), settings.getMaxServiceTime())));
        }
        //Sort the clients with respect to Arrival Time
        clientList.sort(Comparator.comparingInt(Client::getArrivalTime));

        return  clientList;
    }

    private List<Client> makeViewData(){
        List<Client> clientList = new LinkedList<>();
        List<Server> servers = scheduler.getServers();
        for(Server s : servers){
            clientList.addAll(s.getClients());
        }
        clientList.addAll(clients);
        return  clientList;
    }
}
