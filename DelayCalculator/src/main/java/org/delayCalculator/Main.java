package org.delayCalculator;

import org.delayCalculator.csvUtility.CreateCSVFile;
import org.delayCalculator.csvUtility.ReadCSVFile;
import org.delayCalculator.trainRecords.ActualTrainRecord;
import org.delayCalculator.trainRecords.OutputTrainRecord;
import org.delayCalculator.trainRecords.PlannedTrainRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        List<PlannedTrainRecord> planned = (List<PlannedTrainRecord>) new ReadCSVFile().readCSV("planned.csv");
        List<ActualTrainRecord> actual = (List<ActualTrainRecord>) new ReadCSVFile().readCSV("actual.csv");

        List<OutputTrainRecord> outputTrainRecordList = new ArrayList<>();

        List<String> differentTrains = planned.stream().map(PlannedTrainRecord::getTrainNumber).distinct().collect(Collectors.toList());

        for (String trainType : differentTrains) {
            List<PlannedTrainRecord> plannedTrainRecords = planned.stream()
                    .filter(plannedTrain -> plannedTrain.getTrainNumber().equals(trainType)).toList();

            List<ActualTrainRecord> actualTrainRecords = actual.stream()
                    .filter(actualTrain -> actualTrain.getTrainNumber().equals(trainType)).toList();

            PlannedTrainRecord lastPlanned = plannedTrainRecords.get(plannedTrainRecords.size()-1);
            ActualTrainRecord lastActual = actualTrainRecords.get(actualTrainRecords.size()-1);

            outputTrainRecordList.add(createOutputTrainRecord(lastPlanned, lastActual));

        }

        Collections.sort(outputTrainRecordList);

        List<OutputTrainRecord> mostDelayed = outputTrainRecordList.subList(0,5);
        new CreateCSVFile().generateCsv(mostDelayed);

    }

    protected static OutputTrainRecord createOutputTrainRecord(PlannedTrainRecord plannedTrainRecord, ActualTrainRecord actualTrainRecord){
        OutputTrainRecord outputTrainRecord = new OutputTrainRecord();
        outputTrainRecord.setTrainNumber(plannedTrainRecord.getTrainNumber());
        outputTrainRecord.setDestination(plannedTrainRecord.getStationName());
        outputTrainRecord.setActualTime(actualTrainRecord.getTrainTime());
        outputTrainRecord.setPlannedTime(plannedTrainRecord.getTrainTime());
        return outputTrainRecord;
    }


}