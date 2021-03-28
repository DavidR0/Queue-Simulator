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

    public List<Client> getClients(){
        return new LinkedList<>(clients);
    }

    public int getNrClients(){
        return  clients.size();
    }

    public int waitingPeriod(){
        return waitingPeriod.get();
    }

    @Override
    public void run() {
        while(true){

            if(clients.peek() != null){
                clients.peek().setServiceTime(clients.peek().getServiceTime() - 1);
                if(clients.peek().getServiceTime() == 0){
                    clients.remove();
                }
            }

            try {
                Thread.sleep(Settings.getSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitingPeriod.decrementAndGet();
        }
    }
}
