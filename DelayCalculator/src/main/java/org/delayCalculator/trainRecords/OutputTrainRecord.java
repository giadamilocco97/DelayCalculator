package org.delayCalculator.trainRecords;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class OutputTrainRecord implements Comparable<Object>{

    private String trainNumber;
    private String destination;
    private ZonedDateTime plannedTime;
    private ZonedDateTime actualTime;

    public Long getDelayTime(){
        return ChronoUnit.SECONDS.between(this.plannedTime, this.actualTime);
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ZonedDateTime getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(ZonedDateTime plannedTime) {
        this.plannedTime = plannedTime;
    }

    public ZonedDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(ZonedDateTime actualTime) {
        this.actualTime = actualTime;
    }

    @Override
    public int compareTo(Object o) {
        OutputTrainRecord trainRecord = (OutputTrainRecord) o;
        return trainRecord.getDelayTime().compareTo(this.getDelayTime());
    }
}
