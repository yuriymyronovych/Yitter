package com.ym.yitter.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class GsonFactory {
    private static Gson gson;
    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder b = new GsonBuilder();
            b.setDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
            gson = b.create();
        }
        return gson;
    }
}
