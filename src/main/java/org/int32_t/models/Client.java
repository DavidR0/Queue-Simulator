package org.int32_t.models;


public class Client {
    private int clientId;
    private int arrivalTime;
    private int serviceTime;
    private int queue = -1;
    private int timeSpentInQueue = 0;


    public Client(int clientId, int arrivalTime, int serviceTime) {
        this.clientId = clientId;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public Client(int clientId, int arrivalTime, int serviceTime, int queue) {
        this.clientId = clientId;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.queue = queue;
    }

    public void increaseTimeSpentInQueue(){
        timeSpentInQueue++;
    }

    public int getTimeSpentInQueue(){
        return  timeSpentInQueue;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
}
