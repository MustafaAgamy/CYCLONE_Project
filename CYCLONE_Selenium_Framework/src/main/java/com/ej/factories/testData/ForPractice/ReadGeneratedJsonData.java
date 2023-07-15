package com.ej.factories.testData.ForPractice;

import com.ej.properties.setup.Test;
import io.reactivex.rxjava3.annotations.Beta;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
@Beta
public class ReadGeneratedJsonData {

    private static final Test testSetups = ConfigFactory.create(Test.class);

    private static final String TEST_DATA_DEFAULT_FOLDER = testSetups.testDataDefaultFolder();
    public static String jsonDataRead(String filePath) {
        StringBuilder jsonDataBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_DATA_DEFAULT_FOLDER + filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonDataBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(jsonDataBuilder);
        return jsonDataBuilder.toString();
    }

    public static String getJsonValue(String filePath, int key, String keyName) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray array = (JSONArray) parser.parse(jsonDataRead(TEST_DATA_DEFAULT_FOLDER + filePath));
            JSONObject obj = (JSONObject) array.get(key);
            keyName = (String) obj.get(keyName + key);
//            System.out.println(keyName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return keyName;
    }
}
