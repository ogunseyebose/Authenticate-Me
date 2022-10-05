package com.authenticate.FoodOrdering.utils;

import com.google.gson.Gson;

public class JSONUtils {
    private static Gson gson= new Gson();

    private  static <T> String stringifyObject(T object){
        return gson.toJson(object);
    }
    private static <T> T toObject(String text, Class<T> object){
        return gson.fromJson(text, object);
    }

}
