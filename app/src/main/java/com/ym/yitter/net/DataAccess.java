package com.ym.yitter.net;

import android.content.Context;
import com.ym.yitter.Constants;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class DataAccess {
    private static DataAccess instance;
    private TwitterClient twitterClient;

    public static void init(Context ctx) {
        DataAccess dataAccess = new DataAccess();
        init(dataAccess);
    }

    public static void init(DataAccess dataAccess) {
        instance = dataAccess;
    }

    public static DataAccess getInstance() {
        return instance;
    }

    public AuthRequestToken startAuth(Context ctx) {
        return new AuthRequestToken(ctx, new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey(Constants.API_KEY)
                .apiSecret(Constants.API_SECRET)
                .callback(Constants.CALLBACK_URL)
                .build());
    }

    public TwitterClient getTwitterClient() {
        return twitterClient;
    }

    public void setTwitterClient(TwitterClient twitterClient) {
        this.twitterClient = twitterClient;
    }
}
