package com.ym.yitter.net;

import com.google.gson.reflect.TypeToken;
import com.ym.yitter.Constants;
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
public class TwitterServiceClient {
    private OAuthService service;
    private Token requestToken;
    private Token accessToken;

    public TwitterServiceClient() {
        service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey(Constants.API_KEY)
                .apiSecret(Constants.API_SECRET)
                .callback(getCallbackUrl())
                .build();
    }

    public String getCallbackUrl() {
        return Constants.CALLBACK_URL;
    }

    /**
     * Step 1. Obtain requestToken
     */
    public void prepareRequestToken() {
        requestToken = service.getRequestToken();
    }

    /**
     * Step 2. Get an external url to authorise
     */
    public String getAuthorizationUrl() {
        return service.getAuthorizationUrl(requestToken);
    }

    /**
     * Step 3. Obtain access token
     */
    public void prepareAccessToken(String verifier) {
        accessToken = service.getAccessToken(requestToken, new Verifier(verifier));
    }

    public List<Tweet> getTimeline() {
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/statuses/home_timeline.json");
        service.signRequest(accessToken, request);
        Response response = request.send();
        return (List<Tweet>) GsonFactory.getGson().fromJson(response.getBody(), new TypeToken<List<Tweet>>(){}.getType());
    }

    public Tweet postTweet(String message) {
        OAuthRequest request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/statuses/update.json?status=" + URLEncoder.encode(message));
        service.signRequest(accessToken, request);
        Response response = request.send();
        return GsonFactory.getGson().fromJson(response.getBody(), Tweet.class);
    }
}
