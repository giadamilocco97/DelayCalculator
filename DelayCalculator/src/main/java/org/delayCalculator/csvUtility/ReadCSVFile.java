package org.delayCalculator.csvUtility;

import org.delayCalculator.trainRecords.ActualTrainRecord;
import org.delayCalculator.trainRecords.PlannedTrainRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSVFile {

    String COMMA_DELIMITER = ",";

    public List<?> readCSV(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getTrainRecords(records, fileName);
    }


    public List<?> getTrainRecords(List<List<String>> records, String fileName) {
        if (fileName.equals("planned.csv")) {
            List<PlannedTrainRecord> plannedTrainRecords = new ArrayList<>();

            for (int i = 1; i < records.size(); i++) {
                List<String> plannedRecord = records.get(i);
                PlannedTrainRecord plannedTrainRecord = new PlannedTrainRecord();
                plannedTrainRecord.setTrainNumber(plannedRecord.get(0));
                plannedTrainRecord.setStationName(plannedRecord.get(1));
                plannedTrainRecord.setTrainTime(ZonedDateTime.parse(plannedRecord.get(2).toString()));
                plannedTrainRecords.add(plannedTrainRecord);
            }
            return plannedTrainRecords;
        }
        if (fileName.equals("actual.csv")){
            List<ActualTrainRecord> actualTrainRecords = new ArrayList<>();

            for (int i = 1; i < records.size(); i++) {
                List<String> actualRecord = records.get(i);
                ActualTrainRecord actualTrainRecord = new ActualTrainRecord();
                actualTrainRecord.setTrainNumber(actualRecord.get(0));
                actualTrainRecord.setStationName(actualRecord.get(1));
                actualTrainRecord.setTrainTime(ZonedDateTime.parse(actualRecord.get(2).toString()));
                actualTrainRecords.add(actualTrainRecord);
            }
            return actualTrainRecords;
        }
        return null;
    }

}
