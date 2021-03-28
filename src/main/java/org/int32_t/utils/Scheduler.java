package org.int32_t.utils;

import org.int32_t.models.Client;
import org.int32_t.models.Settings;

import java.util.LinkedList;
import java.util.List;

public class Scheduler {
    Strategy strategy;
    Settings settings;
    private List<Server> servers;

    public Scheduler(Settings settings){
        this.settings = settings;
            servers = new LinkedList<>();

        for(int i = 0; i < settings.getNrQueues(); ++i){
            Server buff = new Server(i);
            Thread t = new Thread(buff);
            t.start();
            servers.add(buff);
        }
    }

    public void changeStrategy(String policy){
        if(policy.equals("Queue")){
            strategy = new ConcreteStrategyQueue();
        }else if(policy.equals("Time")){
            strategy = new ConcreteStrategyTime();
        }
    }
    public void dispatchClient(Client c){
        strategy.addTask(servers,c);
    }

    public List<Server> getServers(){
        return servers;
    }
}
