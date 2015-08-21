package com.ym.yitter.net;

import android.content.Context;
import android.provider.ContactsContract;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class DataAccess {
    private static DataAccess instance;
    private TwitterServiceClientAsync client;

    public static void init(Context ctx) {
        DataAccess dataAccess = new DataAccess();
        dataAccess.client = new TwitterServiceClientAsync(new TwitterServiceClient(), ctx);
        init(dataAccess);
    }

    public static void init(DataAccess dataAccess) {
        instance = dataAccess;
    }

    public static DataAccess getInstance() {
        return instance;
    }

    public TwitterServiceClientAsync getClient() {
        return client;
    }
}
