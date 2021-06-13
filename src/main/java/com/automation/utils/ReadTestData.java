package com.automation.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
//import com.jayway.jsonpath.JsonPath;


public class ReadTestData {
    private final static String filePath = "TestData//testdata.json";

    @SuppressWarnings("unchecked")
    public static void readJsonFromFile() throws IOException, ParseException {

        JsonReader reader = new JsonReader(new FileReader("TestData//testdata.json"));
        Gson gson = new Gson();

        JsonElement jelement = gson.fromJson(reader, JsonElement.class);
        // JsonElement jelement=new JsonParser().parse(reader);
        Object obj = read(jelement);
        Map<String, Object> map = (Map<String, Object>) obj;
        System.out.println(map.get("username"));
    }

    public static Object read(JsonElement json) {

        if (json.isJsonObject()) {
            JsonObject obj = json.getAsJsonObject();
            Map<String, Object> map = new LinkedTreeMap<String, Object>();
            Set<Map.Entry<String, JsonElement>> entitySet = obj.entrySet();
            for (Map.Entry<String, JsonElement> entry : entitySet) {
                map.put(entry.getKey(), read(entry.getValue()));
            }
            return map;

        } else if (json.isJsonPrimitive()) {
            JsonPrimitive prim = json.getAsJsonPrimitive();
            if (prim.isBoolean()) {

                return prim.getAsBoolean();
            } else if (prim.isString()) {

                return prim.getAsString();
            } else if (prim.isNumber()) {

                if (prim.getAsString().contains("."))
                    return prim.getAsDouble();
                else {
                    return prim.getAsLong();
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) throws IOException, ParseException {
        readJsonFromFile();
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getData() throws IOException {
        File f = new File("TestData//testdata.json");
        String json = FileUtils.readFileToString(f, "UTF-8");
        Gson gson = new Gson();
        Map<String, Object> data = new LinkedTreeMap<String, Object>();
        data.putAll(gson.fromJson(json, Map.class));
        return data;
    }

    public static JSONObject getJsonData() throws IOException, ParseException {
        //pass the path of the testdata.json file
        File filename = new File("TestData//testdata.json");
        //convert json file into string
        String json = FileUtils.readFileToString(filename, "UTF-8");
        //parse the string into object
        Object obj = new JSONParser().parse(json);
        //give jsonobject  that I can return it to the function everytime it get called
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }


    public static String getTestData(String input) throws IOException, ParseException {
        String testdata;
        //	return  testdata = JsonUtil.getData().get(input).toString();
        return testdata = (String) getJsonData().get(input);
    }


	/*public static String getdatafromjson(String path) throws IOException {

		File f = new File("TestData//testdata.json");
		String json = FileUtils.readFileToString(f, "UTF-8");
		String result;
		return result = JsonPath.read(json, path);

	}*/
}
