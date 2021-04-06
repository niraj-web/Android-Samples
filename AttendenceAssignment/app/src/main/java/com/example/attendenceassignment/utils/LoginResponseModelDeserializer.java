package com.example.attendenceassignment.utils;
import com.example.attendenceassignment.Model.ProjectAssModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


public class LoginResponseModelDeserializer implements JsonDeserializer<ProjectAssModel> {

    @Override
    public ProjectAssModel deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        ProjectAssModel userData = null;
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement jsonType = jsonObject.get("data");
            Gson gson = new GsonBuilder().setLenient().create();
            if (jsonType != null && jsonType.isJsonObject()) {
                JsonObject type = jsonType.getAsJsonObject();
                userData = new ProjectAssModel();
                userData.setCount(jsonObject.get("count").getAsInt());
                if (jsonObject.has("msg"))
                    userData.setMsg(jsonObject.get("msg").getAsString());
                userData.setData(gson.fromJson(type, (Type) ProjectAssModel.Datum.class));
            } else {
                JsonArray type = jsonType.getAsJsonArray();
                userData = new ProjectAssModel();
                userData.setCount(jsonObject.get("count").getAsInt());
                if (jsonObject.has("msg"))
                    userData.setMsg(jsonObject.get("msg").getAsString());
                userData.setData(gson.fromJson(type, (Type) ProjectAssModel.Datum.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userData;
    }
}