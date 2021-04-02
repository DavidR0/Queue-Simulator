package org.int32_t.controllers;

import javafx.application.Platform;
import org.int32_t.models.Client;
import org.int32_t.models.Settings;
import org.int32_t.utils.Scheduler;
import org.int32_t.utils.Server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
        int currentTime = 0;
        createFile();
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(settings.getFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            try {
                myWriter.write(getLogTxt(currentTime));

            } catch (IOException e) {
                e.printStackTrace();
            }

            //TimeRelated stuff
//            System.out.println("Current time interval : " + currentTime);
            currentTime++;
            try {
                Thread.sleep(settings.getSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            myWriter.write("Average wait time: " + calculateAverageWaitTime());
            myWriter.close();
            System.out.println("File Closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Clear old threads?
    }

    private String getLogTxt(int time){
        String buff = "Time "  + time + "\nWaiting clients: ";

        for(Client c : clients){
            buff += "(" + c.getClientId() + "," + c.getArrivalTime() + "," +c.getServiceTime() + ");";
        }
        buff += "\n";

        List<Client> clientList = new LinkedList<>();
        List<Server> servers = scheduler.getServers();
        for(Server s : servers){
            clientList = s.getClients();
            if(clientList.isEmpty()){
                buff += "Queue "+s.getServerID() + ": closed\n";
            }else{
                buff += "Queue "+s.getServerID() + ": ";
                for(Client c : clientList){
                    buff += "(" + c.getClientId() + "," + c.getArrivalTime() + "," +c.getServiceTime() + ");";
                }
                buff += "\n";
            }
        }
        buff += "\n\n";
        return buff;
    }



    public File createFile() {
        try {
            File myObj = new File(settings.getFileName());
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            return myObj;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
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

    public float calculateAverageWaitTime(){
        int totalTime = 0, totalClients = 0;
        List<Server> servers = scheduler.getServers();
        for(Server s : servers){
            totalTime += s.getTotalWaitTime();
            totalClients += s.getNrClientsProcessed();
        }
        return totalTime/totalClients;
    }

    private List<Client> makeViewData(){
        List<Client> clientList = new LinkedList<>();
        List<Server> servers = scheduler.getServers();

        for(Server s : servers){
            clientList.addAll(s.getClients());
        }

        if(clients.size() <= 300){
            clientList.addAll(clients);
        }

        return  clientList;
    }
}
