package com.ej.factories.testData;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Deprecated
public class DataDrivenClass {

    @Deprecated
    public static class JsonClass {

        @Deprecated
        public String readJsonRelative(String filePath, String key) {
            InputStream input = ReadJSON.class.getClassLoader().getResourceAsStream(filePath);
            JSONObject jsonObject = null;
            try {
                jsonObject = (JSONObject) new JSONParser().parse(new InputStreamReader(input));
            } catch (IOException e) {
                throw new RuntimeException("JsonFile Path not found!");
            } catch (ParseException e) {
                throw new RuntimeException("Cannot Parse Key entered!");
            }

            if (jsonObject.get(key) == null) {
                throw new RuntimeException("Invalid Key Entered");
            }
            return (String) jsonObject.get(key);
        }


        @Deprecated
        public static String readJson(String filePath, String key){
            JSONObject jsonObject = null;
            try {
                jsonObject = (JSONObject) new JSONParser().parse(new FileReader(filePath));
            } catch (IOException e) {
                throw new RuntimeException("JsonFile Path not found!");
            } catch (ParseException e) {
                throw new RuntimeException("Cannot Parse Key entered!");
            }

            if (jsonObject.get(key) == null) {
                throw new RuntimeException("Invalid Key Entered!");
            }
            return (String) jsonObject.get(key);
        }

    }

    @Deprecated
    public static class PropertiesClass {

        @Deprecated
        public static String readProperties(String name, String key){
            InputStream input = DataDrivenClass.class.getClassLoader().getResourceAsStream(name);

            Properties properties = new Properties();

            try {
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException("Invalid Properties File Path!");
            }

            if (properties.get(key) == null) {
                throw new RuntimeException("Invalid Properties Key!");
            }

            return properties.getProperty(key);
        }

    }

    @Deprecated
    public static class CSVClass {

        @Deprecated
        public static String readCSVInteger(String filePath, int skipLines, int key) {
            if (skipLines == 0) {
                throw new RuntimeException("SkipLines Can't be null!");
            }
            CSVReader reader = null;
            try {
                reader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(skipLines).build();
                String[] nextLine;
                nextLine = reader.readNext();
                if (key >= nextLine.length - 1) {
                    throw new RuntimeException("Invalid Key entered!");
                }
                return nextLine[key];

            } catch (FileNotFoundException e) {
                throw new RuntimeException("Cannot CSV File Path!");
            }
            catch (IOException e) {
                throw new RuntimeException("Cannot read NextLine!");
            } catch (CsvValidationException e) {
                throw new RuntimeException("Line Out of Boundaries!");
            }
        }

        @Deprecated
        @SneakyThrows
        public static String readCSVString(String file, String headerKey, int lineNumber){
            InputStream input = ReadCSV.class.getClassLoader().getResourceAsStream(file);

            if (lineNumber < 1) {
                throw new RuntimeException("Invalid lineNumber entered");
            }

            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new InputStreamReader(input));

            String headersLine = bufferedReader.readLine().replaceAll("[^a-zA-Z,]","");
            String[] headers = headersLine.split(",");
            Map<String, Integer> headerIndices = new HashMap<>();

            for (int i = 0; i < headers.length; i++) {
                headerIndices.put(headers[i].toLowerCase(), i);
            }

            Integer headerIndex = headerIndices.get(headerKey);
            if (headerIndex == null) {
                throw new RuntimeException("Invalid HeaderKey entered");
            }

            String line;
            int currentLineNumber = 1;

            while (true) {
                try {
                    if ((line = bufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (currentLineNumber == lineNumber) {
                    String[] values = line.split(",");
                    if (headerIndex >= values.length) {
                        throw new RuntimeException("Invalid HeaderKey entered");
                    }
                    return values[headerIndex];
                }
                currentLineNumber++;
            }

            throw new RuntimeException("Invalid lineNumber entered");
        }
    }
}
