package com.swiggy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;
import java.util.Map;


public class JsonParser {

    public Map<String,DeliveryExecutive> readDeliveryJSON(String path, Map<String, DeliveryExecutive> delivery) {

        DeliveryExecutive deliveryExecutive = null;
        JSONParser parser = new JSONParser();
        try {
            List<Object> obj = (List<Object>) parser.parse(new FileReader(path));

            for (Object json :  obj) {
                JSONObject jsonObject = (JSONObject) json;
                System.out.println(jsonObject);
                String id = (String) jsonObject.get("id");
                String location = (String) jsonObject.get("current_location");
                String time = (String) jsonObject.get("last_order_delivered_time");

                Location location1 = new Location(location.split(",")[0], location.split(",")[1]);
                deliveryExecutive = new DeliveryExecutive(id, location1, time, Status.WAITING);
                delivery.put(id, deliveryExecutive);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return delivery;

    }

    public Map<String, Order> readOrderJSON(String path, Map<String, Order> orderMap) throws SwiggySystemException {
        Order order = null;
        JSONParser parser = new JSONParser();
        try {
            List<Object> obj = (List<Object>) parser.parse(new FileReader(path));

            for (Object json :  obj) {
                JSONObject jsonObject = (JSONObject) json;
                System.out.println(jsonObject);
                String id = (String) jsonObject.get("id");
                String location = (String) jsonObject.get("restaurant_location");
                String time = (String) jsonObject.get("ordered_time");

                Location location1 = new Location(location.split(",")[0], location.split(",")[1]);
                order = new Order(id, location1, time, Status.WAITING);
                orderMap.put(id, order);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderMap;
    }

    public void jsonWrite(String path, Map<String, String> deliveryOrder) throws IOException, SwiggySystemException {
        if (deliveryOrder.size()==0)
            throw new SwiggySystemException("Orders are not assigned");
        // Writing to a file
        try {
        File file=new File(path);
        file.createNewFile();
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry delOrder : deliveryOrder.entrySet()) {
            JSONObject json = new JSONObject();
            json.put("order_id", delOrder.getKey());
            json.put("de_id", delOrder.getValue());
            jsonArray.add(json);
        }
        FileWriter fileWriter = new FileWriter(file);
        System.out.println("Writing JSON object to file");
        System.out.print(jsonArray);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

