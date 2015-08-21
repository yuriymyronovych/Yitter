package com.ym.yitter.net;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import com.ym.yitter.Constants;
import com.ym.yitter.ProgressAsyncTask;
import com.ym.yitter.data.GsonFactory;
import com.ym.yitter.data.Tweet;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Yuriy Myronovych on 19/08/2015.
 */
public class TwitterClient {
    protected Context ctx;
    protected OAuthService service;
    protected Token accessToken;
    protected TwitterRequestFactory factory;

    protected TwitterClient(Context ctx, OAuthService service, Token accessToken, TwitterRequestFactory factory) {
        this.service = service;
        this.accessToken = accessToken;
        this.ctx = ctx;
        this.factory = factory;
    }

    protected List<Tweet> getTimeline() {
        OAuthRequest request = factory.getTimelineRequest();
        service.signRequest(accessToken, request);
        Response response = request.send();
        return (List<Tweet>) GsonFactory.getGson().fromJson(response.getBody(), new TypeToken<List<Tweet>>(){}.getType());
    }

    protected Tweet postTweet(String message) {
        OAuthRequest request = factory.getPostTweetRequest(message);
        service.signRequest(accessToken, request);
        Response response = request.send();
        return GsonFactory.getGson().fromJson(response.getBody(), Tweet.class);
    }

    public void getTimelineAsync(DataListener<List<Tweet>> listener) {
        new ProgressAsyncTask<List<Tweet>>(ctx, listener, false) {
            @Override
            protected List<Tweet> doInBackground(Void... params) {
                return getTimeline();
            }
        }.execute();
    }

    public void postTweetAsync(final String message, DataListener<Tweet> listener) {
        new ProgressAsyncTask<Tweet>(ctx, listener) {
            @Override
            protected Tweet doInBackground(Void... params) {
                return postTweet(message);
            }
        }.execute();
    }
}
