package com.ej.factories.testData;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ReadCSV {

    private final String csvFilePath;

    public ReadCSV(String csvFilePath){
        this.csvFilePath = csvFilePath;
    }

    @SneakyThrows
    public String readCSV(int skipLines, int key) {
        InputStream input = ReadCSV.class.getClassLoader().getResourceAsStream(csvFilePath);

        if(input == null){
            throw new FileNotFoundException("CSVFile Not Found");
        }
        if (skipLines == 0) {
            throw new NullPointerException("skipLines Can't be null");
        }
        CSVReader reader = new CSVReaderBuilder(new InputStreamReader((input))).withSkipLines(skipLines).build();

        String[] nextLine;
        if ((nextLine = reader.readNext()) == null) {
            throw new IllegalArgumentException("Line Out of Boundaries");
        }

        if (key >= nextLine.length - 1) {
            throw new IllegalArgumentException("Invalid CSVKey entered");
        }
        return nextLine[key];
    }
    @SneakyThrows
    public String readCSV(String headerKey, int lineNumber) {
        InputStream input = ReadCSV.class.getClassLoader().getResourceAsStream(csvFilePath);

        if(input == null){
            throw new FileNotFoundException("CSVFile Not Found");
        }
        if (lineNumber < 1) {
            throw new NullPointerException("lineNumber Can't be null");
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));

        String headersLine = bufferedReader.readLine().replaceAll("[^a-zA-Z,]", "");
        String[] headers = headersLine.split(",");
        Map<String, Integer> headerIndices = new HashMap<>();

        for (int i = 0; i < headers.length; i++) {
            headerIndices.put(headers[i], i);
        }

        Integer headerIndex = headerIndices.get(headerKey);
        if (headerIndex == null) {
            throw new IllegalArgumentException("Invalid headerKey entered");
        }

        String line;
        int currentLineNumber = 1;

        while ((line = bufferedReader.readLine()) != null) {
            if (currentLineNumber == lineNumber) {
                String[] values = line.split(",");
//                if (headerIndex >= values.length) {
//                    throw new IllegalArgumentException("Invalid headerKey entered");
//                }
                return values[headerIndex];
            }
            currentLineNumber++;
        }

        throw new IllegalArgumentException("Invalid lineNumber entered");
    }
}
