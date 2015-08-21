package com.ym.yitter.net;

import android.content.Context;
import com.ym.yitter.Constants;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class DataAccess {
    private static DataAccess instance = new DataAccess();
    private TwitterClient twitterClient;

    public static DataAccess getInstance() {
        return instance;
    }

    /**
     * startAuth -> RequestToken -> AccessToken -> TwitterClient
     */
    public RequestToken startAuth(Context ctx) {
        return new RequestToken(ctx, new ServiceBuilder()
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
