package org.delayCalculator.csvUtility;

import org.delayCalculator.trainRecords.OutputTrainRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateCSVFile {

    private static final String CSV_HEADER = "Numero Treno, Destinazione, Ora Pianificata, Ora Effettiva, Ritardo\n";

    public void generateCsv(List<OutputTrainRecord> outputTrainRecords) throws IOException {

        File csvFile = new File("output.csv");
        FileWriter fileWriter = new FileWriter(csvFile);

        StringBuilder csvContent = new StringBuilder();
        csvContent.append(CSV_HEADER);

        for (OutputTrainRecord outputTrainRecord : outputTrainRecords) {
            csvContent.append(outputTrainRecord.getTrainNumber()).append(",")
                    .append(outputTrainRecord.getDestination()).append(",")
                    .append(outputTrainRecord.getPlannedTime().toString()).append(",")
                    .append(outputTrainRecord.getActualTime().toString()).append(",")
                    .append(outputTrainRecord.getDelayTime().toString()).append("\n");
        }

        fileWriter.write(csvContent.toString());
        fileWriter.close();
    }

}
