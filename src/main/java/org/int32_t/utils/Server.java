package org.int32_t.utils;

import org.int32_t.models.Client;
import org.int32_t.models.Settings;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    private int serverID;
    private int totalWaitTime;
    private int nrClientsProcessed;

    public Server(int ID){
        waitingPeriod = new AtomicInteger(0);
        clients = new LinkedBlockingQueue<>();
        serverID = ID;
    }

    public void addClient(Client client){
        client.setQueue(serverID);
        clients.add(client);
        waitingPeriod.addAndGet(client.getServiceTime());
    }

    public int getServerID(){
        return serverID;
    }

    public List<Client> getClients(){
        return new LinkedList<>(clients);
    }

    public int getNrClients(){
        return  clients.size();
    }

    public int waitingPeriod(){
        return waitingPeriod.get();
    }

    private void increaseWaitTime(){
        for(Client c: clients){
            if(clients.peek() != null && clients.peek().getClientId() != c.getClientId()) {
                c.increaseTimeSpentInQueue();
            }
        }
    }

    public int getTotalWaitTime(){
        return totalWaitTime;
    }

    public int getNrClientsProcessed(){
        return  nrClientsProcessed;
    }

    @Override
    public void run() {
        while(true){

            if(clients.peek() != null){
                clients.peek().setServiceTime(clients.peek().getServiceTime() - 1);
                if(clients.peek().getServiceTime() == 0){
                    nrClientsProcessed++;
                    totalWaitTime += clients.peek().getTimeSpentInQueue();
                    clients.remove();

                }
            }

            try {
                Thread.sleep(Settings.getSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            increaseWaitTime();
            if(waitingPeriod.get() > 0) {
                waitingPeriod.decrementAndGet();
            }
        }
    }
}
