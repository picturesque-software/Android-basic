package com.byted.homework4;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonDemo {

    public static void generateJsonString() {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject tempJson = new JSONObject();
            tempJson.put("min", 11.34);
            tempJson.put("max", 19.01);
            jsonObject.put("temp", tempJson);
            jsonObject.put("success", true);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("Adam");
            jsonArray.put("Bob");
            jsonArray.put("John");
            jsonObject.put("notification_user_id", jsonArray);
            Log.d("JsonDemo", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void parseJsonString() {
        String s = "{\"temp\":{\"min\":11.34,\"max\":19.01},\"success\":true,\"notification_user_id\":[\"Adam\",\"Bob\",\"John\"]}";
        try {
            JSONObject jsonObject = new JSONObject(s);

            JSONArray notificationUserId = jsonObject.getJSONArray("notification_user_id");

            boolean success = jsonObject.optBoolean("unexist",true);
            boolean unexist = jsonObject.getBoolean("unexist");
            Log.d("JsonDemo", "success:" + unexist);
        } catch (JSONException e) {
            Log.d("JsonDemo", "crash:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
