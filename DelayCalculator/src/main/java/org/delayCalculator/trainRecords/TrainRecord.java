package org.delayCalculator.trainRecords;

import java.time.ZonedDateTime;


public abstract class TrainRecord {

    private String trainNumber;
    private String stationName;
    private ZonedDateTime trainTime;

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public ZonedDateTime getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(ZonedDateTime trainTime) {
        this.trainTime = trainTime;
    }
}
