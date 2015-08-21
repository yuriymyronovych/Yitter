package com.ym.yitter.net;

import android.content.Context;
import com.ym.yitter.ProgressAsyncTask;
import com.ym.yitter.data.Tweet;
import java.util.List;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class TwitterServiceClientAsync {
    private TwitterServiceClient client;
    private Context ctx;

    public TwitterServiceClientAsync(TwitterServiceClient client, Context ctx) {
        this.client = client;
        this.ctx = ctx;
    }

    public String getCallbackUrl() {
        return client.getCallbackUrl();
    }

    /**
     * Step 1. Obtain requestToken
     * Step 2. Get an external url to authorise
     */
    public void prepareRequestAndGetUrl(DataListener<String> listener) {
        new ProgressAsyncTask<String>(ctx, listener) {
            @Override
            protected String doInBackground(Void... params) {
                client.prepareRequestToken();
                return client.getAuthorizationUrl();
            }
        }.execute();
    }

    /**
     * Step 3. Obtain access token
     */
    public void prepareAccessToken(final String verifier, DataListener<Void> listener) {
        new ProgressAsyncTask<Void>(ctx, listener) {
            @Override
            protected Void doInBackground(Void... params) {
                client.prepareAccessToken(verifier);
                return null;
            }
        }.execute();
    }

    public void getTimeline(final DataListener<List<Tweet>> listener) {
        new ProgressAsyncTask<List<Tweet>>(ctx, listener, false) {
            @Override
            protected List<Tweet> doInBackground(Void... params) {
                return client.getTimeline();
            }
        }.execute();
    }

    public void postTweet(final String message, DataListener<Tweet> listener) {
        new ProgressAsyncTask<Tweet>(ctx, listener) {
            @Override
            protected Tweet doInBackground(Void... params) {
                return client.postTweet(message);
            }
        }.execute();
    }
}
