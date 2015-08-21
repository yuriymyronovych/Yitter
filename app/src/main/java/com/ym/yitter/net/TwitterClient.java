package com.ym.yitter.net;

import android.app.AlertDialog;
import android.content.Context;
import com.google.gson.reflect.TypeToken;
import com.ym.yitter.ProgressAsyncTask;
import com.ym.yitter.R;
import com.ym.yitter.data.GsonFactory;
import com.ym.yitter.data.Tweet;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

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
        if (response.isSuccessful()) {
            return GsonFactory.getGson().fromJson(response.getBody(), Tweet.class);
        } else {
            return null;
        }
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

            @Override
            protected void onPostExecute(Tweet tweet) {
                super.onPostExecute(tweet);
                if (tweet == null) {
                    new AlertDialog.Builder(ctx).setMessage(ctx.getString(R.string.failed)).show();
                }
            }
        }.execute();
    }
}
