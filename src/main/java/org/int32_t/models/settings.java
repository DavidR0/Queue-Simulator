package org.int32_t.models;

public class Settings {
    private static int nrClients = 20;
    private static int nrQueues = 2;
    private static int SimInterval = 20;
    private static int minArrivalTime = 2;
    private static int maxArrivalTime = 7;
    private static int minServiceTime = 2;
    private static int maxServiceTime = 3;

    public static String getFileName() {
        return fileName;
    }

    private static final String fileName = "log.txt";

    public static int getSleepTime() {
        return sleepTime;
    }

    private static final int sleepTime = 1000;

    public int getNrClients() {
        return nrClients;
    }

    public void setNrClients(int nrClients) {
        this.nrClients = nrClients;
    }

    public int getNrQueues() {
        return nrQueues;
    }

    public void setNrQueues(int nrQueues) {
        this.nrQueues = nrQueues;
    }

    public int getSimInterval() {
        return SimInterval;
    }

    public void setSimInterval(int simInterval) {
        SimInterval = simInterval;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }


    public Settings(int nrClients, int nrQueues, int SimInterval, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime){
        Settings.nrClients = nrClients;
        Settings.nrQueues = nrQueues;
        Settings.SimInterval = SimInterval;
        Settings.minArrivalTime = minArrivalTime;
        Settings.maxServiceTime = maxServiceTime;
        Settings.maxArrivalTime = maxArrivalTime;
        Settings.minServiceTime = minServiceTime;
    }

    public Settings(){};
}
