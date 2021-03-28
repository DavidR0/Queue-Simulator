package org.int32_t.utils;

import org.int32_t.models.Client;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addTask(List<Server> servers, Client c) {
        Server minWaitTimerServer = servers.get(0);
        for(Server s : servers){
            if(s.waitingPeriod() < minWaitTimerServer.waitingPeriod()){
                minWaitTimerServer = s;
            }
        }
        minWaitTimerServer.addClient(c);
    }
}
