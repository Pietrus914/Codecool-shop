package com.codecool.shop.util;

import com.codecool.shop.model.Gsonable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    public static String serialize(Gsonable object){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedGson = gson.toJson(object);
//        System.out.println("serialized: " + serializedGson);

        return serializedGson;
    }

    public static void saveToFile(Gsonable object, String path){
        String serializedJson = serialize(object);
        try {
            writeToJsonFile(serializedJson, object.getId(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeToJsonFile(String jsonString, int id, String path) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(path + id + ".txt"));
        writer.write(jsonString);
        writer.close();
    }
}