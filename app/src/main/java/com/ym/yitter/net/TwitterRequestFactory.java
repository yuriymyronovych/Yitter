package com.ym.yitter.net;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;

import java.net.URLEncoder;

/**
 * Created by Yuriy Myronovych on 21/08/2015.
 */
public class TwitterRequestFactory {
    public OAuthRequest getTimelineRequest() {
        return new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/statuses/home_timeline.json");
    }

    public OAuthRequest getPostTweetRequest(String message) {
        return new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/statuses/update.json?status=" + URLEncoder.encode(message));
    }
}
