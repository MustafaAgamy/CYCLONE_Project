package com.ej.factories.testData.ForPractice;

import com.ej.properties.setup.Test;
import io.reactivex.rxjava3.annotations.Beta;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Usage This is used to generate Data for your Demo Tests as a JsonArray Object.
 */
@SuppressWarnings("unused")
@Beta
public class GenerateJsonData {
    private static final Test testSetups = ConfigFactory.create(Test.class);

    private static final String TEST_DATA_DEFAULT_FOLDER = testSetups.testDataDefaultFolder();

    /**
     * @param filePath > Where you want the data to be generated.
     * @param propertyName  > The Name of the Property to generate in case you want Email or Numbers
     *                      it will with Email domains or integers only respectively.
     *
     * @param dataToGenerate  > How many data you want to generate.
     * @param valueLength      > the Length the generated Data
     *                        In case of Email the length is calculated for data generated before @.
     */
    public static void generateJsonData(String filePath, String propertyName, int dataToGenerate, int valueLength) {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        File file = new File(TEST_DATA_DEFAULT_FOLDER + filePath);

        try {
            if (file.exists() && jsonArray.size() > 0) {
                jsonArray = (JSONArray) parser.parse(new FileReader(file));
            } else {
                for (int i = 0; i < dataToGenerate; i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(propertyName + i, "");
                    jsonArray.add(jsonObject);
                }
            }

            Random random = new Random();
            String[] randomStrings = new String[jsonArray.size()];

            for (int i = 0; i < randomStrings.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < valueLength; j++) {
                    String alphabet = "abcdefghijklmnopqrstuvwxyz";

                    Pattern numberPattern = Pattern.compile(".*number.*", Pattern.CASE_INSENSITIVE);
                    if (numberPattern.matcher(propertyName).find()) {
                        sb.append(random.nextInt(9));
                    } else {
                        sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
                    }
                }

                Pattern emailPattern = Pattern.compile(".*email.*", Pattern.CASE_INSENSITIVE);
                String[] emailDomains = {"@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com"};
                if (emailPattern.matcher(propertyName).find()) {
                    sb.append(emailDomains[random.nextInt(emailDomains.length)]);
                }

                randomStrings[i] = sb.toString();
            }

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                jsonObject.put(propertyName + i, randomStrings[i]);
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            writer.write(jsonArray.toJSONString());
            writer.close();

            // Print the contents of the file to the console to verify that it's being written correctly
//            System.out.println("File contents:");
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
