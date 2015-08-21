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

    protected TwitterClient(Context ctx, OAuthService service, Token accessToken) {
        this.service = service;
        this.accessToken = accessToken;
        this.ctx = ctx;
    }

    protected List<Tweet> getTimeline() {
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/statuses/home_timeline.json");
        service.signRequest(accessToken, request);
        Response response = request.send();
        return (List<Tweet>) GsonFactory.getGson().fromJson(response.getBody(), new TypeToken<List<Tweet>>(){}.getType());
    }

    protected Tweet postTweet(String message) {
        OAuthRequest request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/statuses/update.json?status=" + URLEncoder.encode(message));
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
